package com.excilys.tag;


public class ComputerTag  {

//
//	private static final long serialVersionUID = 1L;
//	private List<Computer> lc;
//	
//	private int counter = 0;
//
//	public int doStartTag() throws JspException {
//		prout();
//		return EVAL_BODY_INCLUDE;
//	}
//
//	@Override
//	public int doEndTag() throws JspException {
//		counter = 0;
//		return super.doEndTag();
//	}
//
//	public int doAfterBody() throws JspException  {
//		
//		if (counter >= lc.size()) {
//			return SKIP_BODY;
//		}
//		prout();
//		return EVAL_BODY_AGAIN;
//	}
//	
//	public void prout () throws JspException {
//		try {
//			pageContext.setAttribute("computer", lc.get(counter));
//		} catch (Exception e) {
//			throw new JspException(e);
//		}
//		counter++;
//	}
}
