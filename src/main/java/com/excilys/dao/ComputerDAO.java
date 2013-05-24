package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.om.Computer;

public class ComputerDAO {

	private Connection con = null;
	private PreparedStatement ptmt = null;
	private ResultSet rs = null;

	private Connection getConnection() throws SQLException {
		con = ConnectionPlant.getInstance().getConnection();
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

	public List<Computer> getListProduits() {
		Computer produit = null;
		List<Computer> lp = new ArrayList<Computer>();
		try {
			String querystring = "SELECT * FROM produits";
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				produit = new Computer();
				produit.setId(rs.getLong(1));
				produit.setNom(rs.getString(2));
				produit.setCode_ean(rs.getString(3));
				produit.setDescription(rs.getString(4));
				produit.setLien_photo(rs.getString(5));
				produit.setPrix(rs.getLong(6));
				lp.add(produit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return lp;
	}

	public void insereProduit(Computer produit) {
		try {
			String querystring = "INSERT INTO produits VALUES(?,?,?,?,?,?)";
			con = getConnection();
			ptmt = con.prepareStatement(querystring);
			ptmt.setLong(1, produit.getId());
			ptmt.setString(2, produit.getNom());
			ptmt.setString(3, produit.getCode_ean());
			ptmt.setString(4, produit.getDescription());
			ptmt.setString(5, produit.getLien_photo());
			ptmt.setLong(6, produit.getPrix());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}
}
