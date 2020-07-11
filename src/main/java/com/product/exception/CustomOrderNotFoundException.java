package com.product.exception;

public class CustomOrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8759292814316186790L;
	
	public CustomOrderNotFoundException(String message){
		super(message);
	}
	
	CustomOrderNotFoundException(){}
}
