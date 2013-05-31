package com.excilys.dao;

import java.util.List;

import com.excilys.om.Company;

public interface CompanyDao {

	String SELECT_COMPANY = "SELECT * FROM company";

	String SELECT_COMPANY_BY_ID = "SELECT * FROM company where id=?";

	List<Company> findCompanies();

	Company findCompanyById(int id);
	
	void delete(Company company);

	void update(Company company);

	void insert(Company company);

}
