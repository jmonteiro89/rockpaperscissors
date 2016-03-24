package com.lottoland.game.objects.exceptions;

public class BadPlayerConfigurationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4333037365455653703L;
	public BadPlayerConfigurationException(){
		super();
	}
	public BadPlayerConfigurationException(String message, Throwable cause){
		super(message,cause);
	}
}
