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
/**
 * GetCreditRequest
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-01-12T13:34:51.739596669+01:00[Europe/Warsaw]")
public class GetCreditRequest {
  @JsonProperty("creditRecipientInfo")
  private CreditRecipientInfo creditRecipientInfo = null;

  @JsonProperty("creditInfo")
  private CreditInfo creditInfo = null;

  public GetCreditRequest creditRecipientInfo(CreditRecipientInfo creditRecipientInfo) {
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

  public GetCreditRequest creditInfo(CreditInfo creditInfo) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCreditRequest getCreditRequest = (GetCreditRequest) o;
    return Objects.equals(this.creditRecipientInfo, getCreditRequest.creditRecipientInfo) &&
        Objects.equals(this.creditInfo, getCreditRequest.creditInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creditRecipientInfo, creditInfo);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetCreditRequest {\n");
    
    sb.append("    creditRecipientInfo: ").append(toIndentedString(creditRecipientInfo)).append("\n");
    sb.append("    creditInfo: ").append(toIndentedString(creditInfo)).append("\n");
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
