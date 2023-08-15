package by.itacademy.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.itacademy.htp.ex.bean.NewUserInfo;
import by.itacademy.htp.ex.controller.Command;
import by.itacademy.htp.ex.service.IUserService;
import by.itacademy.htp.ex.service.ServiceException;
import by.itacademy.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToUsersList implements Command {
	
	    private final IUserService userService = ServiceProvider.getInstance().getUserService();
		
		

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<NewUserInfo> usersList;
		request.getSession().setAttribute(ConstName.LINK_TO_SHOW_PREVOUS_URL, "go_to_users_list");
		request.setAttribute("users_list", "users_list");
		
		
		try {
			
	
			usersList = userService.viewUsers();
			
			request.setAttribute("userList", usersList);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} 
		

		
		catch (ServiceException e) {
			
			
			response.sendRedirect("error.jsp");
			
		}
		
	
	}

}
