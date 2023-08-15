package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;

import by.itacademy.htp.ex.controller.Command;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ChangeLocale implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String local = request.getParameter("local");

		if (request.getSession(false) != null) {

	    	String link = (String) request.getSession().getAttribute("link");

			request.getSession(true).setAttribute("local", local);
			request.getRequestDispatcher("controller?command=" + link).forward(request, response);
			
		}

		else {
			
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

		}

	}

}
