package com.excilys.service;

import java.util.List;

import com.excilys.om.Company;

public interface CompanyService {
	
	List<Company> findCompanies();

	Company findCompanyById(int id);

	void delete(Company company);

	void update(Company company);

	void insert(Company company);
}
