package fr.diginamic.tpjpa05.exceptions;

public class CompteNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CompteNotFoundException() {
	}

	public CompteNotFoundException(String message) {
		super(message);
	}

	public CompteNotFoundException(Throwable cause) {
		super(cause);
	}

	public CompteNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompteNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
