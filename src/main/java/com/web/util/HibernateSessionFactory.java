package com.web.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	
	//singleton
	
	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		if(sessionFactory == null) {
						
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.url", System.getenv("PostgresURL"))
					.setProperty("hibernate.connection.username", System.getenv("Postgres Username"))
					.setProperty("hibernate.connection.password", System.getenv("Postgres Password"))
					.buildSessionFactory();
		}
		return sessionFactory.getCurrentSession();
	
	}
}
