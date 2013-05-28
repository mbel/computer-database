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
import com.excilys.service.SortService;

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
		int p = 0;
		SortService sortService = (SortService) request.getSession()
				.getAttribute("ss");
		if (sortService == null)
			sortService = new SortService();

		if (request.getParameter("p") != null) {
			p = Integer.parseInt(request.getParameter("p"));
			if (request.getParameter("r") != null)
				p--;
			else
				p++;
		} else {
			sortService.setBy(request.getParameter("s"));
			sortService.setOrder(request.getParameter("o"));
			sortService.setSearch(request.getParameter("f"));
			sortService.setCurrent(sortService.set());
		}

		List<Computer> lc = computersi.findOrderByComputers(p,
				sortService.getReq(), sortService.getBy(),
				sortService.getSearch());
		request.getSession().setAttribute("ss", sortService);
		request.setAttribute("p", p);
		request.setAttribute("lc", lc);
		request.setAttribute("nbComputer", computersi.countComputers());
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
