<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>











<!DOCTYPE html>
<html>
<head>
<title>Computers database</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="/css/main.css">
</head>
<body>

	<header class="topbar">
		<h1 class="fill">
			<a href="/"> Play 2.0 sample application &mdash; Computer
				database </a>
		</h1>
	</header>

	<section id="main">
		<h1 id="homeTitle">574 computers found</h1>
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
				<tr>
					<td><a href="/computers/381">ACE</a></td>
					<td><em>-</em></td>
					<td><em>-</em></td>
					<td><em>-</em></td>
				</tr>
				<tr>
					<td><a href="/computers/501">AN/FSQ-32</a></td>
					<td>01 janv. 1960</td>
					<td><em>-</em></td>
					<td>IBM</td>
				</tr>
				<tr>
					<td><a href="/computers/500">AN/FSQ-7</a></td>
					<td>01 janv. 1958</td>
					<td><em>-</em></td>
					<td>IBM</td>
				</tr>
				<tr>
					<td><a href="/computers/388">APEXC</a></td>
					<td><em>-</em></td>
					<td><em>-</em></td>
					<td><em>-</em></td>
				</tr>
				<tr>
					<td><a href="/computers/355">ARRA</a></td>
					<td><em>-</em></td>
					<td><em>-</em></td>
					<td><em>-</em></td>
				</tr>

				<tr>
					<td><a href="/computers/385">ASCI Blue Mountain</a></td>
					<td><em>-</em></td>
					<td><em>-</em></td>
					<td><em>-</em></td>
				</tr>
				<tr>
					<td><a href="/computers/313">ASCI Blue Pacific</a></td>
					<td>01 janv. 1998</td>
					<td><em>-</em></td>
					<td>IBM</td>
				</tr>
				<tr>
					<td><a href="/computers/384">ASCI Purple</a></td>
					<td>01 janv. 2005</td>
					<td><em>-</em></td>
					<td>IBM</td>
				</tr>
				<tr>
					<td><a href="/computers/382">ASCI Red</a></td>
					<td><em>-</em></td>
					<td><em>-</em></td>
					<td><em>-</em></td>
				</tr>
				<tr>
					<td><a href="/computers/383">ASCI Thors Hammer</a></td>
					<td><em>-</em></td>
					<td><em>-</em></td>
					<td><em>-</em></td>
				</tr>
			</tbody>
		</table>
		<div id="pagination" class="pagination">
			<ul>
				<li class="prev disabled"><a>&larr; Previous</a></li>
				<li class="current"><a>Displaying 1 to 10 of 574</a></li>
				<li class="next"><a href="/computers?p=1">Next &rarr;</a></li>
			</ul>
		</div>
	</section>
</body>
</html>


