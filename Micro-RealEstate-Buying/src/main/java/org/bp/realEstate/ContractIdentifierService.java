package org.bp.realEstate;


import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ContractIdentifierService {
	
	public String getContractIdentifier() {
		return UUID.randomUUID().toString();
	}
	

}
