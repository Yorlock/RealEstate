package org.bp.realEstate.exceptions;

public class CreditException extends Exception {

	public CreditException() {
	}

	public CreditException(String message) {
		super(message);
	}

	public CreditException(Throwable cause) {
		super(cause);
	}

	public CreditException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreditException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
