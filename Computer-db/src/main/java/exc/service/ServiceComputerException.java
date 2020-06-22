package main.java.exc.service;

import org.springframework.stereotype.Component;

public class ServiceComputerException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceComputerException (String e)
	{
		super(e);
	}
	
}
