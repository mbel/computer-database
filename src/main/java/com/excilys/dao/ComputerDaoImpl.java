package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.excilys.om.Company;
import com.excilys.om.Computer;

public class ComputerDaoImpl implements ComputerDao {

	private Connection con = null;
	private PreparedStatement ptmt = null;
	private ResultSet rs = null;
	private CompanyDaoImpl companydi = new CompanyDaoImpl();

	private static Map<Integer, String> mapQuery = new HashMap<>();

	static {
		mapQuery.put(2, SELECT_ORDER_BY);
		mapQuery.put(-2, SELECT_ORDER_BY_DESC);
		mapQuery.put(3, SELECT_ORDER_BY_JOIN);
		mapQuery.put(-3, SELECT_ORDER_BY_DESC_JOIN);

	}

	private Connection getConnection() throws SQLException {
		con = ConnectionFact.getInstance().getConnection();
		return con;
	}

	private void closeConnection() {
		try {
			if (rs != null)
				rs.close();
			if (ptmt != null)
				ptmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Computer> findComputers() {
		Computer computer = null;
		Company company = null;
		List<Computer> lp = new ArrayList<Computer>();
		try {
			con = getConnection();
			ptmt = con.prepareStatement(SELECT_ALL);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				computer = new Computer();
				computer.setId(rs.getLong(1));
				computer.setName(rs.getString(2));
				computer.setIntroduced(rs.getDate(3));
				computer.setDiscontinued(rs.getDate(4));
				company = companydi.findCompanyById(rs.getInt(5));
				computer.setCompany(company);
				lp.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return lp;
	}

	public int countComputers() {
		try {
			try {
				con = getConnection();
				ptmt = con.prepareStatement(COUNT);

				ResultSet rs = ptmt.executeQuery(COUNT);
				if (rs.next()) {
					return rs.getInt(COUNT_PARAM);
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return -1;
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
				computer.setId(rs.getLong(1));
				computer.setName(rs.getString(2));
				computer.setIntroduced(rs.getDate(3));
				computer.setDiscontinued(rs.getDate(4));
				company = companydi.findCompanyById(rs.getInt(5));
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
		try {
			con = getConnection();
			ptmt = con.prepareStatement(DELETE);
			ptmt.setLong(1, computer.getId());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
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
	public List<Computer> findOrderByComputers(int p, int req, String orderBy,
			String search) {
		Computer computer = null;
		Company company = null;
		List<Computer> lp = new ArrayList<Computer>();
		try {
			con = getConnection();
			if (search == null || "".equals(search)) {
				ptmt = con.prepareStatement(String.format(mapQuery.get(req),
						"", orderBy, orderBy));
				ptmt.setInt(1, p * 10);
			} else {
				ptmt = con.prepareStatement(String.format(mapQuery.get(req),
						SEARCH, orderBy, orderBy));
				ptmt.setString(1,
						new StringBuilder("%").append(search).append("%")
								.toString());
				ptmt.setInt(2, p * 10);
			}
			rs = ptmt.executeQuery();
			while (rs.next()) {
				computer = new Computer();
				computer.setId(rs.getLong(1));
				computer.setName(rs.getString(2));
				computer.setIntroduced(rs.getDate(3));
				computer.setDiscontinued(rs.getDate(4));
				company = companydi.findCompanyById(rs.getInt(5));
				computer.setCompany(company);
				lp.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return lp;
	}
}
