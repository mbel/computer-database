package com.excilys.service;

import java.util.List;

import com.excilys.om.Company;

public interface CompanyService {
	
	List<Company> getListCompany();

	Company getCompanyById(int id);

}
