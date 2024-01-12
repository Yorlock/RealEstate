package org.bp.signContract;
import java.math.BigDecimal;
import java.util.Date;

import org.bp.signContract.model.SignContractException;
import org.bp.signContract.model.SignContractRequest;
import org.bp.signContract.model.SignContractResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class SignContractService {
	
	
		@PostMapping("/signContract")
		@Operation(
			summary = "sign contract operation",
			description = "operation for signing contract",
			responses = {
				@ApiResponse(
					responseCode = "200", 
					description = "OK",
					content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = SignContractResponse.class))
					}
				),
				@ApiResponse(
					responseCode = "400", 
					description = "Bad Request",
					content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
					}
				)
			}
		)
		public SignContractResponse signContract(@RequestBody SignContractRequest signContractRequest) {
			if (signContractRequest!=null && signContractRequest.getFee() < 0) {
				throw new SignContractException("Fee value can not be negative");
			}
				
			SignContractResponse signContractResponse = new SignContractResponse();
			signContractResponse.setTransactionDate(new Date());
			signContractResponse.setTransactionId(999);
			return signContractResponse;
		}
		
		@GetMapping("signContract/{id}")
		public SignContractResponse getSignContract(@PathVariable int id) {
			SignContractResponse signContractResponse = new SignContractResponse();
			signContractResponse.setTransactionDate(new Date());
			signContractResponse.setTransactionId(id);
			
			return signContractResponse;
		}

}
