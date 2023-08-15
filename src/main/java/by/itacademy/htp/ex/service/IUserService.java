package by.itacademy.htp.ex.service;

import java.util.List;

import by.itacademy.htp.ex.bean.NewUserInfo;

public interface IUserService {
	
	NewUserInfo signIn(String login, String password) throws ServiceException;
	boolean registration(NewUserInfo user) throws ServiceException;
	List<NewUserInfo> viewUsers() throws ServiceException;
	NewUserInfo viewUserById(int userId) throws ServiceException;
	boolean editUser(NewUserInfo userEdit) throws ServiceException;
	
	

}
