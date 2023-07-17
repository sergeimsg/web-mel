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
		
		request.getSession().setAttribute("link", "go_to_edit_news");
		
		try {
			
			newsEdit = newsServiceEdit.findById(Integer.parseInt(id));
			request.setAttribute("edit", "edit");
			request.setAttribute("newsEdit", newsEdit);
			
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			
			
			
		} catch (ServiceException e) {
			
			session.setAttribute("error", "error");
			session.setAttribute("errorE", e.getLocalizedMessage());
			request.getRequestDispatcher("WEB-INF/pages/tiles/error.jsp").forward(request, response);
		}
		
		
		

	}

}
