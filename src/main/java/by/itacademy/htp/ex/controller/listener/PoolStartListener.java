package by.itacademy.htp.ex.controller.listener;

import by.itacademy.htp.ex.dao.conpool.ConnectionPool;
import by.itacademy.htp.ex.dao.conpool.Exception.ConnectionPoolException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class PoolStartListener implements ServletContextListener {

	private static final ConnectionPool connectionPoolListener = ConnectionPool.getInstance();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ServletContextListener.super.contextInitialized(sce);
		ServletContext context = sce.getServletContext();
		try {
		
			connectionPoolListener.initPoolData();

		} catch (ConnectionPoolException e) {
			
			throw new RuntimeException("Pool doesn't start", e);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
				
		
		try {
			connectionPoolListener.dispose();
		} catch (ConnectionPoolException e) {
			
			throw new RuntimeException(e);
		}
	

	}

}
