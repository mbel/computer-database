package com.excilys.om;

import java.sql.Date;

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
	private Date introduced;

	@DateTimeFormat(pattern = DATE_FORMAT)
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

	public void setIntroduced(DateTime introduced) {
		if (introduced != null)
			this.introduced = new Date(introduced.getMillis());
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(DateTime discontinued) {
		if (introduced != null)
			this.discontinued = new Date(discontinued.getMillis());
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
