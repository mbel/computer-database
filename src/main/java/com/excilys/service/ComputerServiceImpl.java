package com.excilys.service;

import java.util.List;

import com.excilys.dao.ComputerDao;
import com.excilys.dao.ComputerDaoImpl;
import com.excilys.dao.DsFact;
import com.excilys.om.Computer;

public enum ComputerServiceImpl implements ComputerService {

	INSTANCE;

	private ComputerDao computerdi;

	private ComputerServiceImpl() {
		computerdi = ComputerDaoImpl.INSTANCE;
	}

	private void closeConnection() {
		DsFact.INSTANCE.closeConnection();
	}

	@Override
	public Computer findComputerById(int computer_id) {
		try {
			return computerdi.findComputerById(computer_id);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void delete(Computer computer) {
		try {
			computerdi.delete(computer);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void update(Computer computer) {
		try {
			computerdi.update(computer);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void insert(Computer computer) {
		try {
			computerdi.insert(computer);
		} finally {
			closeConnection();
		}
	}

	@Override
	public void deleteComputerById(int computer_id) {
		try {
			computerdi.deleteComputerById(computer_id);
		} finally {
			closeConnection();
		}
	}

	@Override
	public List<Computer> findOrderByComputers(int p, String req,
			String orderBy, String search) {
		try {
			return computerdi.findOrderByComputers(p, req, orderBy, search);
		} finally {
			closeConnection();
		}
	}

	public int getCurrentCount(int p, String req, String orderBy, String search) {
		try {
			return computerdi.getCurrentCount(p, req, orderBy, search);
		} finally {
			closeConnection();
		}
	}

}
