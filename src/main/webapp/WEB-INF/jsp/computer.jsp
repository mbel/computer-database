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
	href="<c:url value="/resources/css/bootstrap.min.css" /> ">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/main.css" /> ">
</head>
<body>

	<header class="topbar">
		<h1 class="fill">
			<a href="/computer-database/">My Computer database</a>
		</h1>
	</header>

	<section id="main">
		<c:choose>
			<c:when test="${ss.ps.currentCount eq 0}">
				<h1 id="homeTitle">No computers found</h1>
			</c:when>
			<c:otherwise>
				<h1 id="homeTitle">${ss.ps.currentCount} computers found</h1>
			</c:otherwise>
		</c:choose>
		<c:if test="${us.maj}">
			<div class="alert-message warning">
				<strong>Done!</strong> Computer ${us.comp} has been ${us.messaj} !
			</div>
		</c:if>
		<div id="actions">
			<form action="<c:url value="/computers?s=true" /> " method="GET">
				<input type="search" id="searchbox" name="f" value=""
					placeholder="Filter by computer name..."> <input
					type="submit" id="searchsubmit" value="Filter by name"
					class="btn primary">
			</form>
			<a class="btn success" id="add"
				href="<c:url value="/SingleComputer/" />">Add a new computer</a>
		</div>
		<c:choose>
			<c:when test="${ss.ps.currentCount eq 0}">
				<div class="well">
					<em>Nothing to display</em>
				</div>
			</c:when>
			<c:otherwise>
				<table class="computers zebra-striped">
					<thead>
						<tr>
							<th class="name header ${ss.computer_att.header}"><a
								href="<c:url value="/computers?${ss.computer_att.name}" /> ">Computer
									name</a></th>
							<th class="introduced header ${ss.introduced_att.header}"><a
								href="<c:url value="/computers?${ss.introduced_att.name}" />">Introduced</a>
							</th>
							<th class="discontinued header ${ss.discontinued_att.header}"><a
								href="<c:url value="/computers?${ss.discontinued_att.name}" />">Discontinued</a></th>
							<th class="company_name header ${ss.company_att.header}"><a
								href="<c:url value="/computers?${ss.company_att.name}" />">Company</a></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="computer" items="${lc}">
							<tr>
								<td><a
									href="<c:url value="/SingleComputer/${computer.id}" />">${computer.name}</a></td>
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
									href="<c:url value="/computers?p=${p}&r=true" /> ">&larr;
										Previous</a></li>
							</c:when>
							<c:otherwise>
								<li class="prev disabled"><a>&larr; Previous</a></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${(p+1)*10 < ss.ps.currentCount}">
								<li class="current"><a>Displaying ${p*10} to
										${(p+1)*10} of ${ss.ps.currentCount}</a></li>
								<li class="next"><a
									href="<c:url value="/computers?p=${p}&${ss.ps.current.name}" /> ">Next
										&rarr;</a></li>
							</c:when>
							<c:otherwise>
								<li class="current"><a>Displaying ${p*10} to
										${ss.ps.currentCount} of ${ss.ps.currentCount}</a></li>
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


