package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;

import by.itacademy.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAddNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			request.getSession(true).setAttribute("link", "go_to_add_news");
				
			
			request.setAttribute("addNews", "addNews");
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			
			
			
		}
		

	}

