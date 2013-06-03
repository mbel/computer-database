package com.excilys.dao;

import java.util.List;

import com.excilys.om.Company;

public interface CompanyDao {

	String SELECT_COMPANY = "SELECT * FROM company cy";

	String SELECT_COMPANY_BY_ID = "SELECT * FROM company cy where id=?";

	List<Company> findCompanies();

	Company findCompanyById(int id);
	
	void delete(Company company);

	void update(Company company);

	void insert(Company company);

}
