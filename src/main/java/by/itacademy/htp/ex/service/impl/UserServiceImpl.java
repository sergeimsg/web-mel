package by.itacademy.htp.ex.service.impl;

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

}
