package com.hotel.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hotel.api.dao.IUserDAO;
import com.hotel.been.User;

public class UserDAO extends AbstractDao<User> implements IUserDAO {

	private static final Logger logger = Logger.getLogger(UserDAO.class);
	private static UserDAO instance;
	private Class<User> clazz = User.class;
	private final String GET_BY_LOGIN_EXCEPTION = "Exception in the method getByLogin: ";

	private UserDAO() {
		super(User.class);

	}

	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	@Override
	public User getByLogin(Session session, String login) throws Exception {

		User user = null;
		try {
			Criteria criteria = session.createCriteria(clazz).add(Restrictions.eq("login", login));
			user = (User) criteria.uniqueResult();
		} catch (Exception e) {
			logger.error(GET_BY_LOGIN_EXCEPTION);
			throw new Exception(GET_BY_LOGIN_EXCEPTION, e);
		}
		return user;

	}

}
