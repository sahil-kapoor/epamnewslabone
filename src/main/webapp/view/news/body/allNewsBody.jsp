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

<table>
	<logic:iterate name="NewsForm" property="newsList" id="news">
		<tr>
			<td><bean:write name="news" property="id" /></td>
			<td><bean:write name="news" property="title" /></td>
			<td><bean:write name="news" property="date" /></td>
			<td><bean:write name="news" property="brief" /></td>
			<td><bean:write name="news" property="content" /></td>
			<td><html:link forward="NewsView" paramId="id" paramName="news"
					paramProperty="id">view</html:link></td>
			<td><html:link forward="EditNews" paramId="id" paramName="news"
					paramProperty="id">edit</html:link></td>

			<%-- 			<html:link forward="NewsEdit">edit</html:link> --%>
		</tr>
		<tr>
			<td><hr></td>
			<td><hr></td>
			<td><hr></td>
			<td><hr></td>
			<td><hr></td>
			<td><hr></td>
			<td><hr></td>
		</tr>
	</logic:iterate>
</table>