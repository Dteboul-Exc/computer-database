package main.java.excilys.persistence;

import org.springframework.stereotype.Component;


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
