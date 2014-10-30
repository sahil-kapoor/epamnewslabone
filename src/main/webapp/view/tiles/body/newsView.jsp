<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="assets/css/newsView.css">
<script type="text/javascript" src="assets/js/view.js"></script>

<script type="text/javascript">
	var deleteSingle = '<bean:message key="js.news.delete.single" />'
</script>

<c:set var="last" scope="session" value="view" />
<c:set var="lastId" scope="session" value="${NewsForm.newsMessage.id}" />
<c:set var="currentId" scope="session"
	value="${NewsForm.newsMessage.id}" />
<c:set var="current" scope="session" value="view" />
<div id="news-view-body">
	<table>
		<tr>
			<td valign="top" class="td-shadow"><bean:message
					key="label.news.title" /></td>
			<td class="td-body"><div class="td-div-body">
					<span class="inner-pre"><bean:write name="NewsForm"
							property="newsMessage.title" /></span>
				</div></td>
		</tr>

		<tr>
			<td valign="top" class="td-shadow"><bean:message
					key="label.news.news_date" /></td>
			<td class="td-body"><div class="td-div-body">
					<bean:write name="NewsForm" property="newsMessage.date"
						formatKey="news.date.format" />
				</div></td>
		</tr>

		<tr class="space-under">
			<td valign="top" class="td-shadow"><bean:message
					key="label.news.brief" /></td>
			<td class="td-body"><div class="td-div-body">
					<span class="inner-pre"><bean:write name="NewsForm"
							property="newsMessage.brief" /></span>
				</div></td>
		</tr>

		<tr>
			<td valign="top" class="td-shadow"><bean:message
					key="label.news.content" /></td>
			<td class="td-body"><div class="td-div-body">
					<span class="inner-pre"><bean:write name="NewsForm"
							property="newsMessage.content" /></span>
				</div></td>
		</tr>
	</table>
</div>

<div id="news-view-control">

	<c:set var="newsId" scope="request" value="${NewsForm.newsMessage.id}" />


	<html:form action="EditNews">
		<html:submit>
			<bean:message key="label.news.button.edit" />
		</html:submit>
	</html:form>

	<html:form action="DeleteNews">
		<html:hidden property="newsToDelete" value="${newsId}"></html:hidden>
		<html:submit styleId="delete-button">
			<bean:message key="label.news.button.delete" />
		</html:submit>
	</html:form>
</div>

<input id="current-page-hidden" type="hidden"
	value='<bean:message key="link.news.show_news" />' />
