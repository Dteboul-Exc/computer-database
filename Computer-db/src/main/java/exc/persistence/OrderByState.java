package main.java.exc.persistence;

public enum OrderByState {
	COMPANY("company.name"),
	NAME("computer.name");

	private String order;
	OrderByState(String string) {
		this.order = string;
	}
	
	public String getOrder()
	{
		return order;
	}
	

}
