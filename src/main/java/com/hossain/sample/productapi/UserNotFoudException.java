package com.hossain.sample.productapi;

public class UserNotFoudException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4691872277046719897L;
	
	public UserNotFoudException(String email) {
		super("Could not find user "+email);
	}

}
