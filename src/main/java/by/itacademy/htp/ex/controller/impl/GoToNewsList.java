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

public class GoToNewsList implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<News> newsList;

		request.getSession().setAttribute(ConstName.LINK_TO_SHOW_PREVOUS_URL, "go_to_news_list");

		try {

			if (request.getSession(false) != null) {

				newsList = newsService.list();
				request.setAttribute(ConstName.ATTTIBUTE_PRESENTATION_TO_SHOW_NEWS, "newsList");

				if (newsList.isEmpty()) {

					request.setAttribute(ConstName.ATTRIBUTE_NEWS_TO_SHOW_NEWS, null);

				} else {

					request.setAttribute(ConstName.ATTRIBUTE_NEWS_TO_SHOW_NEWS, newsList);

				}

				request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

			}

			else {

				response.sendRedirect("controller?command=go_to_base_page");

			}

		} catch (ServiceException e) {

			response.sendRedirect("controller?command=go_to_error_page");

		}

	}

}
