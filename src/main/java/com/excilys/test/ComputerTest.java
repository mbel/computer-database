package com.excilys.test;

import java.util.List;

import com.excilys.dao.ComputerDaoImpl;
import com.excilys.om.Computer;

public class ComputerTest {


	public static void main(String[] args) {
		ComputerDaoImpl computerDAO = new ComputerDaoImpl();
		List<Computer> lp = computerDAO.findComputers();
		System.out.println(lp);
	}

}
