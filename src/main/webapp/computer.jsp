<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.excilys.om.Computer"%>
<%@ page import="java.util.*"%>
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
			<a href="/"> Play 2.0 sample application &mdash; Computer
				database </a>
		</h1>
	</header>


	<section id="main">
		<h1 id="homeTitle">${nbComputer} computers found</h1>
		<div id="actions">
			<form action="/computers?o=desc" method="GET">
				<input type="search" id="searchbox" name="f" value=""
					placeholder="Filter by computer name..."> <input
					type="submit" id="searchsubmit" value="Filter by name"
					class="btn primary">
			</form>
			<a class="btn success" id="add" href="/computers/new">Add a new
				computer</a>
		</div>
		<table class="computers zebra-striped">
			<thead>
				<tr>

					<th class="name header headerSortDown"><a
						href="/computers?o=desc">Computer name</a></th>


					<th class="introduced header "><a
						href="/computers?s=introduced">Introduced</a></th>


					<th class="discontinued header "><a
						href="/computers?s=discontinued">Discontinued</a></th>


					<th class="company_name header "><a
						href="/computers?s=company.name">Company</a></th>

				</tr>
			</thead>
			<tbody>

				<%
					List<Computer> lc = (List<Computer>)request.getAttribute("lc");
					for (int i = 0; i < lc.size(); i++) {
						Computer computer = lc.get(i);
						request.setAttribute("computer", computer);
				%>
				<tr>
					<td><a href="/computers/${computer.id}">${computer.name}</a></td>
					<td>${computer.introduced}</td>
					<td>${computer.discontinued}</td>
					<td>${computer.company.name}</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<div id="pagination" class="pagination">
			<ul>
				<li class="prev disabled"><a>&larr; Previous</a></li>
				<li class="current"><a>Displaying ${p*10} to ${(p+1)*10} of ${nbComputer}</a></li>
				<li class="next"><a href="/computer-database/computer?p=${p}">Next &rarr;</a></li>
			</ul>
		</div>
	</section>
</body>
</html>


