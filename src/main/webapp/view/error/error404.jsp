<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>News</title>
	<link rel="stylesheet" type="text/css" href="assets/css/layout.css">
</head>
<body>
	<div class="error">
		<h1><bean:message key="error.404.title"/></h1>
		<p><bean:message key="error.404.message"/></p>
		<html:link action="ListNews.do?"><bean:message key="error.back.main"/></html:link>
	</div>
</body>
</html>