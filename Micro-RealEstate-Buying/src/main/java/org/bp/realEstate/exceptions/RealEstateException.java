package org.bp.realEstate.exceptions;

public class RealEstateException extends Exception {

	public RealEstateException() {
	}

	public RealEstateException(String message) {
		super(message);
	}

	public RealEstateException(Throwable cause) {
		super(cause);
	}

	public RealEstateException(String message, Throwable cause) {
		super(message, cause);
	}

	public RealEstateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
