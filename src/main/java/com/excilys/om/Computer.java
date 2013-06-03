package com.excilys.om;

import java.sql.Date;

public class Computer {

	public static final String COMPUTER_ID = "c.id";
	public static final String COMPUTER_NAME = "c.name";
	public static final String COMPUTER_INTRODUCED = "c.introduced";
	public static final String COMPUTER_DISCONTINUED = "c.discontinued";
	public static final String COMPUTER_COMPANY = "c.company_id";

	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Company company;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued + ", company="
				+ company + "]";
	}

}
