package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;

import by.itacademy.htp.ex.bean.NewUserInfo;
import by.itacademy.htp.ex.controller.Command;
import by.itacademy.htp.ex.service.IUserService;
import by.itacademy.htp.ex.service.ServiceException;
import by.itacademy.htp.ex.service.ServiceProvider;
import by.itacademy.htp.ex.util.validation.DataValidationException;
import by.itacademy.htp.ex.util.validation.UserDataValidation;
import by.itacademy.htp.ex.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoUpdateUser implements Command {
	
	private final IUserService userService = ServiceProvider.getInstance().getUserService();
	
	private final UserDataValidation userDataValidationService = ValidationProvider.getInstance().getUserDataValidationInstance();

	private static final String JSP_ID_USER = "userId";
	private static final String JSP_NAME = "name";
	private static final String JSP_SURNAME = "surname";
	private static final String JSP_LOGIN = "login";
	private static final String JSP_EMIAL = "email";
	private static final String JSP_ROLE = "role";
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NewUserInfo userUpdate;
		int userIdEdit;
		String name;
		String surname;
		String login;
		String email;
		String role;
		String password = "xx";
		
		userIdEdit =Integer.parseInt(request.getParameter(JSP_ID_USER));
		name = request.getParameter(JSP_NAME);
		surname = request.getParameter(JSP_SURNAME);
		login = request.getParameter(JSP_LOGIN);
		email = request.getParameter(JSP_EMIAL);
		role = request.getParameter(JSP_ROLE);
		
		userUpdate = new NewUserInfo(userIdEdit,name,surname,login,email,role);
		
			
		try {

			if (userDataValidationService.checkRegistrationForm(login, password, name, surname, email) && 
					request.getSession(false)!= null) {
				
				
				userService.editUser(userUpdate);

		
				response.sendRedirect("controller?command=go_to_users_list");

			} else {

				response.sendRedirect("controller?command=go_to_base_page");

			}

		} catch (ServiceException | DataValidationException e) {

			
			response.sendRedirect("controller?command=go_to_error_page");
			
		}

		

	}

}
