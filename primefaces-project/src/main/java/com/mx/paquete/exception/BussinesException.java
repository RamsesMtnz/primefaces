package com.mx.paquete.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class BussinesException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BussinesException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BussinesException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BussinesException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BussinesException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BussinesException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
