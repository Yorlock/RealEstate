package org.bp.bookrealestate;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.bp.credit.GetCreditEndpointService;
import org.realestate.GetCreditRequest;
import org.realestate.credit.CreditFaultMsg;
import org.realestate.credit.DatatypeConfigurationException_Exception;
import org.realestate.credit.GetCredit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GetCreditController {
	private static final QName SERVICE_NAME = new QName("http://credit.bp.org/", "GetCreditEndpointService");

	@GetMapping("/getCredit")
	public String getCreditForm(Model model) {
		model.addAttribute("getCreditRequest", new GetCreditRequest());
		return "getCredit";
	}
	
	@PostMapping("/getCredit")
	public String getCredit(@ModelAttribute GetCreditRequest brf, Model model) {
		URL wsdlURL = GetCreditEndpointService.WSDL_LOCATION;
		
		GetCreditEndpointService ss = new GetCreditEndpointService(wsdlURL, SERVICE_NAME);
        GetCredit port = ss.getGetCreditEndpointPort();

        {
	        System.out.println("Invoking getCredit...");
	        
	        org.realestate.GetCreditRequest _getCredit_payload = brf;
	        try {
	            org.realestate.types.ContractInfo _getCredit__return = port.getCredit(_getCredit_payload);
	            System.out.println("getCredit.result=" + _getCredit__return);
	            
	            model.addAttribute("contractInfo", _getCredit__return);
	            return "result";
	
	        } catch (CreditFaultMsg e) {
	            System.out.println("Expected exception: CreditFaultMsg has occurred.");
	            System.out.println(e.toString());
	            model.addAttribute("creditFaultMsg", e);
	            return "fault";
	        } catch (DatatypeConfigurationException_Exception e) {
	        	System.out.println("Expected exception: DatatypeConfigurationException_Exception has occurred.");
	            System.out.println(e.toString());
	            model.addAttribute("DatatypeConfigurationException_Exception", e);
	            return "fault";
			}
        }
	}

}
