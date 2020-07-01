package com.excilys.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "company")
public final class Company implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="name")
	private String name;
	
	public Company() {}
	
	public long getId() {
		return id;//
	}
	public Company setId(final long id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public Company setName(final String name) {
		this.name = name;
		return this;
	}
	public Company(Builder builder)
	{
		this.id = builder.id;
		this.name = builder.name;
	}
	
	public Company(long id2,String name2)
	{
		this.id =id2;
		this.name = name2;
	}
    public boolean equals(final Company other) {
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
        if (other instanceof Company)  return equals((Company) other);
        return false;
    }
	public static class Builder
	{
		private long id;
        public static Builder newInstance() 
        { 
            return new Builder(); 
        } 
        private Builder() {}
		public Builder setId(final int id) {
			this.id = id;
			return this;
		}

		public Builder setName(final String name) {
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
