package com.excilys.om;

public class Company {

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
	private long id;
	private String name;
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
	
}
