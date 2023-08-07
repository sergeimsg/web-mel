package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;

import by.itacademy.htp.ex.controller.Command;
import by.itacademy.htp.ex.util.ConstName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToRegistrationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession(true).setAttribute(ConstName.LINK, "go_to_registration_page");
		                                               
		request.setAttribute("user2","register");
	
	
		request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		
		
	}

}
