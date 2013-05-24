package com.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.om.Computer;
import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;

/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet("/computer")
public class ComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService computersi;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ComputerServlet() {
		super();
		computersi = new ComputerServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int nbComputer = computersi.countComputers();
		int p = 0;
		if (request.getParameter("p") != null) {
			p = Integer.parseInt(request.getParameter("p"));
			p++;
		}
		List<Computer> lc = computersi.getListComputers(p);
		request.setAttribute("p", p);
		System.out.println(lc);
		request.setAttribute("lc", lc);
		request.setAttribute("nbComputer", nbComputer);
		request.getRequestDispatcher("computer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
