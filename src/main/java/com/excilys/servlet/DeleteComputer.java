package com.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;
import com.excilys.service.UtilsService;

/**
 * Servlet implementation class DeleteComputer
 */
@WebServlet("/DeleteComputer")
public class DeleteComputer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String UTILS_SERVICE = "us";
	private static final String ID = "id";

	private ComputerService computersi;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteComputer() {
		computersi = ComputerServiceImpl.INSTANCE;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UtilsService utilsService = (UtilsService) request.getSession()
				.getAttribute(UTILS_SERVICE);
		if (utilsService == null)
			utilsService = new UtilsService();
		int computer_id = Integer.parseInt(request.getParameter(ID));
		utilsService.setMessaj(UtilsService.DELETED);
		utilsService
				.setComp(computersi.findComputerById(computer_id).getName());
		utilsService.setMaj(true);
		computersi.deleteComputerById(computer_id);
		request.getSession().setAttribute(UTILS_SERVICE, utilsService);
		response.sendRedirect("computer");
	}

}
