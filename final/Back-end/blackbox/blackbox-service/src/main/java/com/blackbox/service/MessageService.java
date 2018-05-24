package com.blackbox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackbox.api.dao.IMessageDao;
import com.blackbox.api.service.IMessageService;
import com.blackbox.beans.Message;
/**
 * The Class MessageService that manages messages.
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
@Service("messageService")
@Transactional
public class MessageService implements IMessageService {

	@Autowired
	private IMessageDao messageDao;

	@Override
	public List<Message> getDialogMessages(Integer id) throws Exception {

		return messageDao.getDialogMessages(id);
	}

	@Override
	public void sendMessage(Message message, Integer id) throws Exception {

		messageDao.create(message);
		
	}

	@Override
	public void deleteMessage(Integer messageId) throws Exception {

		Message message = messageDao.getById(messageId);
		messageDao.delete(message);
	}

}
