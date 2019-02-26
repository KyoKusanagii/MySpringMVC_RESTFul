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
		$("testJson").click(function () {
			let url = $(this).href;
			let args = {};
			$.post(url,args,function (data) {
				for(let i=0;i<data.length;i++){
					let id = data[i].empId;
					let age = data[i].age;
					let name = data[i].name;
					console.log("id=" + id + ",age = " + age + ",name = " + name + "\n");
				}
			});
			return false;
		});
	})
	
</script>
</head>
<body>
	<a href="<%=request.getContextPath()%>/emps">�q�X�������u</a>
	<br><br>
	<a href="testJson" id="testJson">����JSON</a>
	<br><br>
	<form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
		�ɮ�:<input type="file" name="file">
		�y�z:<input type="text" name="desc">
		<input type="submit">
	</form>
	<br><br>
	<a href="testResponseEntity">����ResponseEntity</a>

	<!--
		�����ڤ�:
		1.�b�����W����ھ��s�����y���]�m�����p��奻(���O���e)�A�ɶ��B�ƭȶi�楻�a�ƳB�z
		2.�i�H�bbean�������ڤƸ귽���locale����������
		3.�i�H�z�L�W�s������locale�A�Ӥ��A�̿���s�������y���]�m���p

		�ѨM:
		1.�ϥ�JSTL��fmt���ҸѨM
		2.�bbean���`�JResourceBundleMessageSource����ҡA�ϥΨ������getMessage��k�Y�i
		3.�t�mLocaleResolver�MLocaleChangeInterceptor
	-->
	<a href="i18n">i18n ����</a>
</body>
</html>