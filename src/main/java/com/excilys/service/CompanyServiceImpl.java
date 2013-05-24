package com.excilys.service;

import java.util.List;

import com.excilys.dao.CompanyDao;
import com.excilys.dao.CompanyDaoImpl;
import com.excilys.om.Company;

public class CompanyServiceImpl implements CompanyService{

	private CompanyDao companydi;
	
	public CompanyServiceImpl() {
		companydi = new CompanyDaoImpl();
	}

	public List<Company> findCompanies() {
		return companydi.findCompanies();
	}

	public Company findCompanyById(int id) {
		return companydi.findCompanyById(id);
	}

	@Override
	public void delete(Company company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Company company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Company company) {
		// TODO Auto-generated method stub
		
	}

}
