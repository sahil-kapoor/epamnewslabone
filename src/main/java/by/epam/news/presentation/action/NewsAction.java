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

import by.epam.news.exception.ServiceException;
import by.epam.news.model.News;
import by.epam.news.presentation.Routes;
import by.epam.news.presentation.form.NewsForm;
import by.epam.news.service.NewsService;

/**
 * News Action.
 * 
 * Action to CRUD for news and also for change locale in app.
 * 
 * @author Alexander_Demeshko
 *
 */
public class NewsAction extends MappingDispatchAction {

	private NewsService service;

	public void setService(NewsService service) {
		this.service = service;
	}

	private static final Logger Log = Logger.getLogger(NewsAction.class);

	/**
	 * Create news.
	 * 
	 * Create or update news using service. Get news model from NewsForm and.
	 * After executing update forward to new or updated news view. Redirect to
	 * error page when some error throws by service.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		if (isCancelled(request)) {
			return getBackForward(request, mapping, Routes.LAST_PAGE, Routes.LAST_ID);
		}

		NewsForm newsForm = (NewsForm) form;		
		int id = 0;
		try {
			id = service.saveNews(newsForm.getNewsMessage());
		} catch (ServiceException e) {
			Log.error(e.getMessage(), e);
			return mapping.findForward(Routes.GLOBAL_ERROR);
		}		
		newsForm.getNewsMessage().setId(id);
		return mapping.findForward(Routes.VIEW_FORWARD);
	}


	/**
	 * View news.
	 * 
	 * Show news view. Get news's id from request. If id undefined or some
	 * exceptions in service layer show 404 error page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NewsForm newsForm = (NewsForm) form;
		int id = newsForm.getNewsMessage().getId();
		News news = null;
		if ( id != 0) {
			news = loadNewsById(id+"");
		}
		if (null == news) {
			return mapping.findForward(Routes.ERROR_404);
		}
		newsForm.setNewsMessage(news);
		return mapping.findForward(Routes.VIEW_FORWARD);
	}

	/**
	 * Show add or edit page.
	 * 
	 * Get news's id from request, if id == null show add page, if id illegal
	 * show 404 error page. Otherwise show edit page for news with this id.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NewsForm newsForm = (NewsForm) form;
		newsForm.setNewsMessage(new News());
		return mapping.findForward(Routes.ADD_FORWARD);
	}
	
	/**
	 * Show add or edit page.
	 * 
	 * Get news's id from request, if id == null show add page, if id illegal
	 * show 404 error page. Otherwise show edit page for news with this id.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NewsForm newsForm = (NewsForm) form;
		int id = newsForm.getNewsMessage().getId();
		News news = null;
		if (0 == id) {
			return mapping.findForward(Routes.ERROR_404);
		} else {
			news = loadNewsById(id+"");
		}
		if (null == news) {
			return mapping.findForward(Routes.ERROR_404);
		}
		newsForm.setNewsMessage(news);
		return mapping.findForward(Routes.EDIT_FORWARD);
	}

	/**
	 * List page.
	 * 
	 * Show all news page. In service error situation show global error.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NewsForm news = (NewsForm) form;
		try {
			news.setNewsList(service.newsList());
		} catch (ServiceException e) {
			Log.error(e.getMessage(), e);
			return mapping.findForward(Routes.GLOBAL_ERROR);
		}
		return mapping.findForward(Routes.LIST_FORWARD);
	}

	/**
	 * Delete news.
	 * 
	 * Delete news. Delete all news that id in NewsForm newsToDelete field(array
	 * int). In exception situation show global error. In successful way show to
	 * ListNews.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NewsForm newsForm = (NewsForm) form;
		try {
			service.deleteNews(newsForm.getNewsToDelete());
		} catch (ServiceException e) {
			Log.error(e.getMessage(), e);
			return mapping.findForward(Routes.GLOBAL_ERROR);
		}
		return mapping.findForward(Routes.LIST_FORWARD);
	}

	/**
	 * Change locale.
	 * 
	 * Change app locale. Get locale idtf from request parameter Routes.LOCALE
	 * and put it to session storage. By default show list news.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward locale(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(Globals.LOCALE_KEY,
				Locale.forLanguageTag(request.getParameter(Routes.LOCALE)));
		return getBackForward(request, mapping, Routes.CURRENT_PAGE, Routes.CURRENT_ID);
	}

	/**
	 * Get last page.
	 * 
	 * Invoke when on add page user press cancel or change locale. Get forwardName Attribute
	 * from session and try find forward for this. Also look Routes.LAST_ID
	 * Attribute and put it in request.
	 * 
	 * @param request
	 * @param mapping
	 * @param forwardName
	 * @return
	 */
	
	private ActionForward getBackForward(HttpServletRequest request,
			ActionMapping mapping, String forwardName, String idName) {
		Object page = request.getSession().getAttribute(forwardName);
		Object lastId = request.getSession().getAttribute(idName);
		if (null != page) {
			ActionRedirect redirect = new ActionRedirect(
					mapping.findForward((String) page));
			if (lastId != null && lastId.toString().length() != 0 && !"0".equals(lastId.toString())) {
				redirect.addParameter(Routes.ID, lastId);
			}
			return redirect;
		}
		return mapping.findForward(Routes.LIST_FORWARD);
	}

	
	/**
	 * Load news by id.
	 * 
	 * Help method to load news by id (string). If news can't be loaded return
	 * null. Be careful.
	 * 
	 * @param strId
	 * @return
	 */
	private News loadNewsById(String strId) {
		try {
			int id = Integer.parseInt(strId);
			return service.loadNews(id);
		} catch (ServiceException | NumberFormatException e) {
			Log.error(e.getMessage(), e);
			return null;
		}
	}
}