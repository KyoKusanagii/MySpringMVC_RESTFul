<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

			<%--顯示所有的錯誤消息			--%>
			<form:errors path="*"></form:errors>
			<br>

			<c:if test="${employee.empId != null}">
				<form:hidden path="empId"/>

					<%--對於_method不能用spring的form標籤 form:hidden，因為modelAttribute對應的bean中沒有_method這個屬性
						<form:hidden path="_method" value="put"/>
					--%>
				<input type="hidden" name="_method" value="put"/>
			</c:if>
			name:<form:input path="name"/>
			<form:errors path="name"></form:errors>
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
			<form:errors path="empId"></form:errors>
			<br>
			<!--對應到html的select， items部門的集合，itemLabel對應到html select底下的option-->
			department<form:select path="department.depId" items="${departments}"
			itemLabel="departmentName" itemValue="depId"/>
			<br>
			<!--
				1.資料Date轉字串的問題
				2.資料格式化的問題
				3.數據校驗的問題
				a.如何校驗?註解?
				  使用JSR303驗證標準
				  需加入hibernate validator驗證框架的jar
				  需在SpringMVC 配置<mvc:annotation-driven>
				  需要在bean的屬性上添加對應的註解
				  在目標方法bean類型的前面添加@valid註解
				b.驗證出錯，轉向到哪一個頁面?
			      注意:需校驗的bean對象和其綁定結果的對象或錯誤對象時成對出現的，他們之間不允許其他的入參
				c.錯誤消息?如何顯示，如何把錯誤消息進行國際化


			-->
			Birthday:<form:input path="birth"/><!--員工的生日 -->
			<form:errors path="birth"></form:errors>
			<br>
			Salary:<form:input path="salary"/><!--員工的生日 -->
			<form:errors path="salary"></form:errors>
			<br>
			<input type="submit">
		</form:form>
</body>
</html>