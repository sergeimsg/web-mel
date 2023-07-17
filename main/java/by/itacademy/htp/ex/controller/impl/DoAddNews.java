package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.itacademy.htp.ex.bean.News;
import by.itacademy.htp.ex.controller.Command;
import by.itacademy.htp.ex.service.INewsService;
import by.itacademy.htp.ex.service.ServiceException;
import by.itacademy.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoAddNews implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	//private static final String JSP_GET_NEWS_ID = "id";
	private static final String JSP_GET_NEWS_TITLE = "title";
	private static final String JSP_GET_NEWS_DATE = "date";
	private static final String JSP_GET_NEWS_BRIEF_NEWS = "briefNews";
	private static final String JSP_GET_NEWS_BRIEF_CONTENT = "content";
	private static final String JSP_GET_NEWS_IMAGE_PATH = "imgPath";
	private static final String JSP_GET_NEWS_STATUS = "newsStatus";
	private static final String SESSION_USER_ID = "userID";
	
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String newsID = request.getParameter(JSP_GET_NEWS_ID);
		String newsTitle = request.getParameter(JSP_GET_NEWS_TITLE);
		String newsDate= request.getParameter(JSP_GET_NEWS_DATE);
		String newsBrief = request.getParameter(JSP_GET_NEWS_BRIEF_NEWS);
		String newsContent = request.getParameter(JSP_GET_NEWS_BRIEF_CONTENT);
		String newsImgPath = request.getParameter(JSP_GET_NEWS_IMAGE_PATH);
		String newsStatus = request.getParameter(JSP_GET_NEWS_STATUS);
		Integer userID = (Integer) request.getSession().getAttribute(SESSION_USER_ID);
		
		
		News newNews = new News(newsTitle, newsBrief, newsContent, newsDate, newsImgPath, Integer.parseInt(newsStatus),userID);
		
		System.out.println("new News from Add News " + newNews.toString());
		
		List<News> newsList;
		
		try {
			
			
			newsService.save(newNews);
			newsList = newsService.list();
			request.setAttribute("news", newsList);
			request.setAttribute("presentation", "newsList");
			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			                             	
			
		} catch (ServiceException e) {
			
			request.setAttribute("error", "error");
			request.setAttribute("errorE", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
	
	
	
	
	
	}

}
