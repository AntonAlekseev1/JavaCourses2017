package com.hotel.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hotel.been.LogEntity;
import com.hotel.dao.LogDAO;
import com.hotel.dao.hibernateutil.HibernateUtil;

public class LogEntityService {
	
	private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	private static LogEntityService instance;
	private LogDAO logDao = LogDAO.getInstance();
	
	private LogEntityService() {
		
	}

	public static LogEntityService getInstance() {
		if(instance==null) {
			instance = new LogEntityService();
		}
		return instance;
	}
	
	public String addLog(LogEntity logg) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			logDao.create(session, logg);
			transaction.commit();
		return null;
		} catch (Exception e) {
			if(transaction!=null) {
			transaction.rollback();
			}
			throw new Exception(e);
		}
		
	}
	
	public List<LogEntity> getAll() throws Exception{
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			logDao.getAll(session, "date");
			transaction.commit();
		return null;
		} catch (Exception e) {
			if(transaction!=null) {
			transaction.rollback();
			}
			throw new Exception(e);
		}
	}


}
