package org.bp.realEstate;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import static org.apache.camel.model.rest.RestParamType.body;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.bp.realEstate.model.BuyRealEstateRequest;
import org.bp.realEstate.model.ContractInfo;
import org.bp.realEstate.model.Utils;
import org.bp.realEstate.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.bp.realEstate.exceptions.RealEstateException;
import org.bp.realEstate.exceptions.CreditException;
import org.bp.realEstate.model.ExceptionResponse;
import org.bp.realEstate.state.ProcessingEvent;
import org.bp.realEstate.state.ProcessingState;
import org.bp.realEstate.state.StateService;

@Component
public class BuyRealEstateService extends RouteBuilder {

	@Autowired
	ContractIdentifierService contractIdentifierService;

	@Autowired
	PaymentService paymentService;

	@Autowired
	StateService realEstateStateService;

	@Autowired
	StateService creditStateService;

	@Override
	public void configure() throws Exception {
		realEstateExceptionHandlers();
		getCreditExceptionHandlers();
		gateway();
		credit();
		realState();
		signContract();
	}

	private void signContract() {
		from("kafka:ContractInfoTopic?brokers=localhost:9092").routeId("paymentContractInfo")
				.log("fired paymentContractInfo").unmarshal().json(JsonLibrary.Jackson, ContractInfo.class)
				.process((exchange) -> {
					String contractId = exchange.getMessage().getHeader("contractId", String.class);
					boolean isReady = paymentService.addBookingInfo(contractId,
							exchange.getMessage().getBody(ContractInfo.class),
							exchange.getMessage().getHeader("serviceType", String.class));
					exchange.getMessage().setHeader("isReady", isReady);
				}).choice().when(header("isReady").isEqualTo(true)).to("direct:finalizePayment").endChoice();

		from("kafka:RealEstateReqTopic?brokers=localhost:9092").routeId("signContractReq").log("fired signContractReq")
				.unmarshal().json(JsonLibrary.Jackson, BuyRealEstateRequest.class).process((exchange) -> {
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

	private void realState() {
		from("kafka:RealEstateReqTopic?brokers=localhost:9092").routeId("bookRealEstate").log("fired bookRealEstate")
				.unmarshal().json(JsonLibrary.Jackson, BuyRealEstateRequest.class).process((exchange) -> {

					String contractId = exchange.getMessage().getHeader("contractId", String.class);
					ProcessingState previousState = realEstateStateService.sendEvent(contractId, ProcessingEvent.START);
					if (previousState != ProcessingState.CANCELLED) {
						ContractInfo ci = new ContractInfo();
						ci.setId(contractIdentifierService.getContractIdentifier());
						BuyRealEstateRequest brer = exchange.getMessage().getBody(BuyRealEstateRequest.class);

						if (brer != null && brer.getRealEstateInfo() != null
								&& brer.getRealEstateInfo().getNumberOfRooms() != null) {
							int numberOfRooms = brer.getRealEstateInfo().getNumberOfRooms();
							if (numberOfRooms > 3 && numberOfRooms < 99) {
								ci.setDate(OffsetDateTime.now().plusMinutes(50000));
							} else if (numberOfRooms > 100) {
								throw new RealEstateException("Too many rooms: " + numberOfRooms);
							} else {
								ci.setDate(OffsetDateTime.now().plusMinutes(100));
							}
						}
						exchange.getMessage().setBody(ci);
						previousState = realEstateStateService.sendEvent(contractId, ProcessingEvent.FINISH);
					}
					exchange.getMessage().setHeader("previousState", previousState);

				}).marshal().json().to("stream:out").choice()
				.when(header("previousState").isEqualTo(ProcessingState.CANCELLED))
				.to("direct:bookFlightCompensationAction").otherwise().setHeader("serviceType", constant("flight"))
				.to("kafka:BookingInfoTopic?brokers=localhost:9092").endChoice();

		from("kafka:BuyRealEstateFailTopic?brokers=localhost:9092").routeId("bookRealEstateCompensation")
				.log("fired bookRealEstateCompensation").unmarshal().json(JsonLibrary.Jackson, ExceptionResponse.class)
				.choice().when(header("serviceType").isNotEqualTo("realEstate")).process((exchange) -> {
					String contractId = exchange.getMessage().getHeader("contractId", String.class);
					ProcessingState previousState = realEstateStateService.sendEvent(contractId,
							ProcessingEvent.CANCEL);
					exchange.getMessage().setHeader("previousState", previousState);
				}).choice().when(header("previousState").isEqualTo(ProcessingState.FINISHED))
				.to("direct:bookRealEstateCompensationAction").endChoice().endChoice();

		from("direct:bookRealEstateCompensationAction").routeId("bookRealEstateCompensationAction")
				.log("fired bookRealEstateCompensationAction").to("stream:out");
	}

	private void credit() {
		from("kafka:RealEstateReqTopic?brokers=localhost:9092").routeId("getCredit").log("fired getCredit").unmarshal()
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
							if (firstName.equals("Dominik")) {
								ci.setDate(OffsetDateTime.now());
							} else if (firstName.length() < 3) {
								throw new CreditException("Invalid first name: " + firstName);
							} else {
								ci.setDate(OffsetDateTime.now().plusMinutes(10000));
							}
						}
						exchange.getMessage().setBody(ci);
						previousState = creditStateService.sendEvent(contractId, ProcessingEvent.FINISH);
					}
				}).marshal().json().to("stream:out").choice()
				.when(header("previousState").isEqualTo(ProcessingState.CANCELLED))
				.to("direct:bookHotelCompensationAction").otherwise().setHeader("serviceType", constant("credit"))
				.to("kafka:ContractInfoTopic?brokers=localhost:9092").endChoice();

