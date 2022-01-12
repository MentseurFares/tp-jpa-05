package fr.diginamic.tpjpa05.exceptions;

public class OperationNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public OperationNotFoundException() {
	}

	public OperationNotFoundException(String message) {
		super(message);
	}

	public OperationNotFoundException(Throwable cause) {
		super(cause);
	}

	public OperationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
