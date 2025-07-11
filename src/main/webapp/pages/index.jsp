<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Reports App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
</head>
<body>
<div class="container">
<h3 class="pb-3 pt-3">Report Application</h3>

<form:form action="search1" modelAttribute="search" method="POST">

<table>
	<tr>
		<td>Plan Name:</td>
		<td>
			<form:select path="planName">
			<form:option value="">-select-</form:option>
			<form:options items="${names}"/>
			</form:select>
		</td>
		<td>Plan Status:</td>
		<td>
			<form:select path="planStatus">
			<form:option value="">-select-</form:option>
			<form:options items="${status}"/>
			</form:select>
		</td>
		<td>Gender:</td>
		<td>
			<form:select path="gender">
			<form:option value="">-select-</form:option>
			<form:option value="Male">Male</form:option>
			<form:option value="Fe-Male">Fe-Male</form:option>
			</form:select>
		</td>
	</tr>
	<tr>
		<td>Start Date:</td>
		<td>
		<form:input type="date" path="planStartDate"/>
		</td>
		<td>End Date:</td>
		<td>
		<form:input type="date" path="planEndDate"/>
		</td>
	</tr>
	
	<tr><td><a href="/" class="btn btn-secondary">Reset</a></td>
		<td>
		<input type="submit" value="search" class="btn btn-primary"></input>
		</td>
	</tr>
</table>

</form:form>

<hr/>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>S.No</th>
			<th>Holder Name</th>
			<th>Gender</th>
			<th>Plan Name</th>
			<th>Plane Status</th>
			<th>Start Date</th>
			<th>End Date</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${plans}" var="plan" varStatus="index">
	<tr>
	<td>${index.count}</td>
	<td>${plan.citizenName}</td>
	<td>${plan.gender}</td>
	<td>${plan.planName}</td>
	<td>${plan.planStatus}</td>
	<td>${plan.planStartDate}</td>
	<td>${plan.planEndDate}</td>
	</tr>
	</c:forEach>
	<c:if test="${empty plans}">
	<td colspan="8" style="text-align: center">No records found</td>
	</c:if>
	</tbody>
</table>

<hr/>

Export : <a href="excel">Excel</a> <a href="pdf">Pdf</a>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq"
 crossorigin="anonymous"></script>
 
</body>
</html>