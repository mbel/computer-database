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
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private static final long serialVersionUID = 1L;

	private static final String UTILS_SERVICE = "us";
	private static final String ID = "id";

	private ComputerService computersi;
	private CompanyService companysi;
	private UtilsService utilsService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveComputer() {
		super();
		computersi = ComputerServiceImpl.INSTANCE;
		companysi = CompanyServiceImpl.INSTANCE;
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
		utilsService = (UtilsService) request.getSession().getAttribute(
				UTILS_SERVICE);
		utilsService.init();
		int computer_id = Integer.parseInt(request.getParameter(ID));
		String redirect = "computer";
		if (computer_id == -1) {
			cp = generateComputer(request, new Computer());
			if ("".equals(utilsService.getError_name())
					&& "".equals(utilsService.getError_introducted())
					&& "".equals(utilsService.getError_discontinued())) {
				computersi.insert(cp);
				utilsService.setComp(cp.getName());
				utilsService.setMaj(true);
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
				utilsService.setComp(cp.getName());
				utilsService.setMaj(true);
			} else {
				redirect = "SingleComputer?id=" + computer.getId();
			}
		}
		request.getSession().setAttribute(UTILS_SERVICE, utilsService);
		response.sendRedirect(redirect);
	}

	private Computer generateComputer(HttpServletRequest request,
			Computer computer) {
		String name = request.getParameter(Computer.COMPUTER_NAME).toString().trim();
		if ("".equals(name)) {
			utilsService.setError_name(UtilsService.ERROR);
		} else {
			computer.setName(name);
			utilsService.setError_name("");
		}
		String introducedDate = request.getParameter(Computer.COMPUTER_INTRODUCED);
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
		String discontinuedDate = request.getParameter(Computer.COMPUTER_DISCONTINUED);
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
		String company_id = request.getParameter(Computer.COMPUTER_COMPANY);
		if (!"".equals(company_id))
			computer.setCompany(companysi.findCompanyById(Integer
					.parseInt(company_id)));
		else {
			computer.setCompany(new Company());
		}
		return computer;
	}

	private static Date stringToDate(String sDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		return formatter.parse(sDate);
	}

}
