<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<header>
	<div id="logo">
		<h1>News management</h1>
	</div>
	<div id="language-select">		
		<html:link page="/Locale.do?locale=en"><bean:message key="link.news.lang.en" /></html:link>
		<html:link page="/Locale.do?locale=ru"><bean:message key="link.news.lang.ru" /></html:link>
	</div>
</header>