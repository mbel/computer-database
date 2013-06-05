package com.excilys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.CompanyDao;
import com.excilys.om.Company;
import com.excilys.service.CompanyService;

@Service("companyServiceImpl")
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companydi;

	public List<Company> findCompanies() {
		return companydi.findCompanies();
	}

	public Company findCompanyById(int id) {
		return companydi.findCompanyById(id);
	}

	@Override
	public void delete(Company company) {
		companydi.delete(company);
	}

	@Override
	public void update(Company company) {
		companydi.update(company);
	}

	@Override
	public void insert(Company company) {
		companydi.insert(company);
	}

}
