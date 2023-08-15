package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;

import by.itacademy.htp.ex.bean.NewUserInfo;
import by.itacademy.htp.ex.controller.Command;
import by.itacademy.htp.ex.service.IUserService;
import by.itacademy.htp.ex.service.ServiceException;
import by.itacademy.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToUserDetail implements Command {

	private final IUserService userService = ServiceProvider.getInstance().getUserService();
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("userDetail", "userDetail");

		NewUserInfo userEdit;
		
		StringBuilder stringBuilderUserLink = new StringBuilder("go_to_user_detail&userId=");

		String userId = request.getParameter("userId");
		
		request.getSession(true).setAttribute(ConstName.LINK_TO_SHOW_PREVOUS_URL, stringBuilderUserLink.append(userId).toString());

		try {
			
			
			userEdit = userService.viewUserById(Integer.parseInt(userId));
			request.setAttribute("userById", userEdit);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			

		} catch (ServiceException e) {
			
			response.sendRedirect("error.jsp");
			
						
		}

	}

}
