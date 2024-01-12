package org.bp.realEstate;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.bp.realEstate.model.BuyRealEstateRequest;
import org.bp.realEstate.model.ContractInfo;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	private HashMap<String, PaymentData> payments;

	@PostConstruct
	void init() {
		payments = new HashMap<>();
	}

	public static class PaymentData {
		BuyRealEstateRequest buyRealEstateRequest;
		ContractInfo realEstateContractInfo;
		ContractInfo creditContractInfo;

		public boolean isReady() {
			return buyRealEstateRequest != null && realEstateContractInfo != null && creditContractInfo != null;
		}
	}

	public synchronized boolean addBuyRealEstateRequest(String contractId, BuyRealEstateRequest buyRealEstateRequest) {
		PaymentData paymentData = getPaymentData(contractId);
		paymentData.buyRealEstateRequest = buyRealEstateRequest;
		return paymentData.isReady();
	}

	public synchronized boolean addBookingInfo(String contractId, ContractInfo contractInfo, String serviceType) {
		PaymentData paymentData = getPaymentData(contractId);
		if (serviceType.equals("credit"))
			paymentData.creditContractInfo = contractInfo;
		else
			paymentData.realEstateContractInfo = contractInfo;
		return paymentData.isReady();
	}

	public synchronized PaymentData getPaymentData(String contractId) {
		PaymentData paymentData = payments.get(contractId);
		if (paymentData == null) {
			paymentData = new PaymentData();
			payments.put(contractId, paymentData);
		}
		return paymentData;
	}

}
