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

		<c:choose>
			<c:when test="${ss.currentCount eq 0}">
				<h1 id="homeTitle">No computers found</h1>
			</c:when>
			<c:otherwise>
				<h1 id="homeTitle">${ss.currentCount} computers found</h1>
			</c:otherwise>
		</c:choose>

		<c:if test="${us.maj}">
			<div class="alert-message warning">
				<strong>Done!</strong> Computer ${us.comp} has been ${us.messaj} !
			</div>
		</c:if>


		<div id="actions">
			<form action="/computer-database/computer?s=true" method="GET">
				<input type="search" id="searchbox" name="f" value=""
					placeholder="Filter by computer name..."> <input
					type="submit" id="searchsubmit" value="Filter by name"
					class="btn primary">
			</form>
			<a class="btn success" id="add"
				href="/computer-database/SingleComputer?id=-1">Add a new
				computer</a>
		</div>

		<c:choose>
			<c:when test="${ss.currentCount eq 0}">
				<div class="well">
					<em>Nothing to display</em>
				</div>

			</c:when>
			<c:otherwise>
				<table class="computers zebra-striped">
					<thead>
						<tr>

							<th class="name header ${ss.computer_header}"><a
								href="/computer-database/computer?${ss.computer}">Computer
									name</a></th>
							<th class="introduced header ${ss.introduced_header}"><a
								href="/computer-database/computer?${ss.introduced}">Introduced</a></th>
							<th class="discontinued header ${ss.discontinued_header}"><a
								href="/computer-database/computer?${ss.discontinued}">Discontinued</a></th>
							<th class="company_name header ${ss.company_header}"><a
								href="/computer-database/computer?${ss.company}">Company</a></th>
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
						<c:choose>
							<c:when test="${p ne 0}">
								<li class="prev"><a
									href="/computer-database/computer?p=${p}&r=true">&larr;
										Previous</a></li>
							</c:when>
							<c:otherwise>
								<li class="prev disabled"><a>&larr; Previous</a></li>
							</c:otherwise>
						</c:choose>



						<c:choose>
							<c:when test="${(p+1)*10 < ss.currentCount}">
								<li class="current"><a>Displaying ${p*10} to
										${(p+1)*10} of ${ss.currentCount}</a></li>
								<li class="next"><a
									href="/computer-database/computer?p=${p}&${ss.current}">Next
										&rarr;</a></li>
							</c:when>
							<c:otherwise>
								<li class="current"><a>Displaying ${p*10} to
										${ss.currentCount} of ${ss.currentCount}</a></li>
								<li class="prev disabled"><a>Next &rarr;</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>
			</c:otherwise>
		</c:choose>

	</section>
</body>
</html>


