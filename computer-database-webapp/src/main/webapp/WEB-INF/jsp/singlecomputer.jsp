<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.excilys.om.Company"%>
<%@ page import="com.excilys.om.Computer"%>
<%@ page import="java.util.*"%><%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Computers database</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/bootstrap.min.css" /> ">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/main.css" /> ">
</head>
<body>

	<header class="topbar">
		<h1 class="fill">
			<a href="<c:url value="/" />">My Computer database </a>
		</h1>
	</header>
	<section id="main">
		<h1>Edit computer</h1>
		<c:url value="/SaveComputer?id=${computer.id}" var="urlSave" />
		<form:form action="${urlSave}" method="POST" modelAttribute="com">
			<fieldset>
				<form:input type="hidden" path="id" value="${computer.id}" />
				<div class="clearfix ${us.error_name}">
					<form:label path="name" for="c.name">Computer name</form:label>
					<div class="input">
						<form:input path="name" type="text" id="c.name" name="c.name"
							value="${computer.name}" />
						<span class="help-inline">Required</span>
					</div>
				</div>

				<div class="clearfix ${us.error_introducted}">
					<form:label path="introduced" for="c.introduced">Introduced date</form:label>
					<div class="input">
						<form:input path="introduced" type="text" id="c.introduced"
							name="c.introduced" value="${computer.introduced}" />
						<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
					</div>
				</div>


				<div class="clearfix ${us.error_discontinued}">
					<form:label path="discontinued" for="c.discontinued">Discontinued date</form:label>
					<div class="input">
						<form:input path="discontinued" type="text" id="c.discontinued"
							name="c.discontinued" value="${computer.discontinued}" />
						<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
					</div>
				</div>

				<div class="clearfix">
					<label for="c.company_id">Company</label>
					<div class="input">


						<form:select path="company" id="c.company_id" name="c.company_id">
							<form:option class="blank" value="" label="Choose a company" />
							<c:forEach var="company" items="${lcany}">
								<c:choose>
									<c:when test="${computer.company.id == company.id}">
										<form:option class="blank" value="${company.id}"
											selected="selected" label="${company.name}" />
									</c:when>
									<c:otherwise>
										<form:option class="blank" value="${company.id}"
											label="${company.name}" />
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>

						<span class="help-inline"></span>
					</div>
				</div>


			</fieldset>
			<div class="actions">
				<input type="submit" value="Save this computer" class="btn primary">
				or <a href="<c:url value="/computers" />" class="btn">Cancel</a>
			</div>
		</form:form>
		<c:if test="${computer.id != -1}">
			<form action="<c:url value="/DeleteComputer/${computer.id}"/>"
				method="POST" class="topRight">
				<input type="submit" value="Delete this computer" class="btn danger">
			</form>
		</c:if>
	</section>

</body>
</html>

