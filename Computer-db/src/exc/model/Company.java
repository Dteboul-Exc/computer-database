package exc.model;

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
