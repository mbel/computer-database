package com.excilys.servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class SingleComputer
 */
@WebServlet("/SingleComputer")
public class SingleComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CompanyService companysi;
	private ComputerService computersi;

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleComputer() {
        super();
        companysi = new CompanyServiceImpl();
        computersi = new ComputerServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> lcany = companysi.findCompanies();
		int company_choose = Integer.parseInt(request.getParameter("id"));
		Computer computer = computersi.findComputerById(company_choose);
		request.setAttribute("lcany", lcany);
		request.setAttribute("company_choose", computer.getName());

		request.getRequestDispatcher("singlecomputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
