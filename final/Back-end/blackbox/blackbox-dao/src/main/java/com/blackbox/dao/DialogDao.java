package com.blackbox.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.blackbox.api.dao.IDialogDao;
import com.blackbox.beans.Dialog;
import com.blackbox.beans.User;
import com.blackbox.dao.generic.AbstractDao;

/**
 * Class for working with a database layer, extends {@link AbstractDao}
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
@Repository
public class DialogDao extends AbstractDao<Dialog> implements IDialogDao {

	public DialogDao() {
		super(Dialog.class);
	}

	/**
	 * This method makes the query in the database and returns a dialog whose header
	 * is equal to the header passed to the method like as parameter
	 * 
	 * @return {@link Dialog}
	 */
	@Override
	public Dialog getByHeader(String name) throws Exception {

		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Dialog> query = builder.createQuery(Dialog.class);
		Root<Dialog> dialog = query.from(Dialog.class);
		query.select(dialog).where(builder.equal(dialog.get("name"), name));

		return entityManager.createQuery(query).getSingleResult();
	}

	/**
	 * This method makes the query in the database and returns a list of user
	 * dialogs with id equal to userId passed to the method like as parameter
	 * 
	 * @return list of dialogs
	 */
	@Override
	public List<Dialog> getUserDialogs(Integer userId) throws Exception {

		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Dialog> query = builder.createQuery(Dialog.class);
		Root<User> root = query.from(User.class);
		Join<User, Dialog> users = root.join("dialogs");
		query.select(users).where(builder.equal(root, userId));
		return entityManager.createQuery(query).getResultList();

	}

}
