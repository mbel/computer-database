package com.excilys.service;

import javax.servlet.http.HttpServletRequest;

import com.excilys.utils.AttributeUtils;

public class SortService {

	public static final String COMPUTER_ASC = "";
	public static final String COMPUTER_DESC = "o=desc";

	public static final String INTRODUCED_ASC = "s=introduced";
	public static final String INTRODUCED_DESC = "s=introduced&o=desc";

	public static final String DISCONTINUED_ASC = "s=discontinued";
	public static final String DISCONTINUED_DESC = "s=discontinued&o=desc";

	public static final String COMPANY_ASC = "s=company.name";
	public static final String COMPANY_DESC = "s=company.name&o=desc";

	private AttributeUtils computer_att;
	private AttributeUtils introduced_att;
	private AttributeUtils discontinued_att;
	private AttributeUtils company_att;

	private String order;
	private String by;
	private String search;
	private AttributeUtils current;
	private int currentCount;

	public SortService() {
		computer_att = new AttributeUtils(COMPUTER_ASC, COMPUTER_DESC);
		introduced_att = new AttributeUtils(INTRODUCED_ASC, INTRODUCED_DESC);
		discontinued_att = new AttributeUtils(DISCONTINUED_ASC,
				DISCONTINUED_DESC);
		company_att = new AttributeUtils(COMPANY_ASC, COMPANY_DESC);

	}

	public AttributeUtils getComputer_att() {
		return computer_att;
	}

	public AttributeUtils setComputer_att() {
		return computer_att.set(COMPUTER_ASC, COMPUTER_DESC);
	}

	public AttributeUtils getIntroduced_att() {
		return introduced_att;
	}

	public AttributeUtils setIntroduced_att() {
		return introduced_att.set(INTRODUCED_ASC, INTRODUCED_DESC);
	}

	public AttributeUtils getDiscontinued_att() {
		return discontinued_att;
	}

	public AttributeUtils setDiscontinued_att() {
		return discontinued_att.set(DISCONTINUED_ASC, DISCONTINUED_DESC);
	}

	public AttributeUtils getCompany_att() {
		return company_att;
	}

	public AttributeUtils setCompany_att() {
		return company_att.set(COMPANY_ASC, COMPANY_DESC);
	}

	public String getReq() {
		if (order != null)
			return "DESC";
		return "ASC";
	}

	public AttributeUtils set() {
		if (by == null) {
			by = "c.name";
		}
		String sortString = by;
		switch (sortString) {
		case "introduced":
			setBy("c.introduced");
			return setIntroduced_att();
		case "discontinued":
			setBy("c.discontinued");
			return setDiscontinued_att();
		case "company.name":
			setBy("cy.name");
			return setCompany_att();
		default:
			return setComputer_att();
		}
	}

	public AttributeUtils getComputer() {
		return computer_att;
	}

	public void defaultSet() {
		defaulSetCurrent(this.computer_att, COMPUTER_ASC);
		defaulSetCurrent(this.introduced_att, INTRODUCED_ASC);
		defaulSetCurrent(this.discontinued_att, DISCONTINUED_ASC);
		defaulSetCurrent(this.company_att, COMPANY_ASC);
	}

	private void defaulSetCurrent(AttributeUtils au, String name) {
		if (!au.equals(current))
			au.init(name);
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public AttributeUtils getCurrent() {
		return current;
	}

	public void setCurrent(AttributeUtils au) {
		this.current = au;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	public static SortService init(HttpServletRequest request) {
		SortService sortService = (SortService) request.getSession()
				.getAttribute("ss");
		if (sortService == null)
			sortService = new SortService();
		return sortService;
	}
}
