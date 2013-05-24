package com.excilys.service;

import java.util.List;

import com.excilys.om.Computer;

public interface ComputerService {
	
	public List<Computer> getListComputers() ;

	public int countComputers();

	public List<Computer> getListComputers(int p);

}
