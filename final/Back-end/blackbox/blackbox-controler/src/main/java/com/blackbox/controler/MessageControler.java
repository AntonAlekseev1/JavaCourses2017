package com.blackbox.controler;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackbox.api.service.IDialogService;
import com.blackbox.api.service.IMessageService;
import com.blackbox.beans.Message;
import com.blackbox.controler.dto.MessageDto;
import com.blackbox.util.DtoWorker;
import com.blackbox.util.JsonParser;

/**
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
@RestController
@RequestMapping("message")
public class MessageControler {

	private static final Logger logger = Logger.getLogger(MessageControler.class);
	@Autowired
	private IMessageService messageService;
	@Autowired
	private IDialogService dialogService;
	private DozerBeanMapper dozer = new DozerBeanMapper();

	@GetMapping("/{id}")
	public Object getDialogMessages(@PathVariable Integer id) {
		try {
			return DtoWorker.listToDto(messageService.getDialogMessages(id), MessageDto.class);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return e;
		}
	}

	@PostMapping
	public Object sendMessage(@RequestBody MessageDto messageDto, @RequestHeader String id) {
		try {
			Message message = dozer.map(messageDto, Message.class);
			message.setDialog(dialogService.getById(Integer.valueOf(id)));
			messageService.sendMessage(message, Integer.valueOf(id));
			return JsonParser.convertToJson(messageDto);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return JsonParser.convertToJson(e.getMessage());
		}
	}

	@DeleteMapping
	public Object removeMessage(@RequestHeader String messageId) {
		try {
			messageService.deleteMessage(Integer.valueOf(messageId));
			return JsonParser.convertToJson("OK");
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return JsonParser.convertToJson(e.getMessage());
		}
	}

}
