<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
		<form action="testConversionServiceConverter" method="post">
			加入順序為:rempId-name-age-department.name-gender
			Employee:<input type="text" name="employee">
			<input type="submit">
		</form>
		<br>
		<br>
		<!-- 1.為何需要使用form標籤 ,可以更快速的開發出表單頁面，可以更方便的進行表單值的回傳顯示
			 2.注意:可以通過ModelAttribute的屬性指定綁定的模型屬性，若沒有指定該屬性，則默認從request裡讀取command
			 的表單bean，如果該屬性值也不存在，就會發生錯誤
		-->
		<form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
		<!--path屬性對應form表單的name屬性，而path同時也是映射到物件的屬性，故path的名稱要與物件名稱屬性相同 -->

			<c:if test="${employee.empId != null}">
				<form:hidden path="empId"/>
					<%--對於_method不能用spring的form標籤 form:hidden，因為modelAttribute對應的bean中沒有_method這個屬性
						<form:hidden path="_method" value="put"/>
					--%>
				<input type="hidden" name="_method" value="put"/>
			</c:if>
			name:<form:input path="name"/>
			<br>
			age:<form:input path="age"/>
			<br>
			<%
				Map<String,String> genders = new HashMap<>();
				genders.put("1","male");
				genders.put("2","female");
				request.setAttribute("genders",genders);
			%>
			gender:<form:radiobuttons path="gender" items="${genders}"/>
			<br>
			ID:<form:input path="empId"/><!--員工的ID -->
			<br>
			<!--對應到html的select， items部門的集合，itemLabel對應到html select底下的option-->
			department<form:select path="department.depId" items="${departments}"
			itemLabel="departmentName" itemValue="depId"/>
			<br>
			<!--
				1.資料Date轉字串的問題
				2.資料格式化的問題
				3.數據校驗的問題
			-->
			<%--Birthday:<form:input path="birth"/><!--員工的生日 -->--%>
			<br>
			<input type="submit">
		</form:form>
</body>
</html>