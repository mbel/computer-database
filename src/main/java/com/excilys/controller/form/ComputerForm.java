package com.excilys.controller.form;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ComputerForm {

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) throws ParseException {
		this.introduced = stringToDate(introduced);
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) throws ParseException {
		this.discontinued = stringToDate(discontinued);
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	private static Date stringToDate(String sDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		return (Date) formatter.parse(sDate);
	}

}
