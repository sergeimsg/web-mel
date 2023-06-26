package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocale implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String link=null;
				
		String local = request.getParameter("local");
		
		String linkURL = request.getRequestURI();
		String commandName = request.getParameter("command");
		System.out.println(linkURL+"--**"+commandName);
		
		if (request.getSession() != null) {

			link = (String) request.getSession().getAttribute("link");
			
			System.out.println(link);
		}

		request.getSession(true).setAttribute("local", local);
	//	request.getRequestDispatcher("controller?command=" + commandName).forward(request, response);
		request.getRequestDispatcher("controller?command=go_to_base_page").forward(request, response);
		

	}

}
