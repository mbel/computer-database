package com.excilys.service;

import java.util.List;

import com.excilys.om.Computer;

public interface ComputerService {

	int countComputers();

	Computer findComputerById(int computer_id);

	void delete(Computer computer);

	void update(Computer computer);

	void insert(Computer computer);

	void deleteComputerById(int computer_id);

	List<Computer> findOrderByComputers(int p, int req, String orderBy,
			String search);

	int getCurrentCount();

}
