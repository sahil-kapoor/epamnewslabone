package by.epam.news.presentation.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.actions.MappingDispatchAction;

import by.epam.news.presentation.form.NewsForm;
import by.epam.news.service.NewsService;
import by.epam.news.util.SpringApplicationContext;

public class NewsAction extends MappingDispatchAction {

//	@Override
//	public ActionForward execute(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		System.out.println("NewsAction in action");
//		NewsForm newsForm = (NewsForm) form;
//		newsForm.setBrief("brief");
//		newsForm.setContent("Content");
//		newsForm.setDate(new Date());
//
//		return mapping.findForward("list");
//	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("add");
		
		return mapping.findForward("add");
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("view");
		return mapping.findForward("view");
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("list");
		NewsForm news = (NewsForm)form;
		NewsService service = (NewsService) SpringApplicationContext.getBean("NewsService");		
		news.setNewsList(service.newsList());
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("edit");
		return mapping.findForward("edit");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("delete");
		return mapping.findForward("delete");
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsForm news = (NewsForm)form;
		
		NewsService service = (NewsService) SpringApplicationContext.getBean("NewsService");
		service.saveNews(news.getNewsMessage());
		System.out.println("create");
		System.out.println(news+"");
		return mapping.findForward("view");
	}
	
	public ActionForward locale(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response){
		
		request.getSession().setAttribute( Globals.LOCALE_KEY,
				Locale.forLanguageTag(request.getParameter("locale")));
 
		return mapping.findForward("list");
	}

//	@Override
//	protected Map<String, String> getKeyMethodMap() {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("news.add", "add");
//		map.put("news.view", "view");
//		map.put("news.list", "list");
//		map.put("news.edit", "edit");
//		map.put("news.delete", "delete");
//		return map;
//	}

}