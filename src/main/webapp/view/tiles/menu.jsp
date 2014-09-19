<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<div id="menu">
	<div id="panel">
		<div id="title">
			<bean:message key="title.news.menu" />
		</div>
		<div id="control">
			<ul>
				<html:submit property="method">
					<bean:message key="news.list" />
				</html:submit>
				<html:submit property="method">
					<bean:message key="news.add" />
				</html:submit>
				
<%-- 				<li><html:link forward="allNews"> --%>
<%-- 						<bean:message key="link.news.news_list" /> --%>
<%-- 					</html:link></li> --%>
<%-- 				<li><html:link forward="addNews"> --%>
<%-- 						<bean:message key="link.news.add_news" /> --%>
<%-- 					</html:link></li> --%>
			</ul>
		</div>
	</div>
</div>