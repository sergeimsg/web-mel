package by.itacademy.htp.ex.dao;

import by.itacademy.htp.ex.bean.NewUserInfo;

public interface IUserDAO {
	
	NewUserInfo logination(String login, String password) throws UserDaoException;
	boolean registrationDB(NewUserInfo user) throws UserDaoException;
	String getRole(String login, String password) throws UserDaoException;

}
