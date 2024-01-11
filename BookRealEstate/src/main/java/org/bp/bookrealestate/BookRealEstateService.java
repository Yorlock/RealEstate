package org.bp.bookrealestate;



import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.bp.types.ContractInfo;
import org.bp.types.Fault;
import org.bp.types.RealEstateInfo;


@javax.jws.WebService
@org.springframework.stereotype.Service
public class BookRealEstateService {
	
	public ContractInfo BookRealEstate(RealEstateInfo realEstateInfo) throws Fault {
		if (realEstateInfo != null &&
				(realEstateInfo.getApartmentSizeInSquareMeters() > 1000 ||
				realEstateInfo.getYearBuilt() > 1960 ||
				realEstateInfo.getPrice() > 5000000 ||
				realEstateInfo.getAverageRentPrice() <= 0 ||
				realEstateInfo.getPrice() <= 0 ||
				realEstateInfo.getApartmentSizeInSquareMeters() <= 0)) {
			Fault fault = new Fault();
			fault.setCode(5);
			fault.setText("The real Estate is not suitable for sale or invalid values");
			throw fault;
		}
		ContractInfo contractInfo = new ContractInfo();
		contractInfo.setId(1);
		
		GregorianCalendar gc;
		XMLGregorianCalendar c = null;
		try {
			gc = new GregorianCalendar();
			gc.setTime(new Date());
			c = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (Exception e) {
			c = null;
		}
		
		contractInfo.setDate(c);
		return contractInfo;
		
	}
	
	public ContractInfo cancelBooking(int bookingId) throws Fault{
		return null;
	}

}
