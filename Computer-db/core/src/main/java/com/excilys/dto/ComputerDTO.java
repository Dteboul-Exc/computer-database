package com.excilys.dto;

import java.util.Objects;

public class ComputerDTO {

	private String id;
	private String name;
	private String Computer_company;
	private CompanyDTO company;
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

	public String getComputer_company() {
		return Computer_company;
	}

	public void setComputer_company(String computer_company) {
		Computer_company = computer_company;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
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

	public boolean equals(final ComputerDTO other) {
		if (other == null) {
			return false;
		}
		if (this == other) {
			return true;
		}
		if (Objects.equals(name, other.name) && Objects.equals(id, other.id))
			return true;
		return false;
	}

	@Override
	public boolean equals(final Object other) {
		if (other instanceof ComputerDTO)
			return equals((ComputerDTO) other);
		return false;
	}

	public ComputerDTO(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.company = builder.company;
		this.discontinued = builder.discontinued;
		this.introduced = builder.introduced;
	}

	public static class Builder {
		private String id;
		private String name;
		private String Computer_company;
		private CompanyDTO company;
		private String introduced;
		private String discontinued;

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public Builder setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public Builder setComputer_company(String computer_company) {
			Computer_company = computer_company;
			return this;
		}

		public Builder setCompany(CompanyDTO company) {
			this.company = company;
			return this;
		}

		public static Builder newInstance() {
			return new Builder();
		}

		private Builder() {
		}

		public ComputerDTO build() {
			return new ComputerDTO(this);
		}
	}
}
