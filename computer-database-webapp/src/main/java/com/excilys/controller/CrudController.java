package com.excilys.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.controller.form.CompanyConverter;
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

	@InitBinder
	public void initBinderCompany(WebDataBinder binder) {
		binder.registerCustomEditor(Company.class, new CompanyConverter(
				companyService));
	}

	@InitBinder
	public void initBinderDate(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

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
		m.addAttribute("com", new Computer());
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
		m.addAttribute("com", computer);
		session.setAttribute("us", errorUtils);
		return "singlecomputer";
	}

	@RequestMapping(value = "/DeleteComputer/{id}", method = RequestMethod.POST)
	public String delete(Model m, HttpSession session, @PathVariable int id) {
		ErrorUtils errorUtils = ErrorUtils.init(session);
		errorUtils.setMessaj(ErrorUtils.DELETED);
		Computer c = computerService.findComputerById(id);
		errorUtils.setComp(c.getName());
		errorUtils.setMaj(true);
		computerService.delete(c);
		session.setAttribute(UTILS_SERVICE, errorUtils);
		return "redirect:/computers";
	}

	@RequestMapping(value = "/SaveComputer", method = RequestMethod.POST)
	public String save(Model m, HttpSession session,
			@ModelAttribute @Valid Computer com, BindingResult result) {
		ErrorUtils errorUtils = ErrorUtils.init(session);
		if (result.hasErrors()) {
			errorUtils.setErrors(result.getFieldErrors());
			session.setAttribute(UTILS_SERVICE, errorUtils);
			if (com.getId() > 0)
				return "redirect:/SingleComputer/" + com.getId();
			else
				return "redirect:/SingleComputer/";
		}
		if (com.getId() <= 0) {
			computerService.insert(com);
		} else {
			computerService.update(com);
		}
		errorUtils.setComp(com.getName());
		errorUtils.setMaj(true);
		session.setAttribute(UTILS_SERVICE, errorUtils);
		return "redirect:/computers";
	}
}
