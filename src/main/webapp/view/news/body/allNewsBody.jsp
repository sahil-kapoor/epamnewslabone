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

<div id="news-list">
	<logic:iterate name="NewsForm" property="newsList" id="news">
		<div class="news-list-single-news">
			<div class="title-date">
				<div class="title">
					<b><bean:write name="news" property="title" /></b>
				</div>
				<div class="date">
					<bean:write name="news" property="date" />
				</div>
			</div>

			<div class="news-content">
				<bean:write name="news" property="content" />
			</div>

			<div class="nav-checkbox">
				<html:link forward="NewsView" paramId="id" paramName="news"
					paramProperty="id">view</html:link>
				<html:link forward="AddNews" paramId="id" paramName="news"
					paramProperty="id">edit</html:link>
				<input type="checkbox">
			</div>
			
		</div>
	</logic:iterate>
</div>
