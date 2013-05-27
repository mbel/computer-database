package com.excilys.service;

public class SortService {

	public static final String COMPUTER_ASC = "computer";
	public static final String COMPUTER_DESC = "computer?o=desc";

	public static final String INTRODUCED_ASC = "computer?s=introduced";
	public static final String INTRODUCED_DESC = "computer?s=introduced&o=desc";

	public static final String DISCONTINUED_ASC = "computer?s=discontinued";
	public static final String DISCONTINUED_DESC = "computer?s=discontinued&o=desc";

	public static final String COMPANY_ASC = "computer?s=company.name";
	public static final String COMPANY_DESC = "computer?s=company.name&o=desc";

	private String computer;
	private String introduced;
	private String discontinued;
	private String company;

	public SortService() {
		this.computer = COMPUTER_ASC;
		this.introduced = INTRODUCED_ASC;
		this.discontinued = DISCONTINUED_ASC;
		this.company = COMPANY_ASC;
	}

	public String getComputer() {
		return computer;
	}

	public void setComputer(String computer) {
		this.computer = (this.computer.equals(COMPUTER_ASC)) ? COMPUTER_DESC
				: COMPUTER_ASC;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = (this.introduced.equals(INTRODUCED_ASC)) ? INTRODUCED_DESC
				: INTRODUCED_ASC;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = (this.discontinued.equals(DISCONTINUED_ASC)) ? DISCONTINUED_DESC
				: DISCONTINUED_ASC;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = (this.company.equals(COMPANY_ASC)) ? COMPANY_DESC
				: COMPANY_ASC;
	}

	public void set(String sortString) {
		switch (sortString) {
		case "computer":
			setComputer(sortString);
			break;
		case "introduced":
			setIntroduced(sortString);
			break;
		case "discontinued":
			setDiscontinued(sortString);
			break;
		case "company":
			setCompany(sortString);
			break;
		}
	}

}
