<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<div id="menu">
	<div id="panel">
		<div id="title">
			<bean:message key="title.news.menu" />
		</div>
		<div id="control">
			<ul>				
				<li><html:link forward="ListNews">
						<bean:message key="link.news.news_list" />
					</html:link></li>
				<li><html:link forward="AddNews">
						<bean:message key="link.news.add_news" />
					</html:link></li>
			</ul>
		</div>
	</div>
</div>