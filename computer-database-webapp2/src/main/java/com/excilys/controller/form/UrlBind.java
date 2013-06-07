package com.excilys.controller.form;

public class UrlBind {

	private int p;
	private boolean r;
	private String s;
	private String o;
	private String f;

	public UrlBind() {
		setP(-1);
		setS("c.name");
		setO("DESC");
		setF("");
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public boolean isR() {
		return r;
	}

	public void setR(boolean r) {
		this.r = r;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public int moveP() {
		if (this.isR())
			p--;
		else
			p++;
		return p;
	}

}
