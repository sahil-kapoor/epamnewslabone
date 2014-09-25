<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="assets/css/newsList.css">
<script type="text/javascript" src="assets/js/list.jsp"></script>

<div id="navigable">
	<html:link forward="ListNews">
		<bean:message key="title.news.menu" />
	</html:link>
	>>
	<bean:message key="link.news.news_list" />
</div>

<div id="news-list">
	<html:form action="DeleteNews">
		<logic:iterate name="NewsForm" property="newsList" id="news">
			<div class="news-list-single-news">
				<div class="title-date">
					<div class="title">
						<b><bean:write name="news" property="title" /></b>
					</div>
					<div class="date">
						<bean:write name="news" format="MM/dd/yyyy" property="date" />
					</div>
				</div>
	
				<div class="news-content">
					<bean:write name="news" property="brief" />
				</div>
	
				<div class="nav-checkbox">
					<html:link forward="NewsView" paramId="id" paramName="news"
						paramProperty="id"><bean:message key="link.news.link.view" /></html:link>
					<html:link action="AddNews.do?id=${news.id}&last=list"><bean:message key="link.news.link.edit" /></html:link>
					<html:multibox styleClass="check-box" property="newsToDelete" value="${news.id}"/>
				</div>
			</div>
		</logic:iterate>
		<div id="delete-all-button">
			<html:submit><bean:message key="label.news.button.delete" /></html:submit>
		</div>
	</html:form>
</div>
