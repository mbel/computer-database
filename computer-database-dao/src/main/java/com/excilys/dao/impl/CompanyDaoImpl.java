package com.excilys.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.excilys.dao.CompanyDao;
import com.excilys.om.Company;

@Repository("companyDaoImpl")
public class CompanyDaoImpl implements CompanyDao {

	private JdbcTemplate jdbcTemplate;

	@Override
	public Company findCompanyById(int id) {
		Company company = this.jdbcTemplate.queryForObject(
				SELECT_COMPANY_BY_ID, new Object[] { id },
				new RowMapper<Company>() {
					public Company mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Company company = new Company();
						company.setId(rs.getInt(Company.COMPANY_ID));
						company.setName(rs.getString(Company.COMPANY_NAME));
						return company;
					}
				});
		return company;
	}

	@Override
	public List<Company> findCompanies() {
		return this.jdbcTemplate.query(SELECT_COMPANY,
				new RowMapper<Company>() {
					public Company mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Company company = new Company();
						company.setId(rs.getInt(Company.COMPANY_ID));
						company.setName(rs.getString(Company.COMPANY_NAME));
						return company;
					}
				});
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
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
