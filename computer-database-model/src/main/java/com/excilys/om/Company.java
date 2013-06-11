package com.excilys.om;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {

	public static final String COMPANY_ID = "cy.id";
	public static final String COMPANY_NAME = "cy.name";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	@OneToMany(mappedBy = "company")
	private Set<Computer> computers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Computer> getComputers() {
		return computers;
	}

	public void setComputers(Set<Computer> computers) {
		this.computers = computers;
	}
}
