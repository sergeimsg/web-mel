package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	private final IUserService userService = ServiceProvider.getInstance().getUserService();
	
	private static final String JSP_NAME = "name";
	private static final String JSP_SURNAME = "surname";
	private static final String JSP_LOGIN = "login";
	private static final String JSP_PASSWORD = "password";
	private static final String JSP_EMIAL = "email";
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name;
		String surname;
		String login;
		String password;
		String email;
		String role = "user";
		
		name = request.getParameter(JSP_NAME);
		surname = request.getParameter(JSP_SURNAME);
		login = request.getParameter(JSP_LOGIN);
		password = request.getParameter(JSP_PASSWORD);
		email = request.getParameter(JSP_EMIAL);
		
		NewUserInfo newUser = new NewUserInfo(name, surname, login, password, email, role);
		
	//	System.out.println("Do registration  ---  " + newUser.toString());
		
		try {
			
			
			if (userService.registration(newUser)) {
				
				request.getSession(true).setAttribute("welcome", "registered");
				response.sendRedirect("controller?command=go_to_registration_page");
				
			} else {
				
				
				response.sendRedirect("controller?command=go_to_base_page");
				
				
							
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
