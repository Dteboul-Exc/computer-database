package main.java.excilys.exception;

/**
 * @author dteboul
 * Class used by service company in case of an exception.
 *
 */
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
