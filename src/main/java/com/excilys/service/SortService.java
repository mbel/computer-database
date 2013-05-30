package com.excilys.service;

public class SortService {

	public static final String COMPUTER_ASC = "";
	public static final String COMPUTER_DESC = "o=desc";

	public static final String INTRODUCED_ASC = "s=introduced";
	public static final String INTRODUCED_DESC = "s=introduced&o=desc";

	public static final String DISCONTINUED_ASC = "s=discontinued";
	public static final String DISCONTINUED_DESC = "s=discontinued&o=desc";

	public static final String COMPANY_ASC = "s=company.name";
	public static final String COMPANY_DESC = "s=company.name&o=desc";

	public static final String HEADER_DOWN = "headerSortDown";
	public static final String HEADER_UP = "headerSortUp";

	private String computer;
	private String introduced;
	private String discontinued;
	private String company;
	private String computer_header;
	private String introduced_header;
	private String discontinued_header;
	private String company_header;
	private String order;
	private String by;
	private String search;
	private String current;

	public String getComputer_header() {
		return computer_header;
	}

	public void setComputer_header(String computer_header) {
		this.computer_header = computer_header;
	}

	public String getIntroduced_header() {
		return introduced_header;
	}

	public void setIntroduced_header(String introduced_header) {
		this.introduced_header = introduced_header;
	}

	public String getDiscontinued_header() {
		return discontinued_header;
	}

	public void setDiscontinued_header(String discontinued_header) {
		this.discontinued_header = discontinued_header;
	}

	public String getCompany_header() {
		return company_header;
	}

	public void setCompany_header(String company_header) {
		this.company_header = company_header;
	}

	public SortService() {
		this.computer = COMPUTER_ASC;
		this.introduced = INTRODUCED_ASC;
		this.discontinued = DISCONTINUED_ASC;
		this.company = COMPANY_ASC;
	}

	public String getComputer() {
		return computer;
	}

	public String setComputer() {
		this.introduced = INTRODUCED_ASC;
		this.discontinued = DISCONTINUED_ASC;
		this.company = COMPANY_ASC;
		setIntroduced_header("");
		setDiscontinued_header("");
		setCompany_header("");
		if (this.computer.equals(COMPUTER_ASC)) {
			setComputer_header(HEADER_DOWN);
			this.computer = COMPUTER_DESC;
		} else {
			setComputer_header(HEADER_UP);
			this.computer = COMPUTER_ASC;
		}
		return this.computer;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String setIntroduced() {
		this.computer = COMPUTER_ASC;
		this.discontinued = DISCONTINUED_ASC;
		this.company = COMPANY_ASC;
		setComputer_header("");
		setDiscontinued_header("");
		setCompany_header("");
		if (this.introduced.equals(INTRODUCED_ASC)) {
			setIntroduced_header(HEADER_DOWN);
			this.introduced = INTRODUCED_DESC;
		} else {
			setIntroduced_header(HEADER_UP);
			this.introduced = INTRODUCED_ASC;
		}
		return this.introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public String setDiscontinued() {
		this.computer = COMPUTER_ASC;
		this.introduced = INTRODUCED_ASC;
		this.company = COMPANY_ASC;
		setIntroduced_header("");
		setComputer_header("");
		setCompany_header("");
		if (this.discontinued.equals(DISCONTINUED_ASC)) {
			setDiscontinued_header(HEADER_DOWN);
			this.discontinued = DISCONTINUED_DESC;
		} else {
			setDiscontinued_header(HEADER_UP);
			this.discontinued = DISCONTINUED_ASC;
		}
		return this.discontinued;
	}

	public String getCompany() {
		return company;
	}

	public String setCompany() {
		this.computer = COMPUTER_ASC;
		this.introduced = INTRODUCED_ASC;
		this.discontinued = DISCONTINUED_ASC;
		setIntroduced_header("");
		setDiscontinued_header("");
		setComputer_header("");
		if (this.company.equals(COMPANY_ASC)) {
			setCompany_header(HEADER_DOWN);
			this.company = COMPANY_DESC;
		} else {
			setCompany_header(HEADER_UP);
			this.company = COMPANY_ASC;
		}
		return this.company;
	}

	public String set() {
		if (by == null) {
			by = "c.name";
		}
		String sortString = by;
		switch (sortString) {
		case "introduced":
			setBy("c.introduced");
			return setIntroduced();
		case "discontinued":
			setBy("c.discontinued");
			return setDiscontinued();
		case "company.name":
			setBy("cy.name");
			return setCompany();
		default:
			return setComputer();
		}
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getReq() {
		if (order != null)
			return "DESC";
		return "ASC";
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

}
