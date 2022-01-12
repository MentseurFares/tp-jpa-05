package fr.diginamic.tpjpa05.exceptions;

public class BanqueNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public BanqueNotFoundException() {
	}

	public BanqueNotFoundException(String message) {
		super(message);
	}

	public BanqueNotFoundException(Throwable cause) {
		super(cause);
	}

	public BanqueNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public BanqueNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
