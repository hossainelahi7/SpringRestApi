package com.hossain.sample.productapi;

public class ProductNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8188996459344883656L;

	public ProductNotFoundException(Integer id){
		super("Could not find product "+id);
	}

}
