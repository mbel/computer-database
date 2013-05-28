package com.excilys.dao;

import java.util.List;

import com.excilys.om.Computer;

public interface ComputerDao {

	String SELECT_ALL = "SELECT * FROM computer limit 10";
	String COUNT = "SELECT COUNT(*) FROM computer";
	String COUNT_PARAM = "COUNT(*)";

	String SELECT_ORDER_BY = "SELECT c.id,c.name,c.introduced,c.discontinued,c.company_id FROM computer c %s order by ISNULL(%s),%s LIMIT ?,10 ";
	String SELECT_ORDER_BY_DESC = "SELECT c.id,c.name,c.introduced,c.discontinued,c.company_id FROM computer c %s order by ISNULL(%s),%s DESC LIMIT ?,10 ";

	String SELECT_ORDER_BY_JOIN = "SELECT c.id,c.name,c.introduced,c.discontinued,c.company_id FROM computer c %s left join company on (company.id=c.company_id) order by ISNULL(%s),%s  LIMIT ?,10 ";
	String SELECT_ORDER_BY_DESC_JOIN = "SELECT c.id,c.name,c.introduced,c.discontinued,c.company_id FROM computer c %s left join company on (company.id=c.company_id) order by ISNULL(%s),%s  DESC LIMIT ?,10 ";

	String SEARCH = "where name like ?";

	String SELECT_BY_ID = "SELECT * FROM computer where id=?";
	String DELETE = "DELETE from computer WHERE id = ?";
	String UPDATE = "update computer set name=?,introduced=?,discontinued=?,company_id=? where id=?";
	String INSERT = "insert into computer(name,introduced,discontinued,company_id) values(?,?,?,?)";
	String DELETE_BY_ID = "DELETE from computer WHERE id = ?";

	List<Computer> findComputers();

	int countComputers();

	Computer findComputerById(int computer_id);

	void delete(Computer computer);

	void update(Computer computer);

	void insert(Computer computer);

	void deleteComputerById(int computer_id);

	List<Computer> findOrderByComputers(int p, int req, String orderBy,
			String search);

}
