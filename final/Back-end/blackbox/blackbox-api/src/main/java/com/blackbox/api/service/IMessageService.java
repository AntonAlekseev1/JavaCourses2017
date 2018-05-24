package com.blackbox.api.service;

import java.util.List;

import com.blackbox.beans.Message;

public interface IMessageService {

	public List<Message> getDialogMessages(Integer id) throws Exception;

	public void sendMessage(Message message, Integer id) throws Exception;
	
	public void deleteMessage(Integer messageId) throws Exception;

}
