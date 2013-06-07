package com.excilys.om;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class Computer {

	public static final String COMPUTER_ID = "c.id";
	public static final String COMPUTER_NAME = "c.name";
	public static final String COMPUTER_INTRODUCED = "c.introduced";
	public static final String COMPUTER_DISCONTINUED = "c.discontinued";
	public static final String COMPUTER_COMPANY = "c.company_id";

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private int id;

	@NotBlank
	@Size(min = 1, max = 50)
	private String name;

	@DateTimeFormat(pattern = DATE_FORMAT)
	private DateTime introduced;

	@DateTimeFormat(pattern = DATE_FORMAT)
	private DateTime discontinued;

	private Company company;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(DateTime introduced) {
		this.introduced = introduced;
	}

	public DateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(DateTime discontinued) {
		this.discontinued = introduced;
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
