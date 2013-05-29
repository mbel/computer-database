package com.excilys.service;

public class UtilsService {

	public static final String ERROR = "error";
	public static final String UPDATED = "updated";
	public static final String CREATED = "created";

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
		this.messaj = null;

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

}
