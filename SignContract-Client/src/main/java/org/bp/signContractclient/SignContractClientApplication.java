package org.bp.signContractclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import org.bp.signContract.model.PaymentCard;
import org.bp.signContract.model.SignContractRequest;
import org.bp.signContract.model.SignContractResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SignContractClientApplication {

	private static final Logger log = LoggerFactory.getLogger(SignContractClientApplication.class);
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			SignContractRequest scr = new SignContractRequest();
			PaymentCard pc = new PaymentCard();
			scr.setPaymentCard(pc);
			pc.setName("DL"); 
			pc.setNumber("99999"); 
			pc.setValidTo("10/50");
			
			scr.setFee(-100);
			scr.setAddress("Piotrowo 2, 60-965 Poznań");
			
			try {
				ResponseEntity<SignContractResponse> re = restTemplate.postForEntity(
					"http://localhost:8083/signContract", 
					scr, 
					SignContractResponse.class
				);
				log.info(re.getBody().getTransactionDate().toString() + " -> " + Integer.toString(re.getBody().getTransactionId()));
			}
			catch (HttpClientErrorException e) { //catch 4xx response codes
				log.error(e.getResponseBodyAsString());
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SignContractClientApplication.class, args);
	}

}