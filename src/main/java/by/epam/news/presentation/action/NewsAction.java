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
		NewsForm news = (NewsForm) form;
		int id = 0;
		try {
			id = service.saveNews(news.getNewsMessage());
		} catch (ServiceException e) {
			Log.error(e.getMessage(), e);
			return mapping.findForward(Routes.GLOBAL_ERROR);
		}
		ActionRedirect redirect = new ActionRedirect(
				mapping.findForward(Routes.VIEW_FORWARD));
		redirect.addParameter(Routes.ID, id);
		return redirect;
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
		String sId = request.getParameter(Routes.ID);
		News news = null;
		if (null != sId) {
			news = loadNewsById(sId);
		}
		if (null == news) {
			return mapping.findForward(Routes.ERROR_404);
		}
		NewsForm newsForm = (NewsForm) form;
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
		String sId = request.getParameter(Routes.ID);
		News news = null;
		if (null == sId) {
			news = new News(0, "", new Date(), "", "");
		} else {
			news = loadNewsById(sId);
		}
		if (null == news) {
			return mapping.findForward(Routes.ERROR_404);
		}
		NewsForm newsForm = (NewsForm) form;
		newsForm.setNewsMessage(news);
		return mapping.findForward(Routes.ADD_FORWARD);
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
			request.setAttribute(Globals.ERROR_KEY, e);
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
		return mapping.findForward(Routes.LIST_FORWARD);
	}

	/**
	 * Load news by id.
	 * 
	 * Help method to load news by id (string).
	 * If news can't be loaded return null. Be careful.
	 * @param strId
	 * @return
	 */
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