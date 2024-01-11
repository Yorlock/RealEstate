
package org.realestate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.realestate.types.CreditInfo;
import org.realestate.types.CreditRecipientInfo;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="creditRecipient" type="{http://www.realestate.org/types}CreditRecipientInfo"/&gt;
 *         &lt;element name="creditInfo" type="{http://www.realestate.org/types}CreditInfo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "creditRecipient",
    "creditInfo"
})
@XmlRootElement(name = "getCreditRequest")
public class GetCreditRequest {

    @XmlElement(required = true)
    protected CreditRecipientInfo creditRecipient;
    @XmlElement(required = true)
    protected CreditInfo creditInfo;

    /**
     * Gets the value of the creditRecipient property.
     * 
     * @return
     *     possible object is
     *     {@link CreditRecipientInfo }
     *     
     */
    public CreditRecipientInfo getCreditRecipient() {
        return creditRecipient;
    }

    /**
     * Sets the value of the creditRecipient property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditRecipientInfo }
     *     
     */
    public void setCreditRecipient(CreditRecipientInfo value) {
        this.creditRecipient = value;
    }

    /**
     * Gets the value of the creditInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CreditInfo }
     *     
     */
    public CreditInfo getCreditInfo() {
        return creditInfo;
    }

    /**
     * Sets the value of the creditInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditInfo }
     *     
     */
    public void setCreditInfo(CreditInfo value) {
        this.creditInfo = value;
    }

}
