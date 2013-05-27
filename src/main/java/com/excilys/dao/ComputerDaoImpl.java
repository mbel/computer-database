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

	private Connection con = null;
	private PreparedStatement ptmt = null;
	private ResultSet rs = null;
	private CompanyDaoImpl companydi = new CompanyDaoImpl();

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
			String querystring = "SELECT * FROM computer limit 10";
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
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
				String querystring = "SELECT COUNT(*) FROM computer";
				con = getConnection();
				ptmt = con.prepareStatement(querystring);

				ResultSet rs = ptmt.executeQuery(querystring);
				if (rs.next()) {
					return rs.getInt("COUNT(*)");
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

	public List<Computer> findComputers(int pagination) {
		Computer computer = null;
		Company company = null;
		List<Computer> lp = new ArrayList<Computer>();
		try {
			String querystring = "SELECT * FROM computer LIMIT ?,10";
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
			int start = pagination * 10;
			ptmt.setInt(1, start);
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

	public Computer findComputerById(int computer_id) {
		Computer computer = null;
		Company company = null;
		try {
			String querystring = "SELECT * FROM computer where id=?";
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
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
		String querystring = "DELETE from computer WHERE id = ?";
		try {
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
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
		String querystring = "update computer set name=?,introduced=?,discontinued=?,company_id=? where id=?";
		try {
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
			ptmt.setString(1, computer.getName());
			ptmt.setDate(2, computer.getIntroduced());
			ptmt.setDate(3, computer.getDiscontinued());
			ptmt.setLong(4, computer.getCompany().getId());
			ptmt.setLong(4, computer.getId());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public void insert(Computer computer) {
		String querystring = "insert into computer values(?,?,?,?)";
		try {
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
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
		String querystring = "DELETE from computer WHERE id = ?";
		try {
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
			ptmt.setInt(1, computer_id);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

}
