
package org.realestate;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.realestate.types.ContractInfo;
import org.realestate.types.Fault;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.realestate package. 
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

    private final static QName _GetCreditResponse_QNAME = new QName("http://www.realestate.org", "getCreditResponse");
    private final static QName _CancelCreditResponse_QNAME = new QName("http://www.realestate.org", "cancelCreditResponse");
    private final static QName _CreditFault_QNAME = new QName("http://www.realestate.org", "creditFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.realestate
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCreditRequest }
     * 
     */
    public GetCreditRequest createGetCreditRequest() {
        return new GetCreditRequest();
    }

    /**
     * Create an instance of {@link CancelCreditRequest }
     * 
     */
    public CancelCreditRequest createCancelCreditRequest() {
        return new CancelCreditRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContractInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ContractInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.realestate.org", name = "getCreditResponse")
    public JAXBElement<ContractInfo> createGetCreditResponse(ContractInfo value) {
        return new JAXBElement<ContractInfo>(_GetCreditResponse_QNAME, ContractInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContractInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ContractInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.realestate.org", name = "cancelCreditResponse")
    public JAXBElement<ContractInfo> createCancelCreditResponse(ContractInfo value) {
        return new JAXBElement<ContractInfo>(_CancelCreditResponse_QNAME, ContractInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Fault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Fault }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.realestate.org", name = "creditFault")
    public JAXBElement<Fault> createCreditFault(Fault value) {
        return new JAXBElement<Fault>(_CreditFault_QNAME, Fault.class, null, value);
    }

}
