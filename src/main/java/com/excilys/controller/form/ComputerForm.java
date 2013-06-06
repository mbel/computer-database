package com.excilys.controller.form;

import java.text.ParseException;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class ComputerForm {

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private int id;

	@NotBlank
	@Size(min = 1, max = 20)
	private String name;

	@DateTimeFormat(pattern = DATE_FORMAT)
	private DateTime introduced;

	@DateTimeFormat(pattern = DATE_FORMAT)
	private DateTime discontinued;

	private int company;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(DateTime introduced) throws ParseException {
		this.introduced = introduced;
	}

	public DateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(DateTime discontinued) throws ParseException {
		this.discontinued = discontinued;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

}
