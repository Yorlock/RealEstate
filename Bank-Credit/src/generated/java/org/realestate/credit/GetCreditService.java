package org.realestate.credit;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.3.2
 * 2024-01-11T19:11:49.645+01:00
 * Generated source version: 3.3.2
 *
 */
@WebServiceClient(name = "GetCreditService",
                  wsdlLocation = "file:/home/aznu/eclipse-workspace/RealEstate/Bank-Credit/src/main/resources/credit.wsdl",
                  targetNamespace = "http://www.realestate.org/credit/")
public class GetCreditService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.realestate.org/credit/", "GetCreditService");
    public final static QName GetCreditPort = new QName("http://www.realestate.org/credit/", "GetCreditPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/home/aznu/eclipse-workspace/RealEstate/Bank-Credit/src/main/resources/credit.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(GetCreditService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/home/aznu/eclipse-workspace/RealEstate/Bank-Credit/src/main/resources/credit.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public GetCreditService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public GetCreditService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GetCreditService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public GetCreditService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public GetCreditService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public GetCreditService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns GetCredit
     */
    @WebEndpoint(name = "GetCreditPort")
    public GetCredit getGetCreditPort() {
        return super.getPort(GetCreditPort, GetCredit.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GetCredit
     */
    @WebEndpoint(name = "GetCreditPort")
    public GetCredit getGetCreditPort(WebServiceFeature... features) {
        return super.getPort(GetCreditPort, GetCredit.class, features);
    }

}