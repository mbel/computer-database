package com.excilys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.dao.CompanyDao;
import com.excilys.om.Company;

@Repository("companyDaoImpl")
public class CompanyDaoImpl implements CompanyDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public Company findCompanyById(int id) {
		return hibernateTemplate.get(Company.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findCompanies() {
		return hibernateTemplate.find(SELECT_COMPANY);
	}

	@Override
	public void delete(Company company) {
	}

	@Override
	public void update(Company company) {
	}

	@Override
	public void insert(Company company) {
	}

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

}
