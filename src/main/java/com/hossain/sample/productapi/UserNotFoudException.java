package com.hossain.sample.productapi;

public class UserNotFoudException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4691872277046719897L;
	
	public UserNotFoudException(Integer id) {
		super("Could not find user "+id);
	}
	
	public UserNotFoudException(String userId) {
		super("Could not find user "+userId);
	}

}
