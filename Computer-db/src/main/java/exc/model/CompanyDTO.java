package main.java.exc.model;

import main.java.exc.model.Company.Builder;

public class CompanyDTO {

		public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public CompanyDTO(Builder builder)
	{
		this.id = builder.id;
		this.name = builder.name;
	}
	public CompanyDTO() {
		// TODO Auto-generated constructor stub
	}
	public void setName(String name) {
		this.name = name;
	}
		private String id;
		private String name;
		public static class Builder
		{
			private String id;
	        public static Builder newInstance() 
	        { 
	            return new Builder(); 
	        } 
	        private Builder() {}
			public Builder setId(final String id) {
				this.id = id;
				return this;
			}

			public Builder setName(final String name) {
				this.name = name;
				return this;
			}
			private String name;
			public CompanyDTO build()
			{
				return new CompanyDTO(this);
			}
		}
}