		from("kafka:BuyRealEstateFailTopic?brokers=localhost:9092").routeId("getCreditCompensation")
				.log("fired getCreditCompensation").unmarshal().json(JsonLibrary.Jackson, ExceptionResponse.class)
				.choice().when(header("serviceType").isNotEqualTo("credit")).process((exchange) -> {
					String contractId = exchange.getMessage().getHeader("contractId", String.class);
					ProcessingState previousState = creditStateService.sendEvent(contractId, ProcessingEvent.CANCEL);
					exchange.getMessage().setHeader("previousState", previousState);
				}).choice().when(header("previousState").isEqualTo(ProcessingState.FINISHED))
				.to("direct:getCreditCompensationAction").endChoice().endChoice();
		from("direct:getCreditCompensationAction").routeId("getCreditCompensationAction")
				.log("fired getCreditCompensationAction").to("stream:out");
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

		from("direct:buyRealEstate").routeId("buyRealEstate").log("buyRealEstate fired").process((exchange) -> {
			exchange.getMessage().setHeader("buyingRealEstateId", contractIdentifierService.getContractIdentifier());
		}).to("direct:BuyRealEstateRequest").to("direct:buyRequester");

		from("direct:buyRequester").routeId("buyRequester").log("buyRequester fired").process((exchange) -> {
			exchange.getMessage().setBody(Utils
					.prepareBookingInfo(exchange.getMessage().getHeader("buyingRealEstateId", String.class), null));
		});

		from("direct:BuyRealEstateRequest").routeId("BuyRealEstateRequest").log("brokerTopic fired").marshal().json()
				.to("kafka:RealEstateReqTopic?brokers=localhost:9092");
	}

	private void realEstateExceptionHandlers() {
		onException(RealEstateException.class).process((exchange) -> {
			ExceptionResponse er = new ExceptionResponse();
			er.setTimestamp(OffsetDateTime.now());
			Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
			er.setMessage(cause.getMessage());
			exchange.getMessage().setBody(er);
		}).marshal().json().to("stream:out").setHeader("serviceType", constant("flight"))
				.to("kafka:BuyRealEstateFailTopic?brokers=localhost:9092").handled(true);
	}

	private void getCreditExceptionHandlers() {
		onException(CreditException.class).process((exchange) -> {
			ExceptionResponse er = new ExceptionResponse();
			er.setTimestamp(OffsetDateTime.now());
			Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
			er.setMessage(cause.getMessage());
			exchange.getMessage().setBody(er);
		}).marshal().json().to("stream:out").setHeader("serviceType", constant("credit"))
				.to("kafka:BuyRealEstateFailTopic?brokers=localhost:9092").handled(true);
	}
}
