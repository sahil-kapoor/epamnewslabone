<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<div id="navigable">
	<html:link forward="allNews">
		<bean:message key="title.news.menu" />
	</html:link>
	>>
	<bean:message key="link.news.add_news" />
</div>
<div id="add-form">
	<html:form>
		<html:errors />
		<table>
			<tr>
				<td valign="top"><bean:message key="label.news.title" />:</td>
				<td><html:text property="message"></html:text></td>
			</tr>

			<tr>
				<td valign="top"><bean:message key="label.news.news_date" />:</td>
				<td><html:text property="message"></html:text></td>
				<td></td>
			</tr>

			<tr>
				<td valign="top"><bean:message key="label.news.brief" />:</td>
				<td><html:textarea property="message" cols="65" rows="10"></html:textarea></td>
			</tr>

			<tr>
				<td valign="top"><bean:message key="label.news.content" />:</td>
				<td><html:textarea property="message" cols="65" rows="10"></html:textarea></td>
			</tr>
		</table>
		<div id="submit-cancel-buttons">
			<html:submit>
				<bean:message key="news.add" />
			</html:submit>
			<html:cancel>
				<bean:message key="label.common.button.cancel" />
			</html:cancel>
		</div>
	</html:form>
</div>
