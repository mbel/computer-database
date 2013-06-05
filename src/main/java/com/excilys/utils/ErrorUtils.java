package com.excilys.utils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

public class ErrorUtils {

	public static final String ERROR = "error";
	public static final String UPDATED = "updated";
	public static final String CREATED = "created";
	public static final String DELETED = "deleted";

	private String error_name = "";
	private String error_introducted = "";
	private String error_discontinued = "";

	private boolean maj;
	private String messaj;
	private String comp;

	public String getError_name() {
		return error_name;
	}

	public void setError_name(String error_name) {
		this.error_name = error_name;
	}

	public String getError_introducted() {
		return error_introducted;
	}

	public void setError_introducted(String error_introducted) {
		this.error_introducted = error_introducted;
	}

	public String getError_discontinued() {
		return error_discontinued;
	}

	public void setError_discontinued(String error_discontinued) {
		this.error_discontinued = error_discontinued;
	}

	public void init() {
		this.error_discontinued = "";
		this.error_introducted = "";
		this.error_name = "";
		this.comp = null;
		this.maj = false;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public String getMessaj() {
		return messaj;
	}

	public void setMessaj(String messaj) {
		this.messaj = messaj;
	}

	public boolean isMaj() {
		return maj;
	}

	public void setMaj(boolean maj) {
		this.maj = maj;
	}

	public boolean getErrors() {
		return "".equals(getError_name()) && "".equals(getError_introducted())
				&& "".equals(getError_discontinued());
	}

	@PostConstruct
	public static ErrorUtils init(HttpSession session) {
		ErrorUtils utilsService = (ErrorUtils) session.getAttribute("us");
		if (utilsService == null)
			utilsService = new ErrorUtils();
		return utilsService;
	}
}
