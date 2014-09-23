<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<div id="navigable">
	<html:link forward="ListNews">
		<bean:message key="title.news.menu" />
	</html:link>
	>>
	<bean:message key="link.news.show_news" />
</div>


<div id="news-view-body">
	<table>
		<tr>
			<td valign="top" class="td-shadow"><bean:message
					key="label.news.title" /></td>
			<td class="td-body"><div>
					<bean:write name="NewsForm" property="newsMessage.title" />
				</div></td>
		</tr>

		<tr>
			<td valign="top" class="td-shadow"><bean:message
					key="label.news.news_date" /></td>
			<td class="td-body"><div>
					<bean:write name="NewsForm" property="newsMessage.date" />
				</div></td>
		</tr>

		<tr class="space-under">
			<td valign="top" class="td-shadow"><bean:message
					key="label.news.brief" /></td>
			<td class="td-body"><div>
					<bean:write name="NewsForm" property="newsMessage.brief" />
				</div></td>
		</tr>
		
		<tr>
			<td valign="top" class="td-shadow"><bean:message
					key="label.news.content" /></td>
			<td class="td-body"><div>
					<bean:write name="NewsForm" property="newsMessage.content" />
				</div></td>
		</tr>
	</table>
</div>

<div id="news-view-control">

 	<html:form action="AddNews">
 		<html:hidden name="NewsForm" property="newsMessage.id"/>
		<html:submit>
			<bean:message key="label.common.button.edit" />
		</html:submit>
	</html:form>
	<p>${newsMessage.id}xxx${NewsForm.newsMessage.id}xxx<bean:write name="NewsForm" property="newsMessage.id" /></p>
	<html:form action="DeleteNews">
	<html:hidden property="newsToDelete" value="${id}"></html:hidden>

		<html:submit>
			<bean:message key="label.common.button.delete" />
		</html:submit>
	</html:form>
</div>


