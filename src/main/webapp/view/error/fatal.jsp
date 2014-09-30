<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
 
<html>
<head>
	<link rel="stylesheet" type="text/css" href="assets/css/layout.css">
	<title>News</title>
</head>
<body>
<div class="error">
	<h1><bean:message key="error.global.mesage"/></h1>
	<html:link action="ListNews.do?"><bean:message key="error.back.main"/></html:link>
</div>
</body>
</html>