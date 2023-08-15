package by.itacademy.htp.ex.dao;

import java.util.List;

import by.itacademy.htp.ex.bean.NewUserInfo;

public interface IUserDAO {
	
	NewUserInfo logination(String login, String password) throws UserDaoException;
	boolean registrationDB(NewUserInfo user) throws UserDaoException;
	String getRole(String login, String password) throws UserDaoException;
	List<NewUserInfo> getUsersList() throws UserDaoException;
	boolean editUserDetail(NewUserInfo userId) throws UserDaoException;
	NewUserInfo getUserDetail(int userId) throws UserDaoException;
}
