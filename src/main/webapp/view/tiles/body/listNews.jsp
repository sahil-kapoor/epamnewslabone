<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="assets/css/newsList.css">
<script type="text/javascript" src="assets/js/list.js"></script>


<script type="text/javascript" src="assets/js/messages/listMessages.jsp"></script>

<c:set var="last" scope="session" value="list"/>
<c:set var="current" scope="session" value="list"/>

<div id="news-list">
	<html:form action="DeleteNews">
		<logic:iterate name="NewsForm" property="newsList" id="news">
			<div class="news-list-single-news">
				<div class="title-date">
					<div class="title">
					<b><span class="inner-pre"><bean:write name="news" property="title" /></span></b>
					</div>
					<div class="date">
						<bean:write name="news" formatKey="news.date.format" property="date" />
					</div>
				</div>
	
				<div class="news-brief">
					<span class="inner-pre"><bean:write name="news" property="brief" /></span>
				</div>
	
				<div class="nav-checkbox">
					<html:link forward="NewsView" paramId="newsMessage.id" paramName="news"
						paramProperty="id"><bean:message key="link.news.link.view" /></html:link>
						<html:link forward="EditNews" paramId="newsMessage.id" paramName="news"
						paramProperty="id"><bean:message key="link.news.link.edit" /></html:link>					
					<html:multibox styleClass="check-box" property="newsToDelete" value="${news.id}"/>
				</div>
			</div>
		</logic:iterate>
		<div id="delete-all-button">
			<html:submit><bean:message key="label.news.button.delete" /></html:submit>
		</div>
	</html:form>
</div>

<input id="current-page-hidden" type="hidden" value='<bean:message key="link.news.news_list" />'/>