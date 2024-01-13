package org.bp.realEstate;

import org.apache.camel.Exchange;
import static org.apache.camel.model.rest.RestParamType.body;
import java.time.OffsetDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.bp.realEstate.model.BuyRealEstateRequest;
import org.bp.realEstate.model.ContractInfo;
import org.bp.realEstate.model.Utils;
import org.springframework.stereotype.Component;

import org.bp.realEstate.exceptions.RealEstateException;
import org.bp.realEstate.exceptions.CreditException;
import org.bp.realEstate.model.ExceptionResponse;
import org.bp.realEstate.state.ProcessingEvent;
import org.bp.realEstate.state.ProcessingState;
import org.bp.realEstate.state.StateService;

@Component
public class BuyRealEstateService extends RouteBuilder {

	@org.springframework.beans.factory.annotation.Value("${realestate.kafka.server}")
	private String realestateKafkaServer;

	@org.springframework.beans.factory.annotation.Value("${realestate.service.type}")
	private String realestateServiceType;

	@org.springframework.beans.factory.annotation.Autowired
	ContractIdentifierService contractIdentifierService;

	@org.springframework.beans.factory.annotation.Autowired
	PaymentService paymentService;

	@org.springframework.beans.factory.annotation.Autowired
	StateService realEstateStateService;

	@org.springframework.beans.factory.annotation.Autowired
	StateService creditStateService;

	@Override
	public void configure() throws Exception {
		if (realestateServiceType.equals("all")) 
		{
			bookRealEstateExceptionHandlers();
			getCreditExceptionHandlers();
			gateway();
			realestate();
			credit();
			signContract();
		} else 
		{	
			if (realestateServiceType.equals("gateway"))
				gateway();
			
			if (realestateServiceType.equals("credit")) {
				getCreditExceptionHandlers();
				credit();
			}
			
			if (realestateServiceType.equals("realestate")) {
				bookRealEstateExceptionHandlers();
				realestate();
			}
			
			if (realestateServiceType.equals("signcontract"))
				signContract();
		}
	}

	private void bookRealEstateExceptionHandlers() {
		onException(RealEstateException.class).process((exchange) -> {
			ExceptionResponse er = new ExceptionResponse();
			er.setTimestamp(OffsetDateTime.now());
			Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
			er.setMessage(cause.getMessage());
			exchange.getMessage().setBody(er);
		}).marshal().json().to("stream:out").setHeader("serviceType", constant("realestate")).to(
				"kafka:BuyRealEstateFailTopic?brokers=" + realestateKafkaServer + "&groupId=" + realestateServiceType)
				.handled(true);
	}

	private void getCreditExceptionHandlers() {
		onException(CreditException.class).process((exchange) -> {
			ExceptionResponse er = new ExceptionResponse();
			er.setTimestamp(OffsetDateTime.now());
			Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
			er.setMessage(cause.getMessage());
			exchange.getMessage().setBody(er);
		}).marshal().json().to("stream:out").setHeader("serviceType", constant("credit")).to(
				"kafka:BuyRealEstateFailTopic?brokers=" + realestateKafkaServer + "&groupId=" + realestateServiceType)
				.handled(true);
	}

	private void gateway() {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
				.dataFormatProperty("prettyPrint", "true").enableCORS(true).contextPath("/api")
				.apiContextPath("/api-doc").apiProperty("api.title", "Micro Real Estate buying API")
				.apiProperty("api.version", "1.0.0");

		rest("/buyRealEstate").description("Micro Real Estate buying REST service").consumes("application/json")
				.produces("application/json").post("/buying").description("Buy a real estate")
				.type(BuyRealEstateRequest.class).outType(ContractInfo.class).param().name("body").type(body)
				.description("The real estate to buy").endParam().responseMessage().code(200)
				.message("Real estate successfully bought").endResponseMessage().to("direct:buyRealEstate");

		from("direct:buyRealEstate").routeId("buyRealEstate").log("fired buyRealEstate").process((exchange) -> {
			exchange.getMessage().setHeader("contractId", contractIdentifierService.getContractIdentifier());
		}).to("direct:BuyRealEstateRequest").to("direct:buyRequester");

		from("direct:buyRequester").routeId("buyRequester").log("fired buyRequester").process((exchange) -> {
			exchange.getMessage().setBody(
					Utils.prepareBookingInfo(exchange.getMessage().getHeader("contractId", String.class), null));
		});

		from("direct:BuyRealEstateRequest").routeId("BuyRealEstateRequest").log("fired brokerTopic").marshal().json()
				.to("kafka:RealEstateReqTopic?brokers=" + realestateKafkaServer + "&groupId=" + realestateServiceType);
	}

