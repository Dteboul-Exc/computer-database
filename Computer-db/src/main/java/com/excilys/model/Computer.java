package com.excilys.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.excilys.model.Company.Builder;

@Entity
@Table(name = "computer")
public final class Computer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="name")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="company_id")
	private Company company;
	
	@Column(name="introduced")
	private LocalDate introduced;
	
	@Column(name="discontinued")
	private LocalDate discontinued;


	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	}

	
	public static class Builder
	{
		
		private long id;
		private String name;
		private Company company;
		private LocalDate introduced;
		private LocalDate discontinued;

		public Builder setId(long id) {
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
