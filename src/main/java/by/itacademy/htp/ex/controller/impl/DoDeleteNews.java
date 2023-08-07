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

public class DoDeleteNews implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	private static final String JSP_GET_NEWS_ID_FROM_CHECKBOX = "idNewsCheckBox";
	
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
        String [] newsIdCheckbox = request.getParameterValues(JSP_GET_NEWS_ID_FROM_CHECKBOX);
		
        for (int i = 0; i < newsIdCheckbox.length; i++) {
			
		}
        
			List<News> newsList;
				
		try {
			
			
		 	newsService.delete(newsIdCheckbox);
			
			newsList = newsService.list();
			if (newsList.isEmpty()) {
				
				request.setAttribute("newsZero", "noNews");
			}
			request.setAttribute("news", newsList);
			request.setAttribute("presentation", "newsList");
			response.sendRedirect("controller?command=go_to_news_list");
		
			                             	
			
		} catch (ServiceException e) {
			
			request.setAttribute("error", "error");
			request.setAttribute("errorE", "empty fields or wrong data");
			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
	

	}

}
