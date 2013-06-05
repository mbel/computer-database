package com.excilys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.om.Company;
import com.excilys.om.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.utils.ErrorUtils;

@Controller
public class CrudController {

	private static final String UTILS_SERVICE = "us";
	private static final String ID = "id";
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ComputerService computerService;

	private ErrorUtils utilsService;

	@RequestMapping(value = "/SingleComputer", method = RequestMethod.GET)
	public String single(Model m, HttpSession session,
			@RequestParam(value = ID, defaultValue = "-1") int id) {
		utilsService = ErrorUtils.init(session);
		List<Company> lcany = companyService.findCompanies();
		Computer computer = null;
		if (id > 0) {
			computer = computerService.findComputerById(id);
			utilsService.setMessaj(ErrorUtils.UPDATED);
		} else {
			computer = new Computer();
			computer.setId(id);
			computer.setName("");
			utilsService.setMessaj(ErrorUtils.CREATED);
		}
		m.addAttribute("lcany", lcany);
		m.addAttribute("computer", computer);
		session.setAttribute("us", utilsService);
		return "singlecomputer";
	}

	@RequestMapping(value = "/DeleteComputer", method = RequestMethod.POST)
	public String delete(Model m, HttpSession session,
			@RequestParam(value = ID, defaultValue = "-1") int id) {
		utilsService = ErrorUtils.init(session);
		utilsService.setMessaj(ErrorUtils.DELETED);
		utilsService.setComp(computerService.findComputerById(id).getName());
		utilsService.setMaj(true);
		computerService.deleteComputerById(id);
		session.setAttribute(UTILS_SERVICE, utilsService);
		return "redirect:/computersDis.html";
	}

	@RequestMapping(value = "/SaveComputer", method = RequestMethod.POST)
	public String save(
			Model m,
			HttpSession session,
			@RequestParam(value = ID, defaultValue = "-1") int id,
			@RequestParam(value = Computer.COMPUTER_NAME, defaultValue = "false") String name,
			@RequestParam(value = Computer.COMPUTER_INTRODUCED, defaultValue = "c.name") String introduced,
			@RequestParam(value = Computer.COMPUTER_DISCONTINUED, defaultValue = "DESC") String discontinued,
			@RequestParam(value = Computer.COMPUTER_COMPANY, defaultValue = "") String company_id) {
		Computer cp = null;
		utilsService = (ErrorUtils) session.getAttribute(UTILS_SERVICE);
		utilsService.init();
		String redirect = "redirect:/computersDis.html";
		if (id == -1) {
			cp = generateComputer(name, introduced, discontinued, company_id,
					new Computer());
			if ("".equals(utilsService.getError_name())
					&& "".equals(utilsService.getError_introducted())
					&& "".equals(utilsService.getError_discontinued())) {
				computerService.insert(cp);
				utilsService.setComp(cp.getName());
				utilsService.setMaj(true);
			} else {
				redirect = "redirect:/SaveComputer.html?id=-1";
			}
		} else {
			Computer computer = computerService.findComputerById(id);
			cp = generateComputer(name, introduced, discontinued, company_id,
					computer);
			if ("".equals(utilsService.getError_name())
					&& "".equals(utilsService.getError_introducted())
					&& "".equals(utilsService.getError_discontinued())) {
				computerService.update(cp);
				utilsService.setComp(cp.getName());
				utilsService.setMaj(true);
			} else {
				redirect = "redirect:/SingleComputer.html?id="
						+ computer.getId();
			}
		}
		session.setAttribute(UTILS_SERVICE, utilsService);
		System.out.println(redirect);
		return redirect;
	}

	private Computer generateComputer(String name, String introduced,
			String discontinued, String company_id, Computer computer) {
		if ("".equals(name)) {
			utilsService.setError_name(ErrorUtils.ERROR);
		} else {
			computer.setName(name);
			utilsService.setError_name("");
		}
		if (!"".equals(introduced)) {
			try {
				computer.setIntroduced(new java.sql.Date(stringToDate(
						introduced).getTime()));
				utilsService.setError_introducted("");
			} catch (ParseException e) {
				utilsService.setError_introducted(ErrorUtils.ERROR);
			}
		} else
			computer.setIntroduced(null);
		if (!"".equals(discontinued)) {
			try {
				computer.setDiscontinued(new java.sql.Date(stringToDate(
						discontinued).getTime()));
				utilsService.setError_discontinued("");
			} catch (ParseException e) {
				utilsService.setError_discontinued(ErrorUtils.ERROR);
			}
		} else
			computer.setDiscontinued(null);
		if (!"".equals(company_id))
			computer.setCompany(companyService.findCompanyById(Integer
					.parseInt(company_id)));
		else {
			computer.setCompany(new Company());
		}
		return computer;
	}

	private static Date stringToDate(String sDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		return formatter.parse(sDate);
	}
}
