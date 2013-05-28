<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<a href="/computer-database/">My Computer database </a>
		</h1>
	</header>

	<section id="main">
		<h1 id="homeTitle">${nbComputer} computers found</h1>
		<div id="actions">
			<form action="/computer-database/computer?s=true" method="GET">
				<input type="search" id="searchbox" name="f" value=""
					placeholder="Filter by computer name..."> <input
					type="submit" id="searchsubmit" value="Filter by name"
					class="btn primary">
			</form>
			<a class="btn success" id="add" href="/computer-database/AddComputer">Add
				a new computer</a>
		</div>
		<table class="computers zebra-striped">
			<thead>
				<tr>

					<th class="name header headerSortDown"><a
						href="/computer-database/computer?${sessionScope.ss.computer}">Computer
							name</a></th>
					<th class="introduced header "><a
						href="/computer-database/computer?${sessionScope.ss.introduced}">Introduced</a></th>
					<th class="discontinued header "><a
						href="/computer-database/computer?${sessionScope.ss.discontinued}">Discontinued</a></th>
					<th class="company_name header "><a
						href="/computer-database/computer?${sessionScope.ss.company}">Company</a></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="computer" items="${lc}">
					<tr>
						<td><a
							href="/computer-database/SingleComputer?id=${computer.id}">${computer.name}</a></td>
						<td>${computer.introduced}</td>
						<td>${computer.discontinued}</td>
						<td>${computer.company.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="pagination" class="pagination">
			<ul>
				<c:if test="${p ne 0}">
					<li class="prev"><a
						href="/computer-database/computer?p=${p}&r=true">&larr;
							Previous</a></li>
				</c:if>
				<li class="current"><a>Displaying ${p*10} to ${(p+1)*10} of
						${nbComputer}</a></li>
				<li class="next"><a
					href="/computer-database/computer?p=${p}&${sessionScope.ss.current}">Next
						&rarr;</a></li>
			</ul>
		</div>
	</section>
</body>
</html>


