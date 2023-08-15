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
import jakarta.servlet.http.HttpSession;

public class GoToEditNews implements Command {

	private final INewsService newsServiceEdit = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id;
		News newsEdit;

		id = request.getParameter("id");
		HttpSession session = request.getSession();
		request.getSession().setAttribute(ConstName.LINK_TO_SHOW_PREVOUS_URL, "go_to_edit_news&id=" + id);

		try {
			
			if (request.getSession(false) != null) {
	
			newsEdit = newsServiceEdit.findById(Integer.parseInt(id));
			request.setAttribute(ConstName.ATTRIBUTE_NEWS_TO_EDIT_NEWS, "edit");
			request.setAttribute("newsEdit", newsEdit);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			
			}
			
			else {
						
				response.sendRedirect("controller?command-go_to_base_page");
				
			}
			

		} catch (ServiceException e) {

			session.setAttribute(ConstName.ATTRIBUTE_FOR_MESSAGE_ERROR_PAGE, "empty fields or wrong data");
			response.sendRedirect("controller?command=go_to_error_page");
		}

	}

}
