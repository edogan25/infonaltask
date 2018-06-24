<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="/resources/css/jquery-ui.css">
	<script src='https://www.google.com/recaptcha/api.js'></script>
	<script src="/resources/js/jquery-2.1.0.js"></script>
<script src="/resources/js/jquery-ui.js"></script>
<script src="/resources/js/jqueyMask.js"></script>
<title>INFONAL SYSTEMS</title>

</head>
<body>

	<div id="dialog-confirm" title="Are you sure you want to delete this?"></div>


	<div id="dialog" title="Update Screen">
	<h3>Update User</h3>
		<p>
			Name: <input id="editName" type="text" placeholder="" /> </br> Surname: <input
				id="editSurname" type="text" placeholder="" /> </br> Phone Number: <input
				id="editPhoneNo" type="text" placeholder="" />
			<button id="dialogEditButton">Edit</button>
			<button id="dialogCloseButton">Cancel</button>
			</br> </br> </br>
		</p>
	</div>

	<h3>Add User</h3>
	<form:form id="myUserForm" modelAttribute="userForm" action="submitedform">
		<fieldset>
			<table>
				<tr>
					<td>Name</td>
					<td>:</td>
					<td><form:input path="userName" /></td>
				</tr>
				<tr>
					<td>Surname</td>
					<td>:</td>
					<td><form:input path="userSurname" /></td>
				</tr>
				<tr>
					<td>Phone Number</td>
					<td>:</td>
					<td><form:input id="phoneNo" path="phoneNo" /></td>
				</tr>
			</table>
			<br />
			<div class="g-recaptcha" data-sitekey="6LcPb2AUAAAAAL9UM2QsEhqASXOA8dcIl4xKpT8b"></div>
			<br />
			<div class="form-buttons">
				<div class="button">
					<input type="submit" id="save" value="Save" />
				</div>
			</div>
		</fieldset>
	</form:form>


	<div style="color: red;">${wrongCaptcha}</div>


	<h1>Users List</h1>


		<table border="1px" cellpadding="1" cellspacing="1">
			<thead>
				<tr>
					<th width="25%">Name</th>
					<th width="20%">Surname</th>
					<th width="25%">Phone Number</th>
					<th width="10%">Controls</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${myUserList}" var="i">
				<tr id="${i.userId}table">
					<td id="${i.userId}tablename">${i.userName}</td>
					<td id="${i.userId}tablesurname">${i.userSurname}</td>
					<td id="${i.userId}tablephoneno">${i.phoneNo}</td>
					<td><button class="opener" id="${i.userId}opener">Edit</button>
						<button class="remover" id="${i.userId}remover">Delete</button> </td>
				</tr>
			</c:forEach>
			</tbody>
		</table>

	<script src="<c:url value='/resources/js/mainScript.js'/>"></script>
</body>
</html>