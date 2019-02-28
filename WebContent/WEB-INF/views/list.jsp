<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		console.log("jQuery獲取成功");
		$(".delete").click(function () {
			$("form").attr("action", $(this).attr("href")).submit();
			return false;
		});
	});
</script>
	<!--
		1.SpringMVC處理靜態資源，
		優雅的REST風格不希望後綴有html、do等等的後綴，若將DispatcherServlet請求設置為/，則
		SpringMVC將捕獲WEB容器的所有請求，包括靜態資源的請求，SpringMVC會當成一個普通請求來處理
		，因找不到對應處理容器將導致錯誤
		2.解決方法:在SpringMVC的配置文件中，配置
		<mvc:default-servlet-handler/>解決靜態資源的問題
	-->
</head>
<body>

	<form action="" method="post">
		<%--要使用HttpHiddenFileter,把post請求轉成delete請求 就是這樣的固定寫法--%>
		<input type="hidden" name="_method" value="delete">
	</form>
	<c:if test="${empty requestScope.employee}">沒有任何員工訊息</c:if>
	<c:if test="${!empty requestScope.employee}">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>員工編號</th>
				<th>員工姓名</th>
				<th>員工年齡</th>
				<th>員工部門名稱</th>
				<th>員工部門編號</th>
				<th>編輯</th>
				<th>刪除</th>
			</tr>
			<c:forEach items="${requestScope.employee}" var="emp">
				<tr>
					<td>${emp.empId}</td>
					<td>${emp.name}</td>
					<td>${emp.age}</td>
					<td>${emp.department.departmentName}</td>
					<td>${emp.department.depId}</td>
					<td><a href="emp/${emp.empId}">編輯</a></td>
					<td><a class="delete" href="emp/${emp.empId}">刪除</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<br>
	<a href="emp">Add New Employee</a>
</body>
</html>