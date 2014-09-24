<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<header>
	<div id="logo">
		<h1>News management</h1>
	</div>
	<div id="language-select">
		<html:link page="/Locale.do?locale=ru" onclick="changeLanguage(this.href);">Russian</html:link>
		<html:link page="/Locale.do?locale=en" onclick="changeLanguage(this.href);">English</html:link>
	</div>
</header>