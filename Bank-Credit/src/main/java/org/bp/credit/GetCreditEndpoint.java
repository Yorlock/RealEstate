package org.bp.credit;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.realestate.CancelCreditRequest;
import org.realestate.GetCreditRequest;
import org.realestate.credit.CreditFaultMsg;
import org.realestate.credit.GetCredit;
import org.realestate.types.ContractInfo;
import org.realestate.types.Fault;
import org.springframework.stereotype.Service;

@Service
public class GetCreditEndpoint implements GetCredit {

	@Override
	public ContractInfo getCredit(GetCreditRequest payload) throws CreditFaultMsg, DatatypeConfigurationException {
		
		
		if (payload != null && 
				payload.getCreditInfo() != null && 
				payload.getCreditRecipient() != null && 
				payload.getCreditInfo().getCreditVolume() * 12 > payload.getCreditInfo().getHowManyMonths() * payload.getCreditInfo().getMonthPayment() +
				payload.getCreditInfo().getHowManyMonths() * (payload.getCreditRecipient().getSumIncomeYear() - payload.getCreditRecipient().getSumSpendingYear())) {
			Fault creditFault = new Fault();
			creditFault.setCode(10);
			creditFault.setText("You cannot take a credit");
			
			CreditFaultMsg fault = new CreditFaultMsg("You will not be able to take a loan", creditFault);
			throw fault;
		}
		
		ContractInfo response = new ContractInfo();
		response.setId(1);
		
		GregorianCalendar gc;
		XMLGregorianCalendar c = null;
		try {
			gc = new GregorianCalendar();
			gc.setTime(new Date());
			c = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (Exception e) {
			c = null;
		}
		
		response.setDate(c);
		
		return response;
	}

	@Override
	public ContractInfo cancelCredit(CancelCreditRequest payload) throws CreditFaultMsg {
		// TODO Auto-generated method stub
		return null;
	}

}
