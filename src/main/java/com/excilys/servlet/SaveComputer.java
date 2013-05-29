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
import com.excilys.service.UtilsService;

/**
 * Servlet implementation class SaveComputer
 */
@WebServlet("/SaveComputer")
public class SaveComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService computersi;
	private CompanyService companysi;
	private UtilsService utilsService;

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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Computer cp = null;
		utilsService = (UtilsService) request.getSession().getAttribute("us");
		utilsService.init();
		int computer_id = Integer.parseInt(request.getParameter("id"));
		String redirect = "computer";
		if (computer_id == -1) {
			cp = generateComputer(request, new Computer());
			if ("".equals(utilsService.getError_name())
					&& "".equals(utilsService.getError_introducted())
					&& "".equals(utilsService.getError_discontinued())) {
				computersi.insert(cp);
			} else {
				redirect = "AddComputer?id=-1";
			}
		} else {
			Computer computer = computersi.findComputerById(computer_id);
			cp = generateComputer(request, computer);
			if ("".equals(utilsService.getError_name())
					&& "".equals(utilsService.getError_introducted())
					&& "".equals(utilsService.getError_discontinued())) {
				computersi.update(cp);
			} else {
				redirect = "SingleComputer?id=" + computer.getId();
			}
		}
		request.getSession().setAttribute("us", utilsService);
		response.sendRedirect(redirect);
	}

	private Computer generateComputer(HttpServletRequest request,
			Computer computer) {
		String name = request.getParameter("name").toString().trim();
		if ("".equals(name)) {
			utilsService.setError_name(UtilsService.ERROR);
		} else {
			computer.setName(name);
			utilsService.setError_name("");
		}
		String introducedDate = request.getParameter("introduced");
		if (!"".equals(introducedDate)) {
			try {
				computer.setIntroduced(new java.sql.Date(stringToDate(
						introducedDate).getTime()));
				utilsService.setError_introducted("");
			} catch (ParseException e) {
				utilsService.setError_introducted(UtilsService.ERROR);
			}
		} else
			computer.setIntroduced(null);
		String discontinuedDate = request.getParameter("discontinued");
		if (!"".equals(discontinuedDate)) {
			try {
				computer.setDiscontinued(new java.sql.Date(stringToDate(
						discontinuedDate).getTime()));
				utilsService.setError_discontinued("");
			} catch (ParseException e) {
				utilsService.setError_discontinued(UtilsService.ERROR);
			}
		} else
			computer.setDiscontinued(null);
		String company_id = request.getParameter("company_id");
		if (!"".equals(company_id))
			computer.setCompany(companysi.findCompanyById(Integer
					.parseInt(company_id)));
		else
			computer.setCompany(new Company());
		return computer;
	}

	private static Date stringToDate(String sDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.parse(sDate);
	}

}
