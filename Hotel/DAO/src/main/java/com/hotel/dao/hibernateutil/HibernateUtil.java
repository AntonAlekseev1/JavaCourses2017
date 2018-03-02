package com.hotel.dao.hibernateutil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessyonFactory = null;
	private static ServiceRegistry serviceRegistry;

	private HibernateUtil() {

	}

	private static SessionFactory bildSessionFactory() throws Exception {
		try {
			Configuration cfg = new Configuration().configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();

			sessyonFactory = cfg.buildSessionFactory(serviceRegistry);
			return sessyonFactory;
		} catch (Throwable e) {
			throw new Exception(e);
		}
	}

	public static SessionFactory getSessionFactory() throws Exception {
		if (sessyonFactory == null) {
			bildSessionFactory();
		}
		return sessyonFactory;
	}

}
