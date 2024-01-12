
package org.realestate.types;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CreditInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lendingRateInPercent" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="howManyMonths" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="monthPayment" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="creditVolume" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="firstPaymentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditInfo", propOrder = {
    "lendingRateInPercent",
    "howManyMonths",
    "monthPayment",
    "creditVolume",
    "firstPaymentDate"
})
public class CreditInfo {

    protected int lendingRateInPercent;
    protected int howManyMonths;
    protected int monthPayment;
    protected int creditVolume;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar firstPaymentDate;

    /**
     * Gets the value of the lendingRateInPercent property.
     * 
     */
    public int getLendingRateInPercent() {
        return lendingRateInPercent;
    }

    /**
     * Sets the value of the lendingRateInPercent property.
     * 
     */
    public void setLendingRateInPercent(int value) {
        this.lendingRateInPercent = value;
    }

    /**
     * Gets the value of the howManyMonths property.
     * 
     */
    public int getHowManyMonths() {
        return howManyMonths;
    }

    /**
     * Sets the value of the howManyMonths property.
     * 
     */
    public void setHowManyMonths(int value) {
        this.howManyMonths = value;
    }

    /**
     * Gets the value of the monthPayment property.
     * 
     */
    public int getMonthPayment() {
        return monthPayment;
    }

    /**
     * Sets the value of the monthPayment property.
     * 
     */
    public void setMonthPayment(int value) {
        this.monthPayment = value;
    }

    /**
     * Gets the value of the creditVolume property.
     * 
     */
    public int getCreditVolume() {
        return creditVolume;
    }

    /**
     * Sets the value of the creditVolume property.
     * 
     */
    public void setCreditVolume(int value) {
        this.creditVolume = value;
    }

    /**
     * Gets the value of the firstPaymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstPaymentDate() {
        return firstPaymentDate;
    }

    /**
     * Sets the value of the firstPaymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstPaymentDate(XMLGregorianCalendar value) {
		try {
			this.firstPaymentDate = value;
		} catch (Exception e) {
			this.firstPaymentDate = null;
		}
	}

	public void setFirstPaymentDate(String value) {

		GregorianCalendar gc;
		XMLGregorianCalendar c = null;
		try {
			DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
			Date date = df.parse(value);

			gc = new GregorianCalendar();
			gc.setTime(date);
			c = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (Exception e) {
			c = null;
		}
		this.firstPaymentDate = c;
	}

}
