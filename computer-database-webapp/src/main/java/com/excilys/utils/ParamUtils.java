package com.excilys.utils;


public class ParamUtils {

//	private int p;
//	private boolean r;
	private String order;
	private String by;
	private String search;
	private AttributeUtils current;
	private int currentCount;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public AttributeUtils getCurrent() {
		return current;
	}

	public void setCurrent(AttributeUtils current) {
		this.current = current;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

}
