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

	private ParamService ps;

	public SortService() {
		computer_att = new AttributeUtils(COMPUTER_ASC, COMPUTER_DESC);
		introduced_att = new AttributeUtils(INTRODUCED_ASC, INTRODUCED_DESC);
		discontinued_att = new AttributeUtils(DISCONTINUED_ASC,
				DISCONTINUED_DESC);
		company_att = new AttributeUtils(COMPANY_ASC, COMPANY_DESC);
		ps = ParamService.INSTANCE;

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
		if (ps.getOrder() != null)
			return "DESC";
		return "ASC";
	}

	public AttributeUtils set() {
		if (ps.getBy() == null) {
			ps.setBy("c.name");

		}
		String sortString = ps.getBy();
		switch (sortString) {
		case "introduced":
			ps.setBy("c.introduced");
			return setIntroduced_att();

		case "discontinued":

			ps.setBy("c.discontinued");
			return setDiscontinued_att();

		case "company.name":

			ps.setBy("cy.name");
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
		if (!au.equals(ps.getCurrent()))
			au.init(name);
	}

	public ParamService getPs() {
		return ps;
	}

	public void setPs(ParamService ps) {
		this.ps = ps;
	}

	public void setCurrentCount(int currentCount) {
		ps.setCurrentCount(currentCount);
	}

	public void setPs(String by, String order, String search) {
		ps.setBy(by);
		ps.setOrder(order);
		ps.setSearch(search);
		ps.setCurrent(this.set());
	}

	public static SortService init(HttpServletRequest request) {
		SortService sortService = (SortService) request.getSession()
				.getAttribute("ss");
		if (sortService == null)
			sortService = new SortService();
		return sortService;
	}
}
