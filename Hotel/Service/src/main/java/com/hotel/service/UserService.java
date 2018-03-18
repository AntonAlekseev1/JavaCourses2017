package com.hotel.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hotel.api.service.IUserService;
import com.hotel.been.User;
import com.hotel.dao.UserDAO;
import com.hotel.dao.hibernateutil.HibernateUtil;

public class UserService implements IUserService {
	
	private static UserService instance;
	private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	private UserDAO userDao = UserDAO.getInstance();

	public static UserService getInstance() {
		if(instance==null) {
			instance = new UserService();
		}
		return instance;
	}

	@Override
	public void addUser(User user) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			userDao.create(session, user);
			transaction.commit();
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
				}
			throw new Exception(e);
		} 
		
	}

	@Override
	public User getUser(String login, String password) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<User> list = userDao.getAll(session, "id");
			User us =null;
			for(User user : list) {
				if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
					us= user;
				}
			}
			transaction.commit();
			return us;
		} catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
				}
			throw new Exception(e);
		}
		
	}



}
