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
/**
 * RealEstateInfo
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-01-12T13:34:51.739596669+01:00[Europe/Warsaw]")
public class RealEstateInfo {
  @JsonProperty("price")
  private Integer price = null;

  @JsonProperty("yearBuilt")
  private Integer yearBuilt = null;

  @JsonProperty("averageRentPrice")
  private Integer averageRentPrice = null;

  @JsonProperty("apartmentSizeInSquareMeters")
  private Float apartmentSizeInSquareMeters = null;

  @JsonProperty("numberOfRooms")
  private Integer numberOfRooms = null;

  public RealEstateInfo price(Integer price) {
    this.price = price;
    return this;
  }

   /**
   * Get price
   * @return price
  **/
  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public RealEstateInfo yearBuilt(Integer yearBuilt) {
    this.yearBuilt = yearBuilt;
    return this;
  }

   /**
   * Get yearBuilt
   * @return yearBuilt
  **/
  public Integer getYearBuilt() {
    return yearBuilt;
  }

  public void setYearBuilt(Integer yearBuilt) {
    this.yearBuilt = yearBuilt;
  }

  public RealEstateInfo averageRentPrice(Integer averageRentPrice) {
    this.averageRentPrice = averageRentPrice;
    return this;
  }

   /**
   * Get averageRentPrice
   * @return averageRentPrice
  **/
  public Integer getAverageRentPrice() {
    return averageRentPrice;
  }

  public void setAverageRentPrice(Integer averageRentPrice) {
    this.averageRentPrice = averageRentPrice;
  }

  public RealEstateInfo apartmentSizeInSquareMeters(Float apartmentSizeInSquareMeters) {
    this.apartmentSizeInSquareMeters = apartmentSizeInSquareMeters;
    return this;
  }

   /**
   * Get apartmentSizeInSquareMeters
   * @return apartmentSizeInSquareMeters
  **/
  public Float getApartmentSizeInSquareMeters() {
    return apartmentSizeInSquareMeters;
  }

  public void setApartmentSizeInSquareMeters(Float apartmentSizeInSquareMeters) {
    this.apartmentSizeInSquareMeters = apartmentSizeInSquareMeters;
  }

  public RealEstateInfo numberOfRooms(Integer numberOfRooms) {
    this.numberOfRooms = numberOfRooms;
    return this;
  }

   /**
   * Get numberOfRooms
   * @return numberOfRooms
  **/
  public Integer getNumberOfRooms() {
    return numberOfRooms;
  }

  public void setNumberOfRooms(Integer numberOfRooms) {
    this.numberOfRooms = numberOfRooms;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RealEstateInfo realEstateInfo = (RealEstateInfo) o;
    return Objects.equals(this.price, realEstateInfo.price) &&
        Objects.equals(this.yearBuilt, realEstateInfo.yearBuilt) &&
        Objects.equals(this.averageRentPrice, realEstateInfo.averageRentPrice) &&
        Objects.equals(this.apartmentSizeInSquareMeters, realEstateInfo.apartmentSizeInSquareMeters) &&
        Objects.equals(this.numberOfRooms, realEstateInfo.numberOfRooms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(price, yearBuilt, averageRentPrice, apartmentSizeInSquareMeters, numberOfRooms);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RealEstateInfo {\n");
    
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    yearBuilt: ").append(toIndentedString(yearBuilt)).append("\n");
    sb.append("    averageRentPrice: ").append(toIndentedString(averageRentPrice)).append("\n");
    sb.append("    apartmentSizeInSquareMeters: ").append(toIndentedString(apartmentSizeInSquareMeters)).append("\n");
    sb.append("    numberOfRooms: ").append(toIndentedString(numberOfRooms)).append("\n");
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
