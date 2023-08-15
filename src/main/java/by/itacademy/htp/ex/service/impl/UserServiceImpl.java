package by.itacademy.htp.ex.service.impl;

import java.util.List;

import by.itacademy.htp.ex.bean.NewUserInfo;
import by.itacademy.htp.ex.dao.UserDaoException;
import by.itacademy.htp.ex.dao.DaoProvider;
import by.itacademy.htp.ex.dao.IUserDAO;
import by.itacademy.htp.ex.service.ServiceException;
import by.itacademy.htp.ex.service.IUserService;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();


	@Override
	public NewUserInfo signIn(String login, String password) throws ServiceException {

	
		NewUserInfo userService = null;

		try {
	

			userService = userDAO.logination(login, password);

			return userService;

		} catch (UserDaoException e) {
			
			throw new ServiceException("Error in DB when sign in",e);
		}

	}

	@Override
	public boolean registration(NewUserInfo user) throws ServiceException {

		try {
			
			userDAO.registrationDB(user);
		} catch (UserDaoException e) {

			throw new ServiceException("Error in DB when send registration data",e);
		}

		return true;

	}

	@Override
	public List<NewUserInfo> viewUsers() throws ServiceException {
		
		try {
			
		return 	userDAO.getUsersList();
						
		} catch (UserDaoException e) {
			
			throw new ServiceException("No users", e);
			
		}
				
		
	}
	
	

	@Override
	public boolean editUser(NewUserInfo userEdit) throws ServiceException {
		
		boolean editPassed = false;
		
		try {
			
			if (userDAO.editUserDetail(userEdit)) {
				
				
				editPassed = true;
				
			}
			
			else {
				editPassed = false;
			}
			
			
		} catch (UserDaoException e) {
		
			throw new ServiceException("No edit user", e);
			
		}
		return editPassed;
	}

	@Override
	public NewUserInfo viewUserById(int userId) throws ServiceException {
	
		try {
			
		
			
			return userDAO.getUserDetail(userId);
			
		} catch (UserDaoException e) {
		
			throw new ServiceException("No user", e);
			
		}
		
	}
	
	
	

}
