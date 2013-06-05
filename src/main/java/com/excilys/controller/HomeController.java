package com.excilys.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.om.Computer;
import com.excilys.service.ComputerService;
import com.excilys.utils.ErrorUtils;
import com.excilys.utils.SortUtils;

@Controller
public class HomeController {

	private static final String FILTRE_SEARCH = "f";
	private static final String FILTRE_ORDER = "o";
	private static final String FILTRE_BY = "s";
	private static final String PAGINATION_LEFT_BOOL = "r";
	private static final String PAGINATION = "p";
	private static final String SORT_SERVICE = "ss";
	private static final String UTILS_SERVICE = "us";
	private static final String LIST_COMPUTERS = "lc";

	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/computersDis", method = RequestMethod.GET)
	public String home(
			Model m,
			HttpSession session,
			@RequestParam(value = PAGINATION, defaultValue = "-1") int p,
			@RequestParam(value = PAGINATION_LEFT_BOOL, defaultValue = "false") boolean r,
			@RequestParam(value = FILTRE_BY, defaultValue = "c.name") String by,
			@RequestParam(value = FILTRE_ORDER, defaultValue = "DESC") String order,
			@RequestParam(value = FILTRE_SEARCH, defaultValue = "") String search) {

		ErrorUtils utilsService = ErrorUtils.init(session);
		SortUtils sortService = SortUtils.init(session);

		if (p != -1) {
			if (r != false)
				p--;
			else
				p++;
		} else {
			p = 0;
			sortService.defaultSet();
			sortService.setPs(by, order, search);
			sortService.setCurrentCount(computerService.getCurrentCount(p,
					sortService.getReq(), sortService.getPs().getBy(),
					sortService.getPs().getSearch()));
		}

		List<Computer> lc = computerService.findOrderByComputers(p, sortService
				.getReq(), sortService.getPs().getBy(), sortService.getPs()
				.getSearch());
		session.setAttribute(SORT_SERVICE, sortService);
		session.removeAttribute(UTILS_SERVICE);
		m.addAttribute(UTILS_SERVICE, utilsService);
		m.addAttribute(PAGINATION, p);
		m.addAttribute(LIST_COMPUTERS, lc);
		return "computer";
	}

}
