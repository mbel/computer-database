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

	private String computer;
	private String introduced;
	private String discontinued;
	private String company;

	private int req;

	private String order;
	private String by;
	private String search;
	private String current;

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
		return this.computer = (this.computer.equals(COMPUTER_ASC)) ? COMPUTER_DESC
				: COMPUTER_ASC;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String setIntroduced() {
		this.computer = COMPUTER_ASC;
		this.discontinued = DISCONTINUED_ASC;
		this.company = COMPANY_ASC;
		return this.introduced = (this.introduced.equals(INTRODUCED_ASC)) ? INTRODUCED_DESC
				: INTRODUCED_ASC;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public String setDiscontinued() {
		this.computer = COMPUTER_ASC;
		this.introduced = INTRODUCED_ASC;
		this.company = COMPANY_ASC;
		return this.discontinued = (this.discontinued.equals(DISCONTINUED_ASC)) ? DISCONTINUED_DESC
				: DISCONTINUED_ASC;
	}

	public String getCompany() {
		return company;
	}

	public String setCompany() {
		this.computer = COMPUTER_ASC;
		this.introduced = INTRODUCED_ASC;
		this.discontinued = DISCONTINUED_ASC;
		return this.company = (this.company.equals(COMPANY_ASC)) ? COMPANY_DESC
				: COMPANY_ASC;
	}

	public String set() {
		if (by == null) {
			by = "name";
		}
		String sortString = by;
		switch (sortString) {
		case "introduced":
			setReq(2);
			return setIntroduced();
		case "discontinued":
			setReq(2);
			return setDiscontinued();
		case "company.name":
			setReq(3);
			return setCompany();
		default:
			setReq(2);
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

	public int getReq() {
		if (order != null)
			req *= -1;
		return req;
	}

	public void setReq(int req) {
		this.req = req;
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
