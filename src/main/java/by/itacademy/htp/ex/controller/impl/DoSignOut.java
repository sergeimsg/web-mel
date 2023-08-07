package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;

import by.itacademy.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			request.getSession(true).setAttribute("user", "not active");
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		
	}

}
