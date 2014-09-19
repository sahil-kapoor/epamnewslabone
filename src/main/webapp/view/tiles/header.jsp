<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<header>
	<div id="logo">
		<h1><bean:message key="title.news.header" /></h1>
	</div>
	<div id="language-select">
		<html:link page="/Locale.do?method=russian">Russian</html:link>
		<html:link page="/Locale.do?method=english">English</html:link>
	</div>
</header>