package com.excilys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.ComputerDao;
import com.excilys.om.Computer;
import com.excilys.service.ComputerService;

@Service("computerServiceImpl")
@Transactional
public class ComputerServiceImpl implements ComputerService {

	@Autowired
	private ComputerDao computerdi;

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
	public List<Computer> findOrderByComputers(int p, String req,
			String orderBy, String search) {
		return computerdi.findOrderByComputers(p, req, orderBy, search);
	}

	public int getCurrentCount(int p, String req, String orderBy, String search) {
		return computerdi.getCurrentCount(p, req, orderBy, search);
	}

}