	private void credit() {
		from("kafka:RealEstateReqTopic?brokers=" + realestateKafkaServer)
				.routeId("getCredit").log("fired getCredit").unmarshal()
				.json(JsonLibrary.Jackson, BuyRealEstateRequest.class).process((exchange) -> {
					String contractId = exchange.getMessage().getHeader("contractId", String.class);
					ProcessingState previousState = creditStateService.sendEvent(contractId, ProcessingEvent.START);
					if (previousState != ProcessingState.CANCELLED) {
						ContractInfo ci = new ContractInfo();
						ci.setId(contractIdentifierService.getContractIdentifier());
						BuyRealEstateRequest brer = exchange.getMessage().getBody(BuyRealEstateRequest.class);
						if (brer != null && brer.getCreditRecipientInfo() != null
								&& brer.getCreditRecipientInfo().getMainRecipientFirstName() != null) {
							String firstName = brer.getCreditRecipientInfo().getMainRecipientFirstName();
							if (firstName.length() < 3) {
								throw new CreditException("Invalid first name: " + firstName);
							} else if (firstName.equals("Dominik")) {
								ci.setDate(OffsetDateTime.now().plusDays(4));
							} else {
								ci.setDate(OffsetDateTime.now().plusDays(2));
							}
						} else {
							ci.setDate(OffsetDateTime.now());
						}
						exchange.getMessage().setBody(ci);
						previousState = creditStateService.sendEvent(contractId, ProcessingEvent.FINISH);
					}
				}).marshal().json().to("stream:out").choice()
				.when(header("previousState").isEqualTo(ProcessingState.CANCELLED))
				.to("direct:getCreditCompensationAction").otherwise().setHeader("serviceType", constant("credit"))
				.to("kafka:ContractInfoTopic?brokers=" + realestateKafkaServer)
				.endChoice();

		from("kafka:BuyRealEstateFailTopic?brokers=" + realestateKafkaServer)
				.routeId("getCreditCompensation").log("fired getCreditCompensation").unmarshal()
				.json(JsonLibrary.Jackson, ExceptionResponse.class).choice()
				.when(header("serviceType").isNotEqualTo("credit")).process((exchange) -> {
					String contractId = exchange.getMessage().getHeader("contractId", String.class);
					ProcessingState previousState = creditStateService.sendEvent(contractId, ProcessingEvent.CANCEL);
					exchange.getMessage().setHeader("previousState", previousState);
				}).choice().when(header("previousState").isEqualTo(ProcessingState.FINISHED))
				.to("direct:getCreditCompensationAction").endChoice().endChoice();
		
		from("direct:getCreditCompensationAction").routeId("getCreditCompensationAction")
				.log("fired getCreditCompensationAction").to("stream:out");
	}

