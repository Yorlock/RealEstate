
package org.bp.types;

import javax.xml.datatype.XMLGregorianCalendar;

public class CreditInfo {

    protected int lendingRateInPercent;
    protected int howManyMonths;
    protected int monthPayment;
    protected int creditVolume;
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
        this.firstPaymentDate = value;
    }

}
