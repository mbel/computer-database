package com.excilys.service;

import java.util.List;

import com.excilys.dao.ComputerDaoImpl;
import com.excilys.om.Computer;

public class ComputerServiceImpl implements ComputerService{

	private ComputerDaoImpl computerdi;
	
	public ComputerServiceImpl() {
		computerdi = new ComputerDaoImpl();
	}	
	
	public List<Computer> getListComputers() {
		return computerdi.getListComputers();
	}

	@Override
	public int countComputers() {
		return computerdi.countComputers();
	}

	@Override
	public List<Computer> getListComputers(int p) {
		return computerdi.getListComputers(p);
	}

}
