package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;

import by.itacademy.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAddNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		if (request.getSession(false)!=null) {
			
			request.getSession().setAttribute("link", "go_to_add_news");
			request.setAttribute(ConstName.ATTRIBUTE_TO_GO_ADD_NEWS_PAGE, "addNews");
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			
		}
		
		else {
			
			response.sendRedirect("controller?command=go_to_base_page");
			
		}
		
						
			
		}
		

	}

