package com.lottoland.game.objects.exceptions;

public class ArgumentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1897320304621737947L;
	public ArgumentException(){
		super();
	}
	public ArgumentException(String message, Throwable cause){
		super(message,cause);
	}
}
