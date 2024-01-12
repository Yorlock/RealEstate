package org.bp.signContract.model;

public class SignContractRequest {
	private PaymentCard paymentCard;
	private int fee;
	private String address;
	public PaymentCard getPaymentCard() {
		return paymentCard;
	}
	public void setPaymentCard(PaymentCard paymentCard) {
		this.paymentCard = paymentCard;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
