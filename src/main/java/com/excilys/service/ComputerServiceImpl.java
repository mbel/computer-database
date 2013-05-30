package com.excilys.service;

import java.util.List;

import com.excilys.dao.ComputerDao;
import com.excilys.dao.ComputerDaoImpl;
import com.excilys.om.Computer;

public class ComputerServiceImpl implements ComputerService {

	private ComputerDao computerdi;

	public ComputerServiceImpl() {
		computerdi = new ComputerDaoImpl();
	}

	@Override
	public Computer findComputerById(int computer_id) {
		return computerdi.findComputerById(computer_id);
	}

	@Override
	public void delete(Computer computer) {
		computerdi.delete(computer);
	}

	@Override
	public void update(Computer computer) {
		computerdi.update(computer);
	}

	@Override
	public void insert(Computer computer) {
		computerdi.insert(computer);
	}

	@Override
	public void deleteComputerById(int computer_id) {
		computerdi.deleteComputerById(computer_id);
	}

	@Override
	public List<Computer> findOrderByComputers(int p, String req, String orderBy,
			String search) {
		return computerdi.findOrderByComputers(p, req, orderBy, search);
	}

	public int getCurrentCount() {
		return computerdi.getCurrentCount();
	}

}
