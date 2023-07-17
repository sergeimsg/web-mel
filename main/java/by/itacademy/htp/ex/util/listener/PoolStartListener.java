package by.itacademy.htp.ex.util.listener;

import by.itacademy.htp.ex.dao.conPool.ConnectionPool;
import by.itacademy.htp.ex.dao.conPool.ConnectionPoolException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class PoolStartListener implements ServletContextListener {

	private static final ConnectionPool connectionPoolListener = ConnectionPool.getInstance();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextInitialized(sce);
		ServletContext context = sce.getServletContext();
		try {
		//	System.out.println("Listener works");
			connectionPoolListener.initPoolData();

		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			context.log("Connection pool doesn't start", e);
			
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
		ServletContextListener.super.contextDestroyed(sce);
		connectionPoolListener.dispose();
	//	System.out.println("Listener closed");

	}

}
