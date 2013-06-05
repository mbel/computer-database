package com.excilys.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.excilys.dao.ComputerDao;
import com.excilys.om.Company;
import com.excilys.om.Computer;

@Repository("computerDaoImpl")
public class ComputerDaoImpl implements ComputerDao {

	private JdbcTemplate jdbcTemplate;

	public Computer findComputerById(int computer_id) {
		return this.jdbcTemplate.queryForObject(SELECT_BY_ID,
				new Object[] { computer_id }, Computer.class);
	}

	@Override
	public void delete(Computer computer) {
		deleteComputerById(computer.getId());
	}

	@Override
	public void update(Computer computer) {
		this.jdbcTemplate.update(UPDATE, computer.getName(),
				computer.getIntroduced(), computer.getDiscontinued(),
				computer.getCompany(), computer.getId());
	}

	@Override
	public void insert(Computer computer) {
		this.jdbcTemplate.update(INSERT, majComputer(computer));
	}

	@Override
	public void deleteComputerById(int computer_id) {
		this.jdbcTemplate.update(DELETE_BY_ID, computer_id);
	}

	@Override
	public List<Computer> findOrderByComputers(int p, String req,
			String orderBy, String search) {
		String sql = findStringRequest(p, req, orderBy, search, SELECT_ORDER_BY);
		RowMapper<Computer> mapper = new RowMapper<Computer>() {
			public Computer mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				return createComputer(rs);
			}
		};
		return (List<Computer>) jdbcTemplate.query(sql,
				findObjectRequest(p, search), mapper);
	}

	public int getCurrentCount(int p, String req, String orderBy, String search) {
		String sql = findStringRequest(p, req, orderBy, search, SELECT_COUNT);
		return jdbcTemplate.queryForObject(sql, findObjectRequest(p, search),
				Integer.class);
	}

	private Computer createComputer(ResultSet rs) throws SQLException {
		Computer computer;
		Company company;
		computer = new Computer();
		computer.setId(rs.getInt("c.id"));
		computer.setName(rs.getString("c.name"));
		computer.setIntroduced(rs.getDate("c.introduced"));
		computer.setDiscontinued(rs.getDate("c.discontinued"));
		company = new Company();
		company.setId(rs.getInt("c.company_id"));
		company.setName(rs.getString("cy.name"));
		computer.setCompany(company);
		return computer;
	}

	private Object[] findObjectRequest(int p, String search) {
		if (search == null || "".equals(search)) {
			return new Object[] { p * LIMIT, LIMIT };
		} else {
			return new Object[] {
					new StringBuilder(PERCENT).append(search).append(PERCENT)
							.toString(), p * LIMIT, LIMIT };
		}
	}

	private String findStringRequest(int p, String req, String orderBy,
			String search, String request) {
		if (search == null || "".equals(search))
			return String.format(request, "", orderBy, orderBy, req);
		return String.format(request, SEARCH, orderBy, orderBy, req);
	}

	private Object[] majComputer(Computer computer) {
		return new Object[] { computer.getName(), computer.getIntroduced(),
				computer.getDiscontinued(), computer.getCompany().getId() };
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
