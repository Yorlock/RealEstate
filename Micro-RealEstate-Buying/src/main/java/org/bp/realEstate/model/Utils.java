package org.bp.realEstate.model;

import java.time.OffsetDateTime;

public class Utils {
	static public ContractInfo prepareBookingInfo(String contractId, OffsetDateTime date) {
		ContractInfo contractInfo = new ContractInfo();
		contractInfo.setId(contractId);
		contractInfo.setDate(date);
		return contractInfo;
	}

}
