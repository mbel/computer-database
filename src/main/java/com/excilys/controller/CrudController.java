package com.excilys.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.controller.form.ComputerForm;
import com.excilys.om.Company;
import com.excilys.om.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.utils.ErrorUtils;

@Controller
public class CrudController {

	private static final String UTILS_SERVICE = "us";
	private static final String ID = "id";

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ComputerService computerService;

	private ErrorUtils errorUtils;

	@RequestMapping(value = "/SingleComputer", method = RequestMethod.GET)
	public String single(Model m, HttpSession session,
			@RequestParam(value = ID, defaultValue = "-1") int id,
			@ModelAttribute("computer") Computer c) {
		errorUtils = ErrorUtils.init(session);
		List<Company> lcany = companyService.findCompanies();
		Computer computer = null;
		if (id > 0) {
			computer = computerService.findComputerById(id);
			errorUtils.setMessaj(ErrorUtils.UPDATED);
		} else {
			computer = new Computer();
			computer.setId(id);
			computer.setName("");
			errorUtils.setMessaj(ErrorUtils.CREATED);
		}
		m.addAttribute("lcany", lcany);
		m.addAttribute("computer", computer);
		m.addAttribute("com", new ComputerForm());
		session.setAttribute("us", errorUtils);
		return "singlecomputer";
	}

	@RequestMapping(value = "/DeleteComputer", method = RequestMethod.POST)
	public String delete(Model m, HttpSession session,
			@RequestParam(value = ID, defaultValue = "-1") int id) {
		errorUtils = ErrorUtils.init(session);
		errorUtils.setMessaj(ErrorUtils.DELETED);
		errorUtils.setComp(computerService.findComputerById(id).getName());
		errorUtils.setMaj(true);
		computerService.deleteComputerById(id);
		session.setAttribute(UTILS_SERVICE, errorUtils);
		return "redirect:/computersDis.html";
	}

	@RequestMapping(value = "/SaveComputer", method = RequestMethod.POST)
	public String save(Model m, HttpSession session, ComputerForm com,
			BindingResult result) {
		errorUtils = (ErrorUtils) session.getAttribute(UTILS_SERVICE);
		errorUtils.init();
		Computer cp = generateComputer(com);
		if (!errorUtils.getErrors()) {
			session.setAttribute(UTILS_SERVICE, errorUtils);
			return "redirect:/SingleComputer.html?id=" + com.getId();
		}
		if (cp.getId() <= 0) {
			computerService.insert(cp);
		} else {
			computerService.update(cp);
		}
		errorUtils.setComp(cp.getName());
		errorUtils.setMaj(true);
		session.setAttribute(UTILS_SERVICE, errorUtils);
		return "redirect:/computersDis.html";
	}

	private Computer generateComputer(ComputerForm computerForm) {
		Computer computer = new Computer();
		String name = computerForm.getName();
		Date introduced = computerForm.getIntroduced();
		Date discontinued = computerForm.getDiscontinued();
		int company_id = computerForm.getCompany();
		if ("".equals(name)) {
			errorUtils.setError_name(ErrorUtils.ERROR);
		} else {
			computer.setName(name);
			errorUtils.setError_name("");
		}
		if (!"".equals(introduced)) {
			computer.setIntroduced(introduced);
			errorUtils.setError_introducted("");
		} else
			computer.setIntroduced(null);
		if (!"".equals(discontinued)) {
			computer.setDiscontinued(discontinued);
			errorUtils.setError_discontinued("");
		} else
			computer.setDiscontinued(null);
		if (!"".equals(company_id))
			computer.setCompany(companyService.findCompanyById(company_id));
		else {
			computer.setCompany(null);
		}
		return computer;
	}
}
