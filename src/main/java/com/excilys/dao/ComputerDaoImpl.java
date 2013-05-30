package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.om.Company;
import com.excilys.om.Computer;

public class ComputerDaoImpl implements ComputerDao {

	private PreparedStatement ptmt = null;
	private ResultSet rs = null;
	private int currentCount;
	private Connection con = null;

	private Connection getConnection() throws SQLException {
		con = DsFactory.INSTANCE.getConnectionThread();
		return con;
	}

	private void closeConnection() {
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
		Company company = null;
		try {
			con = getConnection();
			ptmt = con.prepareStatement(SELECT_BY_ID);
			ptmt.setInt(1, computer_id);
			rs = ptmt.executeQuery();
			if (rs.next()) {
				computer = new Computer();
				computer.setId(rs.getInt("c.id"));
				computer.setName(rs.getString("c.name"));
				computer.setIntroduced(rs.getDate("c.introduced"));
				computer.setDiscontinued(rs.getDate("c.discontinued"));
				company = new Company();
				company.setId(rs.getInt("c.company_id"));
				company.setName(rs.getString("cy.name"));
				computer.setCompany(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return computer;
	}

	@Override
	public void delete(Computer computer) {
		deleteComputerById(computer.getId());
	}

	@Override
	public void update(Computer computer) {
		try {
			con = getConnection();
			ptmt = con.prepareStatement(UPDATE);
			ptmt.setString(1, computer.getName());
			ptmt.setDate(2, computer.getIntroduced());
			ptmt.setDate(3, computer.getDiscontinued());
			ptmt.setLong(4, computer.getCompany().getId());
			ptmt.setLong(5, computer.getId());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public void insert(Computer computer) {
		try {
			con = getConnection();
			ptmt = con.prepareStatement(INSERT);
			ptmt.setString(1, computer.getName());
			ptmt.setDate(2, computer.getIntroduced());
			ptmt.setDate(3, computer.getDiscontinued());
			ptmt.setLong(4, computer.getCompany().getId());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public void deleteComputerById(int computer_id) {
		try {
			con = getConnection();
			ptmt = con.prepareStatement(DELETE_BY_ID);
			ptmt.setInt(1, computer_id);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public List<Computer> findOrderByComputers(int p, String req,
			String orderBy, String search) {
		Computer computer = null;
		Company company = null;
		List<Computer> lp = new ArrayList<Computer>();
		try {
			con = getConnection();
			if (search == null || "".equals(search)) {
				ptmt = con.prepareStatement(String.format(SELECT_ORDER_BY, "",
						orderBy, orderBy, req));
				ptmt.setInt(1, p * 10);
			} else {
				ptmt = con.prepareStatement(String.format(SELECT_ORDER_BY,
						SEARCH, orderBy, orderBy, req));
				ptmt.setString(1,
						new StringBuilder("%").append(search).append("%")
								.toString());
				ptmt.setInt(2, p * 10);
			}
			rs = ptmt.executeQuery();
			while (rs.next()) {
				computer = new Computer();
				computer.setId(rs.getInt("c.id"));
				computer.setName(rs.getString("c.name"));
				computer.setIntroduced(rs.getDate("c.introduced"));
				computer.setDiscontinued(rs.getDate("c.discontinued"));
				company = new Company();
				company.setId(rs.getInt("c.company_id"));
				company.setName(rs.getString("cy.name"));
				computer.setCompany(company);
				lp.add(computer);
			}
			ptmt = con.prepareStatement(ROW_CPT);
			rs = ptmt.executeQuery();
			if (rs.next()) {
				setCurrentCount(rs.getInt("cpt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return lp;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
}
