package com.davesherby.poison.shutdowner;

/**
 * Encapsulates exceptions in Shutdowner API
 * 
 * @author olivier
 */
public class ShutdownerException extends RuntimeException {

	public ShutdownerException(String noShutdownScheduled) {
		super();
	}

	public ShutdownerException() {
		super();
	}

	public ShutdownerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ShutdownerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShutdownerException(Throwable cause) {
		super(cause);
	}


	private static final long serialVersionUID = 1L;
	
	public static final String NO_SHUTDOWN_SCHEDULED = "NO_SHUTDOWN_SCHEDULED";
	public static final String INCORRECT_SHUTDOWN_DATE = "INCORRECT_SHUTDOWN_DATE";

}
