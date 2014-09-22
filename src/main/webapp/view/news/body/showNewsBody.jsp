<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<div id="navigable">
	<html:link forward="ListNews">
		<bean:message key="title.news.menu"  />
	</html:link>
	>>
	<bean:message key="link.news.show_news" />
</div>

		<table>
			<tr>
				<td valign="top"><bean:message key="label.news.title" />:</td>
				<td><bean:write name="NewsForm" property="newsMessage.title"/></td>
			</tr>

			<tr>
				<td valign="top"><bean:message key="label.news.news_date" />:</td>
				<td><bean:write  name="NewsForm"  property="newsMessage.date"/></td>
				<td></td>
			</tr>

			<tr>
				<td valign="top"><bean:message key="label.news.brief" />:</td>
				<td><bean:write  name="NewsForm" property="newsMessage.brief" /></td>
			</tr>

			<tr>
				<td valign="top"><bean:message key="label.news.content" />:</td>
				<td><bean:write  name="NewsForm"  property="newsMessage.content" /></td>
			</tr>
		</table>


