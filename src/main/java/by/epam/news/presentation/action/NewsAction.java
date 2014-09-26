package by.epam.news.presentation.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.MappingDispatchAction;

import by.epam.news.model.News;
import by.epam.news.presentation.Routes;
import by.epam.news.presentation.form.NewsForm;
import by.epam.news.service.NewsService;
import by.epam.news.util.SpringApplicationContext;

public class NewsAction extends MappingDispatchAction {
	
	private NewsService newsService = (NewsService) SpringApplicationContext.getBean("NewsService");
	//private static final Logger Log = Logger.getLogger(NewsAction.class);
	
	 
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String newsId = request.getParameter(Routes.ID);		 
		if( null != newsId && newsId.toString().matches("[0-9]*")){			
			News news = newsService.loadNews(Integer.parseInt(newsId));
			NewsForm newsForm = (NewsForm)form;
			newsForm.setNewsMessage(news);
		} 	
		return mapping.findForward(Routes.ADD_FORWARD);
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		NewsForm news = (NewsForm)form;	
		int id = newsService.saveNews(news.getNewsMessage());
		ActionRedirect redirect = new ActionRedirect(mapping.findForward(Routes.VIEW_FORWARD));
		redirect.addParameter(Routes.ID, id);
		return redirect;
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String newsId = request.getParameter(Routes.ID);
		if( null != newsId){
			News news = newsService.loadNews(Integer.parseInt(newsId));
			NewsForm newsForm = (NewsForm)form;
			newsForm.setNewsMessage(news);
		}
		return mapping.findForward(Routes.VIEW_FORWARD);
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsForm news = (NewsForm)form;
		news.setNewsList(newsService.newsList());
		return mapping.findForward(Routes.LIST_FORWARD);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsForm newsForm = (NewsForm)form;
		newsService.deleteNews(newsForm.getNewsToDelete());
		return mapping.findForward(Routes.LIST_FORWARD);
	}

	
	public ActionForward locale(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response){	
		request.getSession().setAttribute( Globals.LOCALE_KEY,
				Locale.forLanguageTag(request.getParameter(Routes.LOCALE)));        
		return mapping.findForward(Routes.ADD_FORWARD);
	}
	

}