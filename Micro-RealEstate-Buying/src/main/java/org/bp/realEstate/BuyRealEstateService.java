package org.bp.realEstate;

import org.apache.camel.builder.RouteBuilder;
import static org.apache.camel.model.rest.RestParamType.body;
import java.math.BigDecimal;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.bp.realEstate.model.BuyRealEstateRequest;
import org.bp.realEstate.model.ContractInfo;
import org.bp.realEstate.model.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuyRealEstateService extends RouteBuilder {

	@Autowired
	ContractIdentifierService contractIdentifierService;

	@Override
	public void configure() throws Exception {
		gateway();
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
		}) /*.to("direct:TravelBookRequest")*/ .to("direct:buyRequester");

		from("direct:buyRequester").routeId("buyRequester").log("buyRequester fired").process((exchange) -> {
			exchange.getMessage().setBody(
					Utils.prepareBookingInfo(exchange.getMessage().getHeader("buyingRealEstateId", String.class), null));
		});

	}

}
