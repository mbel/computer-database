package com.excilys.controller.form;

import java.beans.PropertyEditorSupport;

import com.excilys.om.Company;
import com.excilys.service.CompanyService;

public class CompanyConverter extends PropertyEditorSupport {

	private CompanyService companyService;

	public CompanyConverter(CompanyService companyService) {
		this.companyService = companyService;
	}

	@Override
	public String getAsText() {
		Company company = (Company) getValue();
		if (company == null) {
			return null;
		}
		return String.valueOf(company.getId());
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.trim().isEmpty()) {
			try {
				int companyId = Integer.parseInt(text);
				setValue(companyService.findCompanyById(companyId));
			} catch (NumberFormatException e) {
				setValue(null);
			}
		}
	}

}
