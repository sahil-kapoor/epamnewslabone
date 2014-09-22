<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="navigable">
	<html:link forward="ListNews">
		<bean:message key="title.news.menu" />
	</html:link>
	>>
	<bean:message key="link.news.news_list" />
</div>
<h1>All news</h1>

<c:forEach items="${NewsForm.newsList}" var="news">
	<tr>
		<td>${news.id}</td>
		<td>${news.content}</td>
	</tr>
</c:forEach>