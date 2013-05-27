package com.excilys.service;

import java.util.List;

import com.excilys.om.Computer;

public interface ComputerService {

	int countComputers();

	List<Computer> findComputers(int p);

	Computer findComputerById(int computer_id);

	void delete(Computer computer);

	void update(Computer computer);

	void insert(Computer computer);

	void deleteComputerById(int computer_id);

}
