package com.excilys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dao.CompanyDao;
import com.excilys.om.Company;
import com.excilys.service.CompanyService;
import com.excilys.utils.DsFact;


@Service("companyServiceImpl")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companydi;

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
		try {
			companydi.delete(company);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void update(Company company) {
		try {
			companydi.update(company);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void insert(Company company) {
		try {
			companydi.insert(company);
		} finally {
			closeConnection();
		}
	}

}
