package com.excilys.dao;

import java.util.List;

import com.excilys.om.Computer;

public interface ComputerDao {

	List<Computer> getListComputers();
	
	List<Computer> getListComputers(int p);

	int countComputers();

}
