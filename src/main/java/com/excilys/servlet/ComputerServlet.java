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
import com.excilys.service.UtilsService;

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
		computersi = ComputerServiceImpl.INSTANCE;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int p = 0;
		UtilsService utilsService = UtilsService.init(request);
		SortService sortService = SortService.init(request);

		if (request.getParameter("p") != null) {
			p = Integer.parseInt(request.getParameter("p"));
			if (request.getParameter("r") != null)
				p--;
			else
				p++;
		} else {
			sortService.defaultSet();
			sortService.setBy(request.getParameter("s"));
			sortService.setOrder(request.getParameter("o"));
			sortService.setSearch(request.getParameter("f"));
			sortService.setCurrent(sortService.set());
			sortService.setCurrentCount(computersi.getCurrentCount(p,
					sortService.getReq(), sortService.getBy(),
					sortService.getSearch()));
		}

		List<Computer> lc = computersi.findOrderByComputers(p,
				sortService.getReq(), sortService.getBy(),
				sortService.getSearch());
		request.getSession().setAttribute("ss", sortService);
		request.getSession().removeAttribute("us");
		request.setAttribute("us", utilsService);
		request.setAttribute("p", p);
		request.setAttribute("lc", lc);
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
