package by.itacademy.htp.ex.service;

import by.itacademy.htp.ex.service.impl.NewsServiceImpl;
import by.itacademy.htp.ex.service.impl.UserServiceImpl;

public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();
	
	private ServiceProvider() {}
	
	private final IUserService userService = new UserServiceImpl();
	private final INewsService newsService = new NewsServiceImpl();
	
	public INewsService getNewsService() {
		return newsService;
	}


	public IUserService getUserService() {
		return userService;
	}
	
	
	public static ServiceProvider getInstance() {
		return instance;
	}

}
