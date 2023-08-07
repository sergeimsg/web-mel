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

public class DoEditNews implements Command {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final NewsDataValidation newsDataValidationService = ValidationProvider.getInstance().getNewsValidationInstance();

	
	private static final String JSP_GET_NEWS_ID = "id";
	private static final String JSP_GET_NEWS_TITLE = "title";
	private static final String JSP_GET_NEWS_DATE = "date";
	private static final String JSP_GET_NEWS_BRIEF_NEWS = "briefNews";
	private static final String JSP_GET_NEWS_BRIEF_CONTENT = "content";
	private static final String JSP_GET_NEWS_IMAGE_PATH = "imgPath";
	private static final String JSP_GET_NEWS_IMAGE_CURRENT_PATH = "currentImgPath";
	private static final String JSP_GET_NEWS_STATUS = "newsStatus";
	//private static final String SESSION_USER_ID = "userID";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String newsID = request.getParameter(JSP_GET_NEWS_ID);
		String newsTitle = request.getParameter(JSP_GET_NEWS_TITLE);
		String newsDate= request.getParameter(JSP_GET_NEWS_DATE);
		String newsBrief = request.getParameter(JSP_GET_NEWS_BRIEF_NEWS);
		String newsContent = request.getParameter(JSP_GET_NEWS_BRIEF_CONTENT);
		String newsImgPath = request.getParameter(JSP_GET_NEWS_IMAGE_PATH);
		
		
		if (newsImgPath.isEmpty() && !request.getParameter(JSP_GET_NEWS_IMAGE_CURRENT_PATH).isEmpty()) {
			
			newsImgPath = request.getParameter(JSP_GET_NEWS_IMAGE_CURRENT_PATH);
	
		}
		
		else { newsImgPath = "images/newsImages/" + newsImgPath;
	
		}
		String newsStatus = request.getParameter(JSP_GET_NEWS_STATUS);
		
		News newNews= null;
				
		
			List<News> newsList;
				
		try {
			if (newsDataValidationService.dataValidatioEditdNews(newsTitle, newsDate, newsBrief, newsContent, newsImgPath, newsID)) {
			
			newNews = new News(Integer.parseInt(newsID), newsTitle, newsBrief, newsContent, newsDate,
			newsImgPath, Integer.parseInt(newsStatus));
			newsService.update(newNews);
			newsList = newsService.list();
			request.setAttribute("news", newsList);
			request.setAttribute("presentation", "newsList");
			response.sendRedirect("controller?command=go_to_news_list");
				
			}
			
			
			
			                             	
			
		} catch (ServiceException | DataValidationException e) {
			
			request.setAttribute("error", "error");
			request.setAttribute("errorE", "wrong data");
			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
	
	
	
	}

}
