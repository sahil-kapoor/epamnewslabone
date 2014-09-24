<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><bean:message key="title.news.menu" /></title>
<link rel="stylesheet" type="text/css" href="assets/css/layout.css">
<link rel="stylesheet" type="text/css" href="assets/css/addNews.css">
<link rel="stylesheet" type="text/css" href="assets/css/newsView.css">
<link rel="stylesheet" type="text/css" href="assets/css/newsList.css">
<script type="text/javascript" src="assets/js/main.js"></script>
<script type="text/javascript" src="assets/js/addNewsValidation.js"></script>
</head>
<body>
	<tiles:insert attribute="header" />
	<div id="page">
		<tiles:insert attribute="menu" />
		<div class="wrap">
			<tiles:insert attribute="body" ignore="true" />
		</div>
	</div>
	<tiles:insert attribute="footer" />
</body>
</html>