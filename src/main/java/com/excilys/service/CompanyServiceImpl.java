package com.excilys.service;

import java.util.List;

import com.excilys.dao.CompanyDao;
import com.excilys.dao.CompanyDaoImpl;
import com.excilys.dao.DsFact;
import com.excilys.om.Company;

public enum CompanyServiceImpl implements CompanyService {

	INSTANCE;

	private CompanyDao companydi;

	private CompanyServiceImpl() {
		companydi = CompanyDaoImpl.INSTANCE;
	}

	private void closeConnection() {
		DsFact.INSTANCE.closeConnection();
	}

	public List<Company> findCompanies() {
		try {
			return companydi.findCompanies();
		} finally {
			closeConnection();
		}
	}

	public Company findCompanyById(int id) {
		try {
			return companydi.findCompanyById(id);
		} finally {
			closeConnection();
		}
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
