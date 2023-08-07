package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.itacademy.htp.ex.bean.News;
import by.itacademy.htp.ex.controller.Command;
import by.itacademy.htp.ex.service.INewsService;
import by.itacademy.htp.ex.service.ServiceException;
import by.itacademy.htp.ex.service.ServiceProvider;
import by.itacademy.htp.ex.util.ConstName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNewsList implements Command {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newsList;
		
		request.getSession(true).setAttribute("link", "go_to_news_list");
		
		try {
			newsList = newsService.list();
			
			if (newsList.isEmpty()) {
				
							
				request.setAttribute("news", null);
			}
			else {
				
				request.setAttribute("news", newsList);
				
			}
			
			request.setAttribute("presentation", "newsList");
			
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			
			
		
		} catch (ServiceException e) {
			
			request.setAttribute(ConstName.ERROR, ConstName.ERROR);
			request.setAttribute("errorE", "empty fields or wrong data");
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
		
	}

}
