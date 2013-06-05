package com.excilys.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.excilys.dao.ComputerDao;
import com.excilys.om.Company;
import com.excilys.om.Computer;
import com.excilys.utils.DsFact;

@Repository("computerDaoImpl")
public class ComputerDaoImpl implements ComputerDao {

	private void closeConnection(PreparedStatement ptmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ptmt != null)
				ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Computer findComputerById(int computer_id) {
		Computer computer = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		try {
			ptmt = DsFact.INSTANCE.getConnectionThread().prepareStatement(
					SELECT_BY_ID);
			ptmt.setInt(1, computer_id);
			rs = ptmt.executeQuery();
			if (rs.next()) {
				computer = createComputer(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(ptmt, rs);
		}
		return computer;
	}

	@Override
	public void delete(Computer computer) {
		deleteComputerById(computer.getId());
	}

	@Override
	public void update(Computer computer) {
		PreparedStatement ptmt = null;
		try {
			ptmt = DsFact.INSTANCE.getConnectionThread().prepareStatement(
					UPDATE);
			majComputer(computer, ptmt);
			ptmt.setLong(5, computer.getId());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(ptmt, null);
		}
	}

	@Override
	public void insert(Computer computer) {
		PreparedStatement ptmt = null;
		try {
			ptmt = DsFact.INSTANCE.getConnectionThread().prepareStatement(
					INSERT);
			majComputer(computer, ptmt);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(ptmt, null);
		}
	}

	@Override
	public void deleteComputerById(int computer_id) {
		PreparedStatement ptmt = null;
		try {
			ptmt = DsFact.INSTANCE.getConnectionThread().prepareStatement(
					DELETE_BY_ID);
			ptmt.setInt(1, computer_id);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(ptmt, null);
		}
	}

	@Override
	public List<Computer> findOrderByComputers(int p, String req,
			String orderBy, String search) {
		Computer computer = null;
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<Computer> lp = new ArrayList<Computer>();
		try {
			con = DsFact.INSTANCE.getConnectionThread();
			ptmt = findRequest(p, req, orderBy, search, con, SELECT_ORDER_BY);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				computer = createComputer(rs);
				lp.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(ptmt, rs);
		}
		return lp;
	}

	public int getCurrentCount(int p, String req, String orderBy, String search) {
		Connection con = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		int currentCount = 0;
		try {
			con = DsFact.INSTANCE.getConnectionThread();
			ptmt = findRequest(p, req, orderBy, search, con, SELECT_COUNT);
			rs = ptmt.executeQuery();
			if (rs.next()) {
				currentCount = rs.getInt(CPT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(ptmt, rs);
		}
		return currentCount;
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

	private PreparedStatement findRequest(int p, String req, String orderBy,
			String search, Connection con, String request) throws SQLException {
		PreparedStatement ptmt;
		if (search == null || "".equals(search)) {
			ptmt = con.prepareStatement(String.format(request, "", orderBy,
					orderBy, req));
			ptmt.setInt(1, p * LIMIT);
			ptmt.setInt(2, LIMIT);
		} else {
			ptmt = con.prepareStatement(String.format(request, SEARCH, orderBy,
					orderBy, req));
			ptmt.setString(1,
					new StringBuilder(PERCENT).append(search).append(PERCENT)
							.toString());
			ptmt.setInt(2, p * LIMIT);
			ptmt.setInt(3, LIMIT);
		}
		return ptmt;
	}

	private void majComputer(Computer computer, PreparedStatement ptmt)
			throws SQLException {
		ptmt.setString(1, computer.getName());
		ptmt.setDate(2, computer.getIntroduced());
		ptmt.setDate(3, computer.getDiscontinued());
		ptmt.setLong(4, computer.getCompany().getId());
	}

}
