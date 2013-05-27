package com.excilys.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.om.Company;
import com.excilys.om.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;

/**
 * Servlet implementation class SaveComputer
 */
@WebServlet("/SaveComputer")
public class SaveComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService computersi;
	private CompanyService companysi;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveComputer() {
		super();
		computersi = new ComputerServiceImpl();
		companysi = new CompanyServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int computer_id = Integer.parseInt(request.getParameter("id"));
		if (computer_id == -1) {
			computersi.insert(GenerateComputer(request, new Computer()));
		} else {
			Computer computer = computersi.findComputerById(computer_id);
			computersi.update(GenerateComputer(request, computer));
		}
		response.sendRedirect("computer");
	}

	private Computer GenerateComputer(HttpServletRequest request,
			Computer computer) {
		computer.setName(request.getParameter("name").toString());
		String introducedDate = request.getParameter("introduced");
		if (!"".equals(introducedDate))
			computer.setIntroduced(new java.sql.Date(stringToDate(
					introducedDate).getTime()));
		else
			computer.setIntroduced(null);
		String discontinuedDate = request.getParameter("discontinued");
		if (!"".equals(discontinuedDate))
			computer.setDiscontinued(new java.sql.Date(stringToDate(
					discontinuedDate).getTime()));
		else
			computer.setDiscontinued(null);
		String company_id = request.getParameter("company_id");
		System.out.println(company_id);
		if (!"".equals(company_id))
			computer.setCompany(companysi.findCompanyById(Integer
					.parseInt(company_id)));
		else
			computer.setCompany(new Company());
		return computer;
	}

	private static Date stringToDate(String sDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
