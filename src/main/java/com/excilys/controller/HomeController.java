package com.excilys.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.controller.form.UrlBind;
import com.excilys.om.Computer;
import com.excilys.service.ComputerService;
import com.excilys.utils.ErrorUtils;
import com.excilys.utils.SortUtils;

@Controller
public class HomeController {

	private static final String SORT_SERVICE = "ss";
	private static final String UTILS_SERVICE = "us";
	private static final String LIST_COMPUTERS = "lc";
	private static final String PAGINATION = "p";

	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/computersDis", method = RequestMethod.GET)
	public String home(Model m, HttpSession session, UrlBind urlBind) {

		ErrorUtils utilsService = ErrorUtils.init(session);
		SortUtils sortService = SortUtils.init(session);
		int p = urlBind.getP();
		if (p != -1) {
			p = urlBind.moveP();
		} else {
			p = 0;
			sortService.defaultSet();
			sortService.setPs(urlBind.getS(), urlBind.getO(), urlBind.getF());
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
