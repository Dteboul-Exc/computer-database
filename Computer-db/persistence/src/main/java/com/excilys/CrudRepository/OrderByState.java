package com.excilys.CrudRepository;


/**
 * Enum  used for the OrderBy feature
 * @author dteboul
 *
 */
public enum OrderByState {
	COMPANY("company.name"),
	COMPUTER("computer.name");

	private String order;
	OrderByState(String string) {
		this.order = string;
	}
	
	public String getOrder()
	{
		return order;
	}
	

}
