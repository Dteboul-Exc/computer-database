package com.excilys.dto;

import java.util.Objects;



public class CompanyDTO {
	private String id;
	private String name;

	
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

	public CompanyDTO(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
	}

	public CompanyDTO() {
		// TODO Auto-generated constructor stub
	}

	public boolean equals(final CompanyDTO other) {
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
		if (other instanceof CompanyDTO)
			return equals((CompanyDTO) other);
		return false;
	}

	public static class Builder {
		private String id;

		public static Builder newInstance() {
			return new Builder();
		}

		private Builder() {
		}

		public Builder setId(final String id) {
			this.id = id;
			return this;
		}

		public Builder setName(final String name) {
			this.name = name;
			return this;
		}

		private String name;

		public CompanyDTO build() {
			return new CompanyDTO(this);
		}
	}
}
