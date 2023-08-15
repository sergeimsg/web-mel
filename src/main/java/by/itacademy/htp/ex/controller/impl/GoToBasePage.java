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
import jakarta.servlet.http.HttpSession;

public class GoToBasePage implements Command{
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			List<News> latestNews;
			HttpSession session = request.getSession(true);
			session.setAttribute(ConstName.LINK_TO_SHOW_PREVOUS_URL, "go_to_base_page");
			                                               
		
		try {
			
			latestNews = newsService.latestList(5);
			request.setAttribute(ConstName.ATTRIBUTE_TO_SHOW_LATEST_NEWS, "latestNews");
			request.setAttribute(ConstName.SHOW_LATEST_NEWS, latestNews);				

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			
		} catch (ServiceException e) {
			
			 
			  response.sendRedirect("controller?command=go_to_error_page");
			 
		}
		
		
	}

}
