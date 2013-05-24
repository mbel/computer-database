package com.excilys.dao;

import java.util.List;

import com.excilys.om.Company;

public interface CompanyDao {

	List<Company> findCompanies();

	Company findCompanyById(int id);

}