	private void realestate() {
		from("kafka:RealEstateReqTopic?brokers=" + realestateKafkaServer)
				.routeId("bookRealEstate").log("fired bookRealEstate").unmarshal()
				.json(JsonLibrary.Jackson, BuyRealEstateRequest.class).process((exchange) -> {

					String contractId = exchange.getMessage().getHeader("contractId", String.class);
					ProcessingState previousState = realEstateStateService.sendEvent(contractId, ProcessingEvent.START);
					if (previousState != ProcessingState.CANCELLED) {
						ContractInfo ci = new ContractInfo();
						ci.setId(contractIdentifierService.getContractIdentifier());
						BuyRealEstateRequest brer = exchange.getMessage().getBody(BuyRealEstateRequest.class);

						if (brer != null && brer.getRealEstateInfo() != null
								&& brer.getRealEstateInfo().getNumberOfRooms() != null) {
							int numberOfRooms = brer.getRealEstateInfo().getNumberOfRooms();
							if (numberOfRooms >= 50) {
								throw new RealEstateException("Too many rooms: " + numberOfRooms);
							} else if (numberOfRooms > 3) {
								ci.setDate(OffsetDateTime.now().plusDays(5));
							} else {
								ci.setDate(OffsetDateTime.now().plusDays(3));
							}
						} else {
							ci.setDate(OffsetDateTime.now().plusDays(1));
						}

						exchange.getMessage().setBody(ci);
						previousState = realEstateStateService.sendEvent(contractId, ProcessingEvent.FINISH);
					}
					exchange.getMessage().setHeader("previousState", previousState);

				}).marshal().json().to("stream:out").choice()
				.when(header("previousState").isEqualTo(ProcessingState.CANCELLED))
				.to("direct:bookRealEstateCompensationAction").otherwise()
				.setHeader("serviceType", constant("realestate"))
				.to("kafka:ContractInfoTopic?brokers=" + realestateKafkaServer)
				.endChoice();

		from("kafka:BuyRealEstateFailTopic?brokers=" + realestateKafkaServer)
				.routeId("bookRealEstateCompensation").log("fired bookRealEstateCompensation").unmarshal()
				.json(JsonLibrary.Jackson, ExceptionResponse.class).choice()
				.when(header("serviceType").isNotEqualTo("realEstate")).process((exchange) -> {
					String contractId = exchange.getMessage().getHeader("contractId", String.class);
					ProcessingState previousState = realEstateStateService.sendEvent(contractId,
							ProcessingEvent.CANCEL);
					exchange.getMessage().setHeader("previousState", previousState);
				}).choice().when(header("previousState").isEqualTo(ProcessingState.FINISHED))
				.to("direct:bookRealEstateCompensationAction").endChoice().endChoice();

		from("direct:bookRealEstateCompensationAction").routeId("bookRealEstateCompensationAction")
				.log("fired bookRealEstateCompensationAction").to("stream:out");
	}

	private void signContract() {
		from("kafka:ContractInfoTopic?brokers=" + realestateKafkaServer + "&groupId=" + realestateServiceType)
				.routeId("signContractInfo").log("fired signContractInfo").unmarshal()
				.json(JsonLibrary.Jackson, ContractInfo.class).process((exchange) -> {
					String contractId = exchange.getMessage().getHeader("contractId", String.class);
					boolean isReady = paymentService.addBookingInfo(contractId,
							exchange.getMessage().getBody(ContractInfo.class),
							exchange.getMessage().getHeader("serviceType", String.class));
					exchange.getMessage().setHeader("isReady", isReady);
				}).choice().when(header("isReady").isEqualTo(true)).to("direct:finalizePayment").endChoice();

		from("kafka:RealEstateReqTopic?brokers=" + realestateKafkaServer + "&groupId=" + realestateServiceType)
				.routeId("signContractReq").log("fired signContractReq").unmarshal()
				.json(JsonLibrary.Jackson, BuyRealEstateRequest.class).process((exchange) -> {
					String contractId = exchange.getMessage().getHeader("contractId", String.class);
					boolean isReady = paymentService.addBuyRealEstateRequest(contractId,
							exchange.getMessage().getBody(BuyRealEstateRequest.class));
					exchange.getMessage().setHeader("isReady", isReady);
				}).choice().when(header("isReady").isEqualTo(true)).to("direct:finalizePayment").endChoice();

		from("direct:finalizePayment").routeId("finalizePayment").log("fired finalizePayment").process((exchange) -> {
			String contractId = exchange.getMessage().getHeader("contractId", String.class);
			PaymentService.PaymentData paymentData = paymentService.getPaymentData(contractId);
			OffsetDateTime creditDate = paymentData.creditContractInfo.getDate();
			OffsetDateTime realEstateDate = paymentData.realEstateContractInfo.getDate();
			OffsetDateTime finalDate = realEstateDate;
			if (creditDate.compareTo(realEstateDate) > 0) {
				finalDate = creditDate;
			}

			ContractInfo contractInfo = new ContractInfo();
			contractInfo.setId(contractId);
			contractInfo.setDate(finalDate);
			exchange.getMessage().setBody(contractInfo);

		}).to("direct:notification");

		from("direct:notification").routeId("notification").log("fired notification").to("stream:out");
	}
}
