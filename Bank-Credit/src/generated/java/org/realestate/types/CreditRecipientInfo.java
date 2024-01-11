
package org.realestate.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreditRecipientInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditRecipientInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numberOfCreditRecipient" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="sumIncomeYear" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="NumberCurrentlyOwningProperties" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="sumSpendingYear" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="sumChildren" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="mainRecipientFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="mainRecipientLastName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="mainRecipientTelefonNumber" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="mainRecipientPesel" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditRecipientInfo", propOrder = {
    "numberOfCreditRecipient",
    "sumIncomeYear",
    "numberCurrentlyOwningProperties",
    "sumSpendingYear",
    "sumChildren",
    "mainRecipientFirstName",
    "mainRecipientLastName",
    "mainRecipientTelefonNumber",
    "mainRecipientPesel"
})
public class CreditRecipientInfo {

    protected int numberOfCreditRecipient;
    protected int sumIncomeYear;
    @XmlElement(name = "NumberCurrentlyOwningProperties")
    protected int numberCurrentlyOwningProperties;
    protected int sumSpendingYear;
    protected int sumChildren;
    @XmlElement(required = true)
    protected String mainRecipientFirstName;
    @XmlElement(required = true)
    protected String mainRecipientLastName;
    protected int mainRecipientTelefonNumber;
    protected int mainRecipientPesel;

    /**
     * Gets the value of the numberOfCreditRecipient property.
     * 
     */
    public int getNumberOfCreditRecipient() {
        return numberOfCreditRecipient;
    }

    /**
     * Sets the value of the numberOfCreditRecipient property.
     * 
     */
    public void setNumberOfCreditRecipient(int value) {
        this.numberOfCreditRecipient = value;
    }

    /**
     * Gets the value of the sumIncomeYear property.
     * 
     */
    public int getSumIncomeYear() {
        return sumIncomeYear;
    }

    /**
     * Sets the value of the sumIncomeYear property.
     * 
     */
    public void setSumIncomeYear(int value) {
        this.sumIncomeYear = value;
    }

    /**
     * Gets the value of the numberCurrentlyOwningProperties property.
     * 
     */
    public int getNumberCurrentlyOwningProperties() {
        return numberCurrentlyOwningProperties;
    }

    /**
     * Sets the value of the numberCurrentlyOwningProperties property.
     * 
     */
    public void setNumberCurrentlyOwningProperties(int value) {
        this.numberCurrentlyOwningProperties = value;
    }

    /**
     * Gets the value of the sumSpendingYear property.
     * 
     */
    public int getSumSpendingYear() {
        return sumSpendingYear;
    }

    /**
     * Sets the value of the sumSpendingYear property.
     * 
     */
    public void setSumSpendingYear(int value) {
        this.sumSpendingYear = value;
    }

    /**
     * Gets the value of the sumChildren property.
     * 
     */
    public int getSumChildren() {
        return sumChildren;
    }

    /**
     * Sets the value of the sumChildren property.
     * 
     */
    public void setSumChildren(int value) {
        this.sumChildren = value;
    }

    /**
     * Gets the value of the mainRecipientFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainRecipientFirstName() {
        return mainRecipientFirstName;
    }

    /**
     * Sets the value of the mainRecipientFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainRecipientFirstName(String value) {
        this.mainRecipientFirstName = value;
    }

    /**
     * Gets the value of the mainRecipientLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainRecipientLastName() {
        return mainRecipientLastName;
    }

    /**
     * Sets the value of the mainRecipientLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainRecipientLastName(String value) {
        this.mainRecipientLastName = value;
    }

    /**
     * Gets the value of the mainRecipientTelefonNumber property.
     * 
     */
    public int getMainRecipientTelefonNumber() {
        return mainRecipientTelefonNumber;
    }

    /**
     * Sets the value of the mainRecipientTelefonNumber property.
     * 
     */
    public void setMainRecipientTelefonNumber(int value) {
        this.mainRecipientTelefonNumber = value;
    }

    /**
     * Gets the value of the mainRecipientPesel property.
     * 
     */
    public int getMainRecipientPesel() {
        return mainRecipientPesel;
    }

    /**
     * Sets the value of the mainRecipientPesel property.
     * 
     */
    public void setMainRecipientPesel(int value) {
        this.mainRecipientPesel = value;
    }

}
