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
	<a href="<%=request.getContextPath()%>/emps">秀出全部員工</a>
	<br><br>
	<a href="testJson" id="testJson">測試JSON</a>
	<br><br>
	<p>testHttpMessageConverter</p>
	<form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
		檔案:<input type="file" name="file">
		描述:<input type="text" name="desc">
		<input type="submit">
	</form>
	<br><br>
	<a href="testResponseEntity">測試ResponseEntity</a>

	<p>testFileUpload</p>
	<form action="testFileUpload " method="post" enctype="multipart/form-data">
		檔案:<input type="file" name="file">
		描述:<input type="text" name="desc">
		<input type="submit">
	</form>
	<br><br>

	<!--
		關於國際化:
		1.在頁面上能夠根據瀏覽器語言設置的情況對文本(不是內容)，時間、數值進行本地化處理
		2.可以在bean中獲取國際化資源文件locale對應的消息
		3.可以透過超連結切換locale，而不再依賴於瀏覽器的語言設置情況

		解決:
		1.使用JSTL的fmt標籤解決
		2.在bean中注入ResourceBundleMessageSource的實例，使用其對應的getMessage方法即可
		3.配置LocaleResolver和LocaleChangeInterceptor
	-->
	<a href="i18n">i18n 頁面</a>
</body>
</html>