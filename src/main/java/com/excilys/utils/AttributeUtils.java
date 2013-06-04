package com.excilys.utils;

public class AttributeUtils {

	private String name;
	private String header;
	private static String ASC;
	private static String DESC;

	public static final String HEADER_DOWN = "headerSortDown";
	public static final String HEADER_UP = "headerSortUp";

	public AttributeUtils(String name, String desc) {
		setName(name);
		setASC(name);
		setDESC(desc);
		setHeader("");
	}

	public AttributeUtils set(String asc, String desc) {
		if (name.equals(asc)) {
			this.update(desc, HEADER_DOWN);
		} else {
			this.update(asc, HEADER_UP);
		}
		return this;
	}

	public void init(String asc) {
		setHeader("");
		setName(asc);
	}

	public void update(String n, String h) {
		setName(n);
		setHeader(h);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getASC() {
		return ASC;
	}

	public static void setASC(String aSC) {
		ASC = aSC;
	}

	public static String getDESC() {
		return DESC;
	}

	public static void setDESC(String dESC) {
		DESC = dESC;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "AttributeUtils [name=" + name + ", header=" + header + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttributeUtils other = (AttributeUtils) obj;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
