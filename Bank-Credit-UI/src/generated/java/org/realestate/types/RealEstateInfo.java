
package org.realestate.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RealEstateInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RealEstateInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="yearBuilt" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="averageRentPrice" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="apartmentSizeInSquareMeters" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="numberOfRooms" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RealEstateInfo", propOrder = {
    "price",
    "yearBuilt",
    "averageRentPrice",
    "apartmentSizeInSquareMeters",
    "numberOfRooms"
})
public class RealEstateInfo {

    protected int price;
    protected int yearBuilt;
    protected int averageRentPrice;
    protected float apartmentSizeInSquareMeters;
    protected int numberOfRooms;

    /**
     * Gets the value of the price property.
     * 
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(int value) {
        this.price = value;
    }

    /**
     * Gets the value of the yearBuilt property.
     * 
     */
    public int getYearBuilt() {
        return yearBuilt;
    }

    /**
     * Sets the value of the yearBuilt property.
     * 
     */
    public void setYearBuilt(int value) {
        this.yearBuilt = value;
    }

    /**
     * Gets the value of the averageRentPrice property.
     * 
     */
    public int getAverageRentPrice() {
        return averageRentPrice;
    }

    /**
     * Sets the value of the averageRentPrice property.
     * 
     */
    public void setAverageRentPrice(int value) {
        this.averageRentPrice = value;
    }

    /**
     * Gets the value of the apartmentSizeInSquareMeters property.
     * 
     */
    public float getApartmentSizeInSquareMeters() {
        return apartmentSizeInSquareMeters;
    }

    /**
     * Sets the value of the apartmentSizeInSquareMeters property.
     * 
     */
    public void setApartmentSizeInSquareMeters(float value) {
        this.apartmentSizeInSquareMeters = value;
    }

    /**
     * Gets the value of the numberOfRooms property.
     * 
     */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Sets the value of the numberOfRooms property.
     * 
     */
    public void setNumberOfRooms(int value) {
        this.numberOfRooms = value;
    }

}
