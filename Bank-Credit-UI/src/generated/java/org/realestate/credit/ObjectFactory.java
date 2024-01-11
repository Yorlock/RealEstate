
package org.realestate.credit;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.realestate.credit package. 
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

    private final static QName _DatatypeConfigurationException_QNAME = new QName("http://www.realestate.org/credit/", "DatatypeConfigurationException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.realestate.credit
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DatatypeConfigurationException }
     * 
     */
    public DatatypeConfigurationException createDatatypeConfigurationException() {
        return new DatatypeConfigurationException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatatypeConfigurationException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DatatypeConfigurationException }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.realestate.org/credit/", name = "DatatypeConfigurationException")
    public JAXBElement<DatatypeConfigurationException> createDatatypeConfigurationException(DatatypeConfigurationException value) {
        return new JAXBElement<DatatypeConfigurationException>(_DatatypeConfigurationException_QNAME, DatatypeConfigurationException.class, null, value);
    }

}
