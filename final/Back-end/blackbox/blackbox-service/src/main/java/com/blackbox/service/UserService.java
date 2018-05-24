package com.blackbox.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackbox.api.dao.IUserDao;
import com.blackbox.api.service.IUserService;
import com.blackbox.beans.User;
/**
 * The Class UserService that manages users.
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
@Service("userService")
@Transactional
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	public UserService() {

	}

	@Override
	public List<User> getAllUsers() throws Exception {

		List<User> list = userDao.getAll("id");

		return list;
	}

	@Override
	public User getUserById(Integer id) throws Exception {

		return userDao.getById(id);
	}

	@Override
	public List<User> search(String name) throws Exception {

		List<User> users = new ArrayList<>();
		List<User> allUsers = userDao.getAll(null);
		for(User user: allUsers) {
			if(user.getName().equals(name)) {
				users.add(user);
			}
		}
		return users;
	}

	@Override
	public User getUserByLogin(String login) throws Exception {

		return userDao.getByLogin(login);
	}

	@Override
	public void addUser(User user) throws Exception {

		userDao.create(user);
	}

	@Override
	public void updateUser(User user) throws Exception {

		userDao.update(user);
	}

	@Override
	public void removeUser(Integer id) throws Exception {

		userDao.delete(userDao.getById(id));
	}

	@Override
	public List<User> getFriends(Integer id) throws Exception {
		return userDao.getFriends(id);
	}

	@Override
	public void addToFriends(Integer userId, Integer friendId) throws Exception {

		User user = userDao.getById(userId);
		User friend = userDao.getById(friendId);
		Set<User> userFrends = user.getFriends();
		userFrends.add(friend);
		user.setFriends(userFrends);
		
	}

	@Override
	public void removeFromFriends(Integer userId, Integer friendId) throws Exception {

		User user = userDao.getById(userId);
		User frend = userDao.getById(friendId);
		Set<User> userFriends = user.getFriends();
		userFriends.remove(frend);
		user.setFriends(userFriends);
	}

	@Override
	public List<User> getDialogUsers(Integer id) throws Exception {
		
		return userDao.getDialogUsers(id);
	}

	@Override
	public List<User> getGroupUsers(Integer id) throws Exception {
		
		return userDao.getGroupUsers(id);
	}

	@Override
	public User getGroupAdmin(Integer id) throws Exception {
		
		return userDao.getGroupAdmin(id);
	}

}
