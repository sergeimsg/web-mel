package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;
import by.itacademy.htp.ex.bean.NewUserInfo;
import by.itacademy.htp.ex.controller.Command;
import by.itacademy.htp.ex.service.ServiceException;
import by.itacademy.htp.ex.service.ServiceProvider;
import by.itacademy.htp.ex.util.validation.DataValidationException;
import by.itacademy.htp.ex.util.validation.UserDataValidation;
import by.itacademy.htp.ex.util.validation.ValidationProvider;
import by.itacademy.htp.ex.service.IUserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoSignIn implements Command {

	private static final IUserService service = ServiceProvider.getInstance().getUserService();

	private static final UserDataValidation serviceUserValidation = ValidationProvider.getInstance()
			.getUserDataValidationInstance();

	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_PASSWORD_PARAM = "password";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login;
		String password;

		login = request.getParameter(JSP_LOGIN_PARAM);
		password = request.getParameter(JSP_PASSWORD_PARAM);

		HttpSession session = request.getSession(true);
		
		NewUserInfo user = null;

		try {

			   serviceUserValidation.checkAuthData(login, password); 

			   user = service.signIn(login, password);

				if (user != null) {

					session.setAttribute("user", "active");
					session.setAttribute("role", user.getRole());
					session.setAttribute("userID", user.getUserID());
					response.sendRedirect("controller?command=go_to_news_list");

				}
				
				  else 
				
				{

					session.setAttribute("user", "not active");
					session.setAttribute("AuthentificationError", "wrong login or password, try again");
					response.sendRedirect("controller?command=go_to_error_page");

				}
								

		} catch (ServiceException | DataValidationException e) {

		
			session.setAttribute(ConstName.ATTRIBUTE_FOR_MESSAGE_ERROR_PAGE, "wrong login or password");
			response.sendRedirect("error.jsp");
			
			
		}

		return;

	}

}
