<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="assets/css/addNews.css">
<script type="text/javascript" src="assets/js/addNews.js"></script>
<script type="text/javascript">
	var invalidDate = '<bean:message key="js.news.bad.date" />';
	var invalidContent = '<bean:message key="js.news.bad.content" />';
	var invalidBrief = '<bean:message key="js.news.bad.brief" />';
	var invalidTitle = '<bean:message key="js.news.bad.title" />';
	var imposibleDate = '<bean:message key="js.news.imposible.date" />';
	var cancel = '<bean:message key="js.news.cancel" />';
	var lang = '<bean:message key="js.news.lang" />';
</script>


<c:if test="${NewsForm.newsMessage.id == 0 }">
	<c:set var="current" scope="session" value="add" />
	<c:set var="currentId" scope="session" value="0" />
</c:if>

<c:if test="${NewsForm.newsMessage.id != 0 }">
	<c:set var="current" scope="session" value="edit" />
	<c:set var="currentId" scope="session" value="${NewsForm.newsMessage.id}" />
</c:if>


<input type="hidden" id="hidden-locale"
	value="${pageContext.response.locale}" />


<div id="add-form">
	<html:form action="CreateNews">
		<table>
			<html:hidden name="NewsForm" property="newsMessage.id" />
			<html:hidden property="last" value="${last}" />
			<tr>
				<td valign="top"><bean:message key="label.news.title" /></td>
				<td><html:text maxlength="100" styleId="title-text"
						name="NewsForm" property="newsMessage.title"></html:text></td>

			</tr>
			<tr>
				<td class="error-message"><html:errors
						property="newsMessage.title" /></td>
			</tr>
			<tr>
				<td valign="top"><bean:message key="label.news.news_date" /></td>
				<td><input type="text" name="newsMessage.date" id="date-text"
					maxlength="10"
					value='<bean:write name="NewsForm" property="newsMessage.date" formatKey="news.date.format"/>'>
				<input type="hidden" name="newsMessage.date" id="date-text-hidden">
				</td>
			</tr>
			<tr>
				<td class="error-message"><html:errors
						property="newsMessage.date" /></td>
			</tr>
			<tr>
				<td valign="top"><bean:message key="label.news.brief" /></td>
				<td><html:textarea styleId="brief-text" name="NewsForm"
						property="newsMessage.brief" cols="65" rows="10"></html:textarea></td>
			</tr>
			<tr>
				<td class="error-message"><html:errors
						property="newsMessage.brief" /></td>
			</tr>
			<tr>
				<td valign="top"><bean:message key="label.news.content" /></td>
				<td><html:textarea styleId="content-text" name="NewsForm"
						property="newsMessage.content" cols="65" rows="10"></html:textarea></td>
			</tr>
			<tr>
				<td class="error-message"><html:errors
						property="newsMessage.content" /></td>
			</tr>
		</table>
		<div id="submit-cancel-buttons">
			<html:submit styleId="submit-save-button">
				<bean:message key="label.news.button.submit" />
			</html:submit>
			<html:cancel styleId="cancel-save-button">
				<bean:message key="label.news.button.cancel" />
			</html:cancel>
		</div>
	</html:form>
</div>

<input id="current-page-hidden" type="hidden"
	value='<bean:message key="link.news.add_news" />' />
