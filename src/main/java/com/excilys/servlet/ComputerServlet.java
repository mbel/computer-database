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

	private static final String FILTRE_SEARCH = "f";
	private static final String FILTRE_ORDER = "o";
	private static final String FILTRE_BY = "s";
	private static final String PAGINATION_LEFT_BOOL = "r";
	private static final String PAGINATION = "p";
	private static final String SORT_SERVICE = "ss";
	private static final String UTILS_SERVICE = "us";
	private static final String LIST_COMPUTERS = "lc";

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

		if (request.getParameter(PAGINATION) != null) {
			p = Integer.parseInt(request.getParameter(PAGINATION));
			if (request.getParameter(PAGINATION_LEFT_BOOL) != null)
				p--;
			else
				p++;
		} else {
			sortService.defaultSet();
			sortService.setPs(request.getParameter(FILTRE_BY),
					request.getParameter(FILTRE_ORDER),
					request.getParameter(FILTRE_SEARCH));
			sortService.setCurrentCount(computersi.getCurrentCount(p,
					sortService.getReq(), sortService.getPs().getBy(),
					sortService.getPs().getSearch()));
		}

		List<Computer> lc = computersi.findOrderByComputers(p, sortService
				.getReq(), sortService.getPs().getBy(), sortService.getPs()
				.getSearch());
		request.getSession().setAttribute(SORT_SERVICE, sortService);
		request.getSession().removeAttribute(UTILS_SERVICE);
		request.setAttribute(UTILS_SERVICE, utilsService);
		request.setAttribute(PAGINATION, p);
		request.setAttribute(LIST_COMPUTERS, lc);
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
