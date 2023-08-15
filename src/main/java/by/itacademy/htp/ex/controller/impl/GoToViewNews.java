package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;

import by.itacademy.htp.ex.bean.News;
import by.itacademy.htp.ex.controller.Command;
import by.itacademy.htp.ex.service.INewsService;
import by.itacademy.htp.ex.service.ServiceException;
import by.itacademy.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		News news;
		String id;

		id = request.getParameter("id");
		StringBuilder stringBuilder = new StringBuilder("go_to_view_news&id=");
		request.getSession().setAttribute(ConstName.LINK_TO_SHOW_PREVOUS_URL, stringBuilder.append(id).toString());
		
		try {
			
			news = newsService.findById(Integer.parseInt(id));
			request.setAttribute(ConstName.ATTRIBUTE_NEWS_TO_SHOW_NEWS, news);
			request.setAttribute(ConstName.ATTTIBUTE_PRESENTATION_TO_SHOW_NEWS, ConstName.ATTTIBUTE_VIEW_NEWS_TO_SHOW_NEWS);

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

		} catch (ServiceException e) {

			response.sendRedirect("controller?command=go_to_error_page");

		}

	}

}
