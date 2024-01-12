/*
 * Buying RealEstate mircro service
 * Micro service to buy a real estate and get credit
 *
 * OpenAPI spec version: 1.0.0
 * Contact: supportm@bp.org
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package org.bp.realEstate.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.bp.realEstate.model.CreditInfo;
import org.bp.realEstate.model.CreditRecipientInfo;
import org.bp.realEstate.model.PaymentCard;
import org.bp.realEstate.model.RealEstateInfo;
/**
 * BuyRealEstateRequest
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-01-12T13:34:51.739596669+01:00[Europe/Warsaw]")
public class BuyRealEstateRequest {
  @JsonProperty("realEstateInfo")
  private RealEstateInfo realEstateInfo = null;

  @JsonProperty("creditRecipientInfo")
  private CreditRecipientInfo creditRecipientInfo = null;

  @JsonProperty("creditInfo")
  private CreditInfo creditInfo = null;

  @JsonProperty("paymentCard")
  private PaymentCard paymentCard = null;

  @JsonProperty("fee")
  private Integer fee = null;

  @JsonProperty("address")
  private String address = null;

  public BuyRealEstateRequest realEstateInfo(RealEstateInfo realEstateInfo) {
    this.realEstateInfo = realEstateInfo;
    return this;
  }

   /**
   * Get realEstateInfo
   * @return realEstateInfo
  **/
  public RealEstateInfo getRealEstateInfo() {
    return realEstateInfo;
  }

  public void setRealEstateInfo(RealEstateInfo realEstateInfo) {
    this.realEstateInfo = realEstateInfo;
  }

  public BuyRealEstateRequest creditRecipientInfo(CreditRecipientInfo creditRecipientInfo) {
    this.creditRecipientInfo = creditRecipientInfo;
    return this;
  }

   /**
   * Get creditRecipientInfo
   * @return creditRecipientInfo
  **/
  public CreditRecipientInfo getCreditRecipientInfo() {
    return creditRecipientInfo;
  }

  public void setCreditRecipientInfo(CreditRecipientInfo creditRecipientInfo) {
    this.creditRecipientInfo = creditRecipientInfo;
  }

  public BuyRealEstateRequest creditInfo(CreditInfo creditInfo) {
    this.creditInfo = creditInfo;
    return this;
  }

   /**
   * Get creditInfo
   * @return creditInfo
  **/
  public CreditInfo getCreditInfo() {
    return creditInfo;
  }

  public void setCreditInfo(CreditInfo creditInfo) {
    this.creditInfo = creditInfo;
  }

  public BuyRealEstateRequest paymentCard(PaymentCard paymentCard) {
    this.paymentCard = paymentCard;
    return this;
  }

   /**
   * Get paymentCard
   * @return paymentCard
  **/
  public PaymentCard getPaymentCard() {
    return paymentCard;
  }

  public void setPaymentCard(PaymentCard paymentCard) {
    this.paymentCard = paymentCard;
  }

  public BuyRealEstateRequest fee(Integer fee) {
    this.fee = fee;
    return this;
  }

   /**
   * Get fee
   * @return fee
  **/
  public Integer getFee() {
    return fee;
  }

  public void setFee(Integer fee) {
    this.fee = fee;
  }

  public BuyRealEstateRequest address(String address) {
    this.address = address;
    return this;
  }

   /**
   * Get address
   * @return address
  **/
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BuyRealEstateRequest buyRealEstateRequest = (BuyRealEstateRequest) o;
    return Objects.equals(this.realEstateInfo, buyRealEstateRequest.realEstateInfo) &&
        Objects.equals(this.creditRecipientInfo, buyRealEstateRequest.creditRecipientInfo) &&
        Objects.equals(this.creditInfo, buyRealEstateRequest.creditInfo) &&
        Objects.equals(this.paymentCard, buyRealEstateRequest.paymentCard) &&
        Objects.equals(this.fee, buyRealEstateRequest.fee) &&
        Objects.equals(this.address, buyRealEstateRequest.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(realEstateInfo, creditRecipientInfo, creditInfo, paymentCard, fee, address);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BuyRealEstateRequest {\n");
    
    sb.append("    realEstateInfo: ").append(toIndentedString(realEstateInfo)).append("\n");
    sb.append("    creditRecipientInfo: ").append(toIndentedString(creditRecipientInfo)).append("\n");
    sb.append("    creditInfo: ").append(toIndentedString(creditInfo)).append("\n");
    sb.append("    paymentCard: ").append(toIndentedString(paymentCard)).append("\n");
    sb.append("    fee: ").append(toIndentedString(fee)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
