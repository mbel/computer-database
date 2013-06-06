package com.excilys.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.controller.form.ComputerForm;
import com.excilys.om.Company;
import com.excilys.om.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.utils.ErrorUtils;

@Controller
public class CrudController {

	private static final String UTILS_SERVICE = "us";

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/SingleComputer", method = RequestMethod.GET)
	public String single(Model m, HttpSession session,
			@ModelAttribute("computer") Computer c) {
		ErrorUtils errorUtils = ErrorUtils.init(session);
		List<Company> lcany = companyService.findCompanies();
		Computer computer = null;
		computer = new Computer();
		computer.setId(-1);
		computer.setName("");
		errorUtils.setMessaj(ErrorUtils.CREATED);
		m.addAttribute("lcany", lcany);
		m.addAttribute("computer", computer);
		m.addAttribute("com", new ComputerForm());
		session.setAttribute("us", errorUtils);
		return "singlecomputer";
	}

	@RequestMapping(value = "/SingleComputer/{id}", method = RequestMethod.GET)
	public String singleWithId(Model m, HttpSession session,
			@PathVariable int id, @ModelAttribute("computer") Computer c) {
		ErrorUtils errorUtils = ErrorUtils.init(session);
		List<Company> lcany = companyService.findCompanies();
		Computer computer = null;
		computer = computerService.findComputerById(id);
		errorUtils.setMessaj(ErrorUtils.UPDATED);
		m.addAttribute("lcany", lcany);
		m.addAttribute("computer", computer);
		m.addAttribute("com", new ComputerForm());
		session.setAttribute("us", errorUtils);
		return "singlecomputer";
	}

	@RequestMapping(value = "/DeleteComputer/{id}", method = RequestMethod.POST)
	public String delete(Model m, HttpSession session, @PathVariable int id) {
		ErrorUtils errorUtils = ErrorUtils.init(session);
		errorUtils.setMessaj(ErrorUtils.DELETED);
		errorUtils.setComp(computerService.findComputerById(id).getName());
		errorUtils.setMaj(true);
		computerService.deleteComputerById(id);
		session.setAttribute(UTILS_SERVICE, errorUtils);
		return "redirect:/computers";
	}

	@RequestMapping(value = "/SaveComputer", method = RequestMethod.POST)
	public String save(Model m, HttpSession session, @Valid ComputerForm com,
			BindingResult result) {
		ErrorUtils errorUtils = ErrorUtils.init(session);
		if (result.hasErrors()) {
			errorUtils.setErrors(result.getFieldErrors());
			session.setAttribute(UTILS_SERVICE, errorUtils);
			if (com.getId() > 0)
				return "redirect:/SingleComputer/" + com.getId();
			else
				return "redirect:/SingleComputer/";
		}
		Computer cp = generateComputer(com);
		if (cp.getId() <= 0) {
			System.out.println(cp);
			System.out.println(cp.getId());
			computerService.insert(cp);
		} else {
			computerService.update(cp);
		}
		errorUtils.setComp(cp.getName());
		errorUtils.setMaj(true);
		session.setAttribute(UTILS_SERVICE, errorUtils);
		return "redirect:/computers";
	}

	private Computer generateComputer(ComputerForm computerForm) {
		Computer computer = new Computer();
		DateTime introduced = computerForm.getIntroduced();
		DateTime discontinued = computerForm.getDiscontinued();
		int company_id = computerForm.getCompany();
		computer.setName(computerForm.getName());
		if (!"".equals(introduced)) {
			computer.setIntroduced(introduced);
		} else
			computer.setIntroduced(null);
		if (!"".equals(discontinued)) {
			computer.setDiscontinued(discontinued);
		} else
			computer.setDiscontinued(null);
		if (company_id != 0)
			computer.setCompany(companyService.findCompanyById(company_id));
		else {
			computer.setCompany(null);
		}
		return computer;
	}
}
