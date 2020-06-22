package main.java.exc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import org.springframework.stereotype.Component;

import main.java.exc.model.Company.Builder;


public final class Computer {
	private int id;
	private String name;
	private Company Computer_company;
	private Company company;
	private LocalDate introduced;
	private LocalDate discontinued;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	public Company getComputer_company() {
		return Computer_company;
	}
	public void setComputer_company(Company computer_company) {
		Computer_company = computer_company;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
    public boolean equals(final Computer other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (Objects.equals(name, other.name) && Objects.equals(id, other.id)) return true;
        return false;
    }
    public boolean equals(final Object other) {
        if (other instanceof Computer)  return equals((Computer) other);
        return false;
    }
	public Computer(Builder builder)
	{
		this.id = builder.id;
		this.name =builder.name;
		this.company = builder.company;
		this.discontinued = builder.discontinued;
		this.introduced = builder.introduced;
	}
	
	public Computer() {
		// TODO Auto-generated constructor stub
	}

	
	public static class Builder
	{
		
		private int id;
		private String name;
		private Company Computer_company;
		private Company company;
		private LocalDate introduced;
		private LocalDate discontinued;

		public Builder setId(int id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setIntroduced(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}

		public Builder setDiscontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public Builder setComputer_company(Company computer_company) {
			Computer_company = computer_company;
			return this;
		}

		public Builder setCompany(Company company) {
			this.company = company;
			return this;
		}
        public static Builder newInstance() 
        { 
            return new Builder(); 
        } 
        private Builder() {}

		public Computer build()
		{
			return new Computer(this);
		}
	}

	
	
}
