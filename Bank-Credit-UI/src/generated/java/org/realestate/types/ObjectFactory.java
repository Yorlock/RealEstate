
package org.realestate.types;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.realestate.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.realestate.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ContractInfo }
     * 
     */
    public ContractInfo createContractInfo() {
        return new ContractInfo();
    }

    /**
     * Create an instance of {@link Fault }
     * 
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link CreditRecipientInfo }
     * 
     */
    public CreditRecipientInfo createCreditRecipientInfo() {
        return new CreditRecipientInfo();
    }

    /**
     * Create an instance of {@link CreditInfo }
     * 
     */
    public CreditInfo createCreditInfo() {
        return new CreditInfo();
    }

    /**
     * Create an instance of {@link RealEstateInfo }
     * 
     */
    public RealEstateInfo createRealEstateInfo() {
        return new RealEstateInfo();
    }

}
