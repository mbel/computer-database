package com.excilys.service;

import java.util.List;

import com.excilys.dao.CompanyDaoImpl;
import com.excilys.om.Company;

public class CompanyServiceImpl implements CompanyService{

	private CompanyDaoImpl companydi;
	
	public CompanyServiceImpl() {
		companydi = new CompanyDaoImpl();
	}

	public List<Company> getListCompany() {
		return companydi.getListCompany();
	}

	public Company getCompanyById(int id) {
		return companydi.getCompanyById(id);
	}

}
