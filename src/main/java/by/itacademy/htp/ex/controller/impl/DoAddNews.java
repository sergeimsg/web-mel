package by.itacademy.htp.ex.controller.impl;


import java.io.IOException;
import java.util.List;

import by.itacademy.htp.ex.bean.News;
import by.itacademy.htp.ex.controller.Command;
import by.itacademy.htp.ex.service.INewsService;
import by.itacademy.htp.ex.service.ServiceException;
import by.itacademy.htp.ex.service.ServiceProvider;
import by.itacademy.htp.ex.util.validation.DataValidationException;
import by.itacademy.htp.ex.util.validation.NewsDataValidation;
import by.itacademy.htp.ex.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoAddNews implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	private final NewsDataValidation newsDataValidationService = ValidationProvider.getInstance().getNewsValidationInstance();
	
	
	private static final String JSP_GET_NEWS_TITLE = "title";
	private static final String JSP_GET_NEWS_DATE = "date";
	private static final String JSP_GET_NEWS_BRIEF_NEWS = "briefNews";
	private static final String JSP_GET_NEWS_BRIEF_CONTENT = "content";
	private static final String JSP_GET_NEWS_IMAGE_PATH = "imgPath";
	private static final String JSP_GET_NEWS_STATUS = "newsStatus";
	private static final String SESSION_USER_ID = "userID";
	
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String newsTitle = request.getParameter(JSP_GET_NEWS_TITLE);
		String newsDate= request.getParameter(JSP_GET_NEWS_DATE);
		String newsBrief = request.getParameter(JSP_GET_NEWS_BRIEF_NEWS);
		String newsContent = request.getParameter(JSP_GET_NEWS_BRIEF_CONTENT);
		String newsImgPath = "images/newsImages/" + request.getParameter(JSP_GET_NEWS_IMAGE_PATH);
		String newsStatus = request.getParameter(JSP_GET_NEWS_STATUS);
		Integer userID = (Integer) request.getSession().getAttribute(SESSION_USER_ID);
		
		List<News> newsList;
		
		News newNews;
				
		try {
			if (newsDataValidationService.dataValidatioAddNews(newsTitle, newsDate, newsBrief, newsContent, 
					newsImgPath, newsStatus, userID) && request.getSession(false)!=null) {
				
			newNews= new News(newsTitle, newsBrief, newsContent, newsDate, newsImgPath, Integer.parseInt(newsStatus),userID);
			newsService.save(newNews);
			newsList = newsService.list();
			request.setAttribute(ConstName.ATTRIBUTE_NEWS_TO_SHOW_NEWS, newsList);
			request.setAttribute(ConstName.ATTTIBUTE_PRESENTATION_TO_SHOW_NEWS, "newsList");
			response.sendRedirect("controller?command=go_to_news_list");
				
			}  
			
			else {
				
				response.sendRedirect("controller?command=go_to_base_page");
				
			}
			             	
			
		} catch (ServiceException | DataValidationException e) {
			
			
			request.getSession().setAttribute(ConstName.ATTRIBUTE_FOR_MESSAGE_ERROR_PAGE, "Empty field or wrong data");
			response.sendRedirect("controller?command=go_to_error_page");
		}
	
	
	
	
	
	}

}
