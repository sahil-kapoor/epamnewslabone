package by.epam.news.presentation.action;

import java.util.Date;
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
import by.epam.news.service.ServiceException;

public class NewsAction extends MappingDispatchAction {

	private NewsService service;

	public void setService(NewsService service) {
		this.service = service;
	}

	private static final Logger Log = Logger.getLogger(NewsAction.class);

	

	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			 {
		NewsForm news = (NewsForm) form;
		int id = 0;
		try {
			 id = service.saveNews(news.getNewsMessage());
		} catch (ServiceException e){
			Log.error(e.getMessage(), e);
			return mapping.findForward(Routes.GLOBAL_ERROR);
		}
		ActionRedirect redirect = new ActionRedirect(
				mapping.findForward(Routes.VIEW_FORWARD));
		redirect.addParameter(Routes.ID, id);
		return redirect;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String sId = request.getParameter(Routes.ID);
		News news = null;
		if ( null != sId){
			news = loadNewsById(sId);
		}
		if (null == news) {
			return mapping.findForward(Routes.ERROR_404);
		}
		NewsForm newsForm = (NewsForm) form;
		newsForm.setNewsMessage(news);
		return mapping.findForward(Routes.VIEW_FORWARD);
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {		
		String sId = request.getParameter(Routes.ID);
		News news = null;
		if (null == sId){
			news = new News(0, "", new Date(), "", "");
		} else {
			news = loadNewsById(sId);
		}
		if (null == news){
			return mapping.findForward(Routes.ERROR_404);
		}
		NewsForm newsForm = (NewsForm) form;
		newsForm.setNewsMessage(news);
		return mapping.findForward(Routes.ADD_FORWARD);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			 {
		NewsForm news = (NewsForm) form;
		try {
			news.setNewsList(service.newsList());
		} catch (ServiceException e){
			Log.error(e.getMessage(), e);
			return mapping.findForward(Routes.GLOBAL_ERROR);
		}
		return mapping.findForward(Routes.LIST_FORWARD);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			{
		NewsForm newsForm = (NewsForm) form;
		try {
			service.deleteNews(newsForm.getNewsToDelete());
		} catch (ServiceException e){
			Log.error(e.getMessage(), e);
			return mapping.findForward(Routes.GLOBAL_ERROR);
		}
		return mapping.findForward(Routes.LIST_FORWARD);
	}

	public ActionForward locale(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(Globals.LOCALE_KEY,
				Locale.forLanguageTag(request.getParameter(Routes.LOCALE)));
		return mapping.findForward(Routes.ADD_FORWARD);
	}

	public News loadNewsById(String strId) {
		String ID_PATTERN = "[0-9]*";
		if (strId.matches(ID_PATTERN)) {
			try {
				int id = Integer.parseInt(strId);
				return service.loadNews(id);
			} catch (ServiceException | NumberFormatException e) {
				Log.error(e.getMessage(), e);
				return null;
			}
		}
		return null;
	}

}