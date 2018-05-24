package com.blackbox.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.blackbox.api.dao.IMessageDao;
import com.blackbox.beans.Dialog;
import com.blackbox.beans.Message;
import com.blackbox.dao.generic.AbstractDao;

/**
 * Class for working with a database layer, extends {@link AbstractDao}
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
@Repository
public class MessageDao extends AbstractDao<Message> implements IMessageDao {

	public MessageDao() {
		super(Message.class);
	}

	/**
	 * This method makes the query in the database and returns a list of dialog
	 * messages
	 * 
	 * @return List of Messages
	 */
	@Override
	public List<Message> getDialogMessages(Integer dialogId) throws Exception {
		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Message> query = builder.createQuery(Message.class);
		Root<Dialog> dialog = query.from(Dialog.class);
		Join<Dialog, Message> messages = dialog.join("messages");
		query.select(messages).where(builder.equal(dialog, dialogId));
		return entityManager.createQuery(query).getResultList();

	}

}
