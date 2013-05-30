package com.excilys.dao;

import java.util.List;

import com.excilys.om.Computer;

public interface ComputerDao {

	String SELECT_ORDER_BY = "SELECT SQL_CALC_FOUND_ROWS c.id,c.name,c.introduced,c.discontinued,c.company_id,cy.name FROM computer c left join company cy on (cy.id=c.company_id) %s order by ISNULL(%s),%s %s LIMIT ?,10 ";

	String SEARCH = "where c.name like ?";
	String ROW_CPT = "SELECT FOUND_ROWS() as cpt";

	String SELECT_BY_ID = "SELECT c.id,c.name,c.introduced,c.discontinued,c.company_id,cy.name FROM computer left join company cy on (cy.id=c.company_id) where id=?";
	String UPDATE = "update computer set name=?,introduced=?,discontinued=?,company_id=? where id=?";
	String INSERT = "insert into computer(name,introduced,discontinued,company_id) values(?,?,?,?)";
	String DELETE_BY_ID = "DELETE from computer WHERE id = ?";

	Computer findComputerById(int computer_id);

	void delete(Computer computer);

	void update(Computer computer);

	void insert(Computer computer);

	void deleteComputerById(int computer_id);

	List<Computer> findOrderByComputers(int p, String req, String orderBy,
			String search);

	int getCurrentCount();
}
