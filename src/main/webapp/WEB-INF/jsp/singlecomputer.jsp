<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.excilys.om.Company"%>
<%@ page import="com.excilys.om.Computer"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Computers database</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="./css/main.css">
</head>
<body>

	<header class="topbar">
		<h1 class="fill">
			<a href="<c:url value="/" />">My Computer database </a>
		</h1>
	</header>

	<section id="main">
		<h1>Edit computer</h1>
		<form action="<c:url value="/SaveComputer.html?id=${computer.id}" />"
			method="POST">
			<fieldset>

				<div class="clearfix ${us.error_name}">
					<label for="c.name">Computer name</label>
					<div class="input">
						<input type="text" id="c.name" name="c.name"
							value="${computer.name}"> <span class="help-inline">Required</span>
					</div>
				</div>

				<div class="clearfix ${us.error_introducted}">
					<label for="c.introduced">Introduced date</label>
					<div class="input">
						<input type="text" id="c.introduced" name="c.introduced"
							value="${computer.introduced}"> <span class="help-inline">Date
							(&#x27;yyyy-MM-dd&#x27;)</span>
					</div>
				</div>


				<div class="clearfix ${us.error_discontinued}">
					<label for="c.discontinued">Discontinued date</label>
					<div class="input">
						<input type="text" id="c.discontinued" name="c.discontinued"
							value="${computer.discontinued}"> <span
							class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
					</div>
				</div>

				<div class="clearfix">
					<label for="c.company_id">Company</label>
					<div class="input">
						<select id="c.company_id" name="c.company_id">
							<option class="blank" value="">-- Choose a company --</option>
							<c:forEach var="company" items="${lcany}">
								<c:choose>
									<c:when test="${computer.company.id == company.id}">
										<option class="blank" value="${company.id}"
											selected="selected">${company.name}</option>
									</c:when>
									<c:otherwise>
										<option class="blank" value="${company.id}">
											${company.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select> <span class="help-inline"></span>
					</div>
				</div>
			</fieldset>
			<div class="actions">
				<input type="submit" value="Save this computer" class="btn primary">
				or <a href="<c:url value="/computersDis.html" />" class="btn">Cancel</a>
			</div>


		</form>
		<c:if test="${computer.id != -1}">


			<form
				action="<c:url value="/DeleteComputer.html?id=${computer.id}"/>"
				method="POST" class="topRight">
				<input type="submit" value="Delete this computer" class="btn danger">
			</form>
		</c:if>
	</section>
</body>
</html>

