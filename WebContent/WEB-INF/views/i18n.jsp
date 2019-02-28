<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <fmt:message key="i18n.user"></fmt:message>
    <br><br>
    <a href="i18n2">i18n2 頁面</a>
    <br><br>
    <a href="i18n2?locale=zh_TW">i18n2 台灣頁面</a>
    <br><br>
    <a href="i18n2?locale=en_US">i18n2 英文頁面</a>


</body>
</html>
