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
			�[�J���Ǭ�:rempId-name-age-department.name-gender
			Employee:<input type="text" name="employee">
			<input type="submit">
		</form>
		<br>
		<br>
		<!-- 1.����ݭn�ϥ�form���� ,�i�H��ֳt���}�o�X��歶���A�i�H���K���i����Ȫ��^�����
			 2.�`�N:�i�H�q�LModelAttribute���ݩʫ��w�j�w���ҫ��ݩʡA�Y�S�����w���ݩʡA�h�q�{�qrequest��Ū��command
			 �����bean�A�p�G���ݩʭȤ]���s�b�A�N�|�o�Ϳ��~
		-->
		<form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
		<!--path�ݩʹ���form��檺name�ݩʡA��path�P�ɤ]�O�M�g�쪫���ݩʡA�Gpath���W�٭n�P����W���ݩʬۦP -->

			<%--��ܩҦ������~����			--%>
			<form:errors path="*"></form:errors>
			<br>

			<c:if test="${employee.empId != null}">
				<form:hidden path="empId"/>

					<%--���_method�����spring��form���� form:hidden�A�]��modelAttribute������bean���S��_method�o���ݩ�
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
			ID:<form:input path="empId"/><!--���u��ID -->
			<form:errors path="empId"></form:errors>
			<br>
			<!--������html��select�A items���������X�AitemLabel������html select���U��option-->
			department<form:select path="department.depId" items="${departments}"
			itemLabel="departmentName" itemValue="depId"/>
			<br>
			<!--
				1.���Date��r�ꪺ���D
				2.��Ʈ榡�ƪ����D
				3.�ƾڮ��窺���D
				a.�p�����?����?
				  �ϥ�JSR303���Ҽз�
				  �ݥ[�Jhibernate validator���Үج[��jar
				  �ݦbSpringMVC �t�m<mvc:annotation-driven>
				  �ݭn�bbean���ݩʤW�K�[����������
				  �b�ؼФ�kbean�������e���K�[@valid����
				b.���ҥX���A��V����@�ӭ���?
			      �`�N:�ݮ��窺bean��H�M��j�w���G����H�ο��~��H�ɦ���X�{���A�L�̤��������\��L���J��
				c.���~����?�p����ܡA�p�����~�����i���ڤ�


			-->
			Birthday:<form:input path="birth"/><!--���u���ͤ� -->
			<form:errors path="birth"></form:errors>
			<br>
			Salary:<form:input path="salary"/><!--���u���ͤ� -->
			<form:errors path="salary"></form:errors>
			<br>
			<input type="submit">
		</form:form>
</body>
</html>