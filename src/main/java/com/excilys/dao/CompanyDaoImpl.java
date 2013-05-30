package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.om.Company;

public class CompanyDaoImpl implements CompanyDao {

	private Connection con = null;
	private PreparedStatement ptmt = null;
	private ResultSet rs = null;



	private Connection getConnection() throws SQLException {
		con = DsFact.INSTANCE.getConnectionThread();
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

	public Company findCompanyById(int id) {
		Company company = null;
		try {
			con = getConnection();
			ptmt = con.prepareStatement(SELECT_COMPANY_BY_ID);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();
			if (rs.next()) {
				company = new Company();
				company.setId(rs.getInt(1));
				company.setName(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return company;
	}

	public List<Company> findCompanies() {
		Company company = null;
		List<Company> lp = new ArrayList<Company>();
		try {
			con = getConnection();
			ptmt = con.prepareStatement(SELECT_COMPANY);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				lp.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return lp;
	}

}
