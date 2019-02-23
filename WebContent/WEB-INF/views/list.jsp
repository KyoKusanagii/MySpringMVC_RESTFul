<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<script
	src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous">
</script>
<script>
	$(function () {
		console.log("jQuery������\");
		$(".delete").click(function () {
			$("form").attr("action", $(this).attr("href")).submit();
			return false;
		});
	});
</script>
	<!--
		1.SpringMVC�B�z�R�A�귽�A
		�u����REST���椣�Ʊ���html�Bdo���������A�Y�NDispatcherServlet�ШD�]�m��/�A�h
		SpringMVC�N����WEB�e�����Ҧ��ШD�A�]�A�R�A�귽���ШD�ASpringMVC�|���@�Ӵ��q�ШD�ӳB�z
		�A�]�䤣������B�z�e���N�ɭP���~
		2.�ѨM��k:�bSpringMVC���t�m��󤤡A�t�m
		<mvc:default-servlet-handler/>�ѨM�R�A�귽�����D
	-->
</head>
<body>

	<form action="" method="post">
		<%--�n�ϥ�HttpHiddenFileter,��post�ШD�নdelete�ШD �N�O�o�˪��T�w�g�k--%>
		<input type="hidden" name="_method" value="delete">
	</form>
	<c:if test="${empty requestScope.employee}">�S��������u�T��</c:if>
	<c:if test="${!empty requestScope.employee}">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>���u�s��</th>
				<th>���u�m�W</th>
				<th>���u�~��</th>
				<th>���u�����W��</th>
				<th>���u�����s��</th>
				<th>�s��</th>
				<th>�R��</th>
			</tr>
			<c:forEach items="${requestScope.employee}" var="emp">
				<tr>
					<td>${emp.empId}</td>
					<td>${emp.name}</td>
					<td>${emp.age}</td>
					<td>${emp.department.departmentName}</td>
					<td>${emp.department.depId}</td>
					<td><a href="emp/${emp.empId}">�s��</a></td>
					<td><a class="delete" href="emp/${emp.empId}">�R��</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<br>
	<a href="emp">Add New Employee</a>
</body>
</html>