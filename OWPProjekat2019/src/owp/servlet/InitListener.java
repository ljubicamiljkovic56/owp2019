package owp.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import owp.dao.ConnectionManager;

public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");

	}
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("ServletContextListener started");
		System.out.println("pokretanje...");
		
		ConnectionManager.open();
		
		System.out.println("kraj");

	}


}
