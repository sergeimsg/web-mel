package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;

import by.itacademy.htp.ex.bean.News;
import by.itacademy.htp.ex.controller.Command;
import by.itacademy.htp.ex.service.INewsService;
import by.itacademy.htp.ex.service.ServiceException;
import by.itacademy.htp.ex.service.ServiceProvider;
import by.itacademy.htp.ex.util.ConstName;
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

		request.getSession().setAttribute(ConstName.LINK, stringBuilder.append(id));
		
		try {
			
			news = newsService.findById(Integer.parseInt(id));
			request.setAttribute(ConstName.NEWS, news);
			request.setAttribute(ConstName.PRESENTATION, ConstName.VIEWNEWS);

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

		} catch (ServiceException e) {

			request.setAttribute(ConstName.ERROR, ConstName.ERROR);
			request.setAttribute("errorE", "empty fields or wrong data");

			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

		}

	}

}
