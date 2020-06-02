package main.java.exc.model;

import java.util.Objects;

public final class Company {
	private int id=0;
	private String name="none";
	
	public int getId() {
		return id;//
	}
	public Company setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public Company setName(String name) {
		this.name = name;
		return this;
	}
	public Company(Builder builder)
	{
		this.id = builder.id;
		this.name = builder.name;
	}
	
	
    public boolean equals(final Company other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        return Objects.equals(name, other.name);
    }
	public static class Builder
	{
		private int id;
        public static Builder newInstance() 
        { 
            return new Builder(); 
        } 
        private Builder() {}
		public Builder setId(int id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		private String name;
		public Company build()
		{
			return new Company(this);
		}
	}
	

}
