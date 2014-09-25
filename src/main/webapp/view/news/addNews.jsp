<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div id="navigable">
	<html:link forward="ListNews">
		<bean:message key="title.news.menu" />
	</html:link>
	>>
	<bean:message key="link.news.add_news" />
</div>
<div id="add-form">
	<html:form action="CreateNews">
		<html:errors />

		<table>
			<html:hidden name="NewsForm" property="newsMessage.id" />
			<html:hidden property="last" value="${last}" />
			<tr>
				<td valign="top"><bean:message key="label.news.title" />:</td>
				<td><html:text maxlength="100" styleId="title-text" name="NewsForm"
						property="newsMessage.title"></html:text></td>
			</tr>

			<tr>
				<td valign="top"><bean:message key="label.news.news_date" />:</td>
				<td>
<%-- 				<html:text styleId="date-text"  name="NewsForm"  --%>
<%--  						property="newsMessage.date" ></html:text></td> --%>
					<input  id="date-text" type="date" name="${NewsForm.newsMessage.date}" value="${NewsForm.newsMessage.date}" ></td>
				
			</tr>

			<tr>
				<td valign="top"><bean:message key="label.news.brief" />:</td>
				<td><html:textarea styleId="brief-text" name="NewsForm"
						property="newsMessage.brief" cols="65" rows="10"></html:textarea></td>
			</tr>

			<tr>
				<td valign="top"><bean:message key="label.news.content" />:</td>
				<td><html:textarea styleId="content-text" name="NewsForm"
						property="newsMessage.content" cols="65" rows="10"></html:textarea></td>
			</tr>
		</table>
		<div id="submit-cancel-buttons">
			<html:submit styleId="submit-save-button" >
				<bean:message key="label.common.button.submit"  />
			</html:submit>
			<html:cancel styleId="cancel-save-button">
				<bean:message key="label.common.button.cancel" />
			</html:cancel>
		</div>
	</html:form>
</div>
