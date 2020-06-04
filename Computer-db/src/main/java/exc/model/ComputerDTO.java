package main.java.exc.model;

import java.time.LocalDate;

import main.java.exc.model.Computer.Builder;

public class ComputerDTO {
	public ComputerDTO(Builder builder)
	{
		this.id = builder.id;
		this.name =builder.name;
		this.company = builder.company;
		this.discontinued = builder.discontinued;
		this.introduced = builder.introduced;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComputer_company() {
		return Computer_company;
	}
	public void setComputer_company(String computer_company) {
		Computer_company = computer_company;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	private String id;
	private String name;
	private String Computer_company;
	private String company;
	private String introduced;
	private String discontinued;
	public static class Builder
	{
		private String id;
		private String name;
		private String Computer_company;
		private String company;
		private String introduced;
		private String discontinued;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getIntroduced() {
			return introduced;
		}
		public void setIntroduced(String introduced) {
			this.introduced = introduced;
		}
		public String getDiscontinued() {
			return discontinued;
		}
		public void setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
		}
		public String getComputer_company() {
			return Computer_company;
		}
		public void setComputer_company(String computer_company) {
			Computer_company = computer_company;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
        public static Builder newInstance() 
        { 
            return new Builder(); 
        } 
        private Builder() {}

		public ComputerDTO build()
		{
			return new ComputerDTO(this);
		}
	}
}
