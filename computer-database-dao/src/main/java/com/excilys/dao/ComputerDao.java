package com.excilys.dao;

import java.util.List;

import com.excilys.om.Computer;

public interface ComputerDao {

	String SELECT_ORDER_BY = "FROM Computer c left outer join fetch l.company cy %s order by isnull(%s),%s %s ";
	String SELECT_COUNT = "SELECT count(*) as cpt FROM Computer where c.name like :search";

	String CPT = "cpt";
	int LIMIT = 10;
	String PERCENT = "%";

	Computer findComputerById(int computer_id);

	void delete(Computer computer);

	void update(Computer computer);

	void insert(Computer computer);

	List<Computer> findOrderByComputers(int p, String req, String orderBy,
			String search);

	int getCurrentCount(int p, String req, String orderBy, String search);
}
