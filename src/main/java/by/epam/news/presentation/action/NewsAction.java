package by.epam.news.presentation.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.MappingDispatchAction;

import by.epam.news.model.News;
import by.epam.news.presentation.form.NewsForm;
import by.epam.news.service.NewsService;
import by.epam.news.util.SpringApplicationContext;

public class NewsAction extends MappingDispatchAction {
	
	private final static String LAST_PAGE = "lastPage";

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("add");
		String newsId = request.getParameter("id");		
		if( null != newsId){
			NewsService service = (NewsService) SpringApplicationContext.getBean("NewsService");
			News news = service.loadNews(Integer.parseInt(newsId));
			NewsForm newsForm = (NewsForm)form;
			newsForm.setNewsMessage(news);
		} 	
		return mapping.findForward("add");
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsForm news = (NewsForm)form;	
		NewsService service = (NewsService) SpringApplicationContext.getBean("NewsService");
		int id = service.saveNews(news.getNewsMessage());
		System.out.println("create");
		ActionRedirect redirect = new ActionRedirect(mapping.findForward("view"));
		redirect.addParameter("id", id);
		return redirect;
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().setAttribute(LAST_PAGE, "view");
		NewsService service = (NewsService) SpringApplicationContext.getBean("NewsService");
		News news = service.loadNews(Integer.parseInt(request.getParameter("id")));
		NewsForm newsForm = (NewsForm)form;
		newsForm.setNewsMessage(news);
		System.out.println("view");
		return mapping.findForward("view");
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("list");
		request.getSession().setAttribute(LAST_PAGE, "list");
		NewsForm news = (NewsForm)form;
		NewsService service = (NewsService) SpringApplicationContext.getBean("NewsService");		
		news.setNewsList(service.newsList());
		return mapping.findForward("list");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("delete");
		return mapping.findForward("delete");
	}

	
	public ActionForward locale(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response){
		request.getSession().setAttribute( Globals.LOCALE_KEY,
				Locale.forLanguageTag(request.getParameter("locale")));
 
		return mapping.findForward("back");
	}
	
	public ActionForward back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){	

		Object page = request.getSession().getAttribute(LAST_PAGE);
		System.out.println("page=" + page);
		if (null != page) {
			return mapping.findForward((String) page);
		}
		return mapping.findForward("list");
	}
	


}