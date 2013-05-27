<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.excilys.om.Company"%>
<%@ page import="com.excilys.om.Computer"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Add Computer</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="./css/main.css">
</head>
<body>

	<header class="topbar">
		<h1 class="fill">
			<a href="/computer-database/">My Computer
				database </a>
		</h1>
	</header>

	<section id="main">
		<h1>Add computer</h1>
		<form action="/computer-database/SaveComputer?id=-1" method="POST">
			<fieldset>

				<div class="clearfix ">
					<label for="name">Computer name</label>
					<div class="input">
						<input type="text" id="name" name="name" required="true" value=""> <span
							class="help-inline">Required</span>
					</div>
				</div>

				<div class="clearfix ">
					<label for="introduced">Introduced date</label>
					<div class="input">
						<input type="text" id="introduced" name="introduced" value="">
						<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
					</div>
				</div>


				<div class="clearfix ">
					<label for="discontinued">Discontinued date</label>
					<div class="input">
						<input type="text" id="discontinued" name="discontinued" value="">
						<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
					</div>
				</div>

				<div class="clearfix ">
					<label for="company_id">Company</label>
					<div class="input">
						<select id="company_id" name="company_id">
							<c:choose>
								<c:when test="${computer.company != null}">
									<option class="blank" value="${computer.company.id}">
										${computer.company.name}</option>
									<option class="blank" value="">-- Choose a company --</option>
								</c:when>
								<c:otherwise>
									<option class="blank" value="">-- Choose a company --</option>
								</c:otherwise>
							</c:choose>
							<c:forEach var="company" items="${lcany}">
								<option value="${company.id}">${company.name}</option>
							</c:forEach>
						</select> <span class="help-inline"></span>
					</div>
				</div>
			</fieldset>
			<div class="actions">
				<input type="submit" value="Save this computer" class="btn primary">
				or <a href="/computer-database/" class="btn">Cancel</a>
			</div>
		</form>
	</section>
</body>
</html>
