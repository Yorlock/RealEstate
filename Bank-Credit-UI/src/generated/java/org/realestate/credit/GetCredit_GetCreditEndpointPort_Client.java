
package org.realestate.credit;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import org.bp.credit.GetCreditEndpointService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.3.2
 * 2024-01-11T21:57:31.028+01:00
 * Generated source version: 3.3.2
 *
 */
public final class GetCredit_GetCreditEndpointPort_Client {

    private static final QName SERVICE_NAME = new QName("http://credit.bp.org/", "GetCreditEndpointService");

    private GetCredit_GetCreditEndpointPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = GetCreditEndpointService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        GetCreditEndpointService ss = new GetCreditEndpointService(wsdlURL, SERVICE_NAME);
        GetCredit port = ss.getGetCreditEndpointPort();

        {
        System.out.println("Invoking cancelCredit...");
        org.realestate.CancelCreditRequest _cancelCredit_payload = null;
        try {
            org.realestate.types.ContractInfo _cancelCredit__return = port.cancelCredit(_cancelCredit_payload);
            System.out.println("cancelCredit.result=" + _cancelCredit__return);

        } catch (CreditFaultMsg e) {
            System.out.println("Expected exception: CreditFaultMsg has occurred.");
            System.out.println(e.toString());
        }

            }
        {
        System.out.println("Invoking getCredit...");
        org.realestate.GetCreditRequest _getCredit_payload = null;
        try {
            org.realestate.types.ContractInfo _getCredit__return = port.getCredit(_getCredit_payload);
            System.out.println("getCredit.result=" + _getCredit__return);

        } catch (CreditFaultMsg e) {
            System.out.println("Expected exception: CreditFaultMsg has occurred.");
            System.out.println(e.toString());
        } catch (DatatypeConfigurationException_Exception e) {
            System.out.println("Expected exception: DatatypeConfigurationException has occurred.");
            System.out.println(e.toString());
        }

            }

        System.exit(0);
    }

}
