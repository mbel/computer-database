package com.excilys.service;


public class UtilsService {

	public static final String ERROR = "error";

	private String error_name = "";
	private String error_introducted = "";
	private String error_discontinued = "";

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
	}

}
