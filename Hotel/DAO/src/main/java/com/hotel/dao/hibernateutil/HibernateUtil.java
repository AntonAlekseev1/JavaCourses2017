package com.hotel.dao.hibernateutil;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessyonFactory = null;
	private static ServiceRegistry serviceRegistry;
	private static HibernateUtil instance;

	private HibernateUtil() {
		bildSessionFactory();
	}

	private static SessionFactory bildSessionFactory() {
		try {
			Configuration cfg = new Configuration().configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();

			sessyonFactory = cfg.buildSessionFactory(serviceRegistry);
			return sessyonFactory;
		} catch (Exception e) {
			throw new HibernateException(e);
		}
	}

	public static SessionFactory getSessionFactory()  {
		if (sessyonFactory == null) {
			bildSessionFactory();
		}
		return sessyonFactory;
	}

	public static HibernateUtil getInstance() {
		if(instance == null) {
			instance = new HibernateUtil();
		}
		return instance;
	}


}
