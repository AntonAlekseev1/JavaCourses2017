package com.blackbox.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackbox.api.dto.IDto;
import com.blackbox.api.service.IDialogService;
import com.blackbox.beans.Dialog;
import com.blackbox.controler.dto.DialogDto;
import com.blackbox.util.DtoWorker;
import com.blackbox.util.JsonParser;

@RestController
@RequestMapping("dialog")
public class DialogControler {

	private static final Logger logger = Logger.getLogger(DialogControler.class);
	@Autowired
	private IDialogService dialogService;
	private DozerBeanMapper dozer = new DozerBeanMapper();

	@GetMapping("/{id}")
	public Object getById(@PathVariable Integer id) {
		try {
			return dozer.map(dialogService.getById(id), DialogDto.class);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@PutMapping
	public Object create(@RequestBody DialogDto dialogDto) {
		try {
			Dialog dialog = dozer.map(dialogDto, Dialog.class);
			dialogService.createDialog(dialog);
			return dozer.map(dialogService.getByHeader(dialog.getHeader()), DialogDto.class);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@DeleteMapping("/{id}")
	public Map<String, Object> removeDialog(@PathVariable Integer id) {
		Map<String, Object> response;
		try {
			response = new HashMap<>();
			dialogService.removeDialog(id);
			response.put("OK",new DialogDto());
			return response;//JsonParser.convertToJson("OK");
		} catch (Exception e) {
			response = new HashMap<>();
			response.put("ERROR", e.getMessage());
			return response;
		}
	}

	@GetMapping("/user/{id}")
	public List<IDto> getUserDialogs(@PathVariable Integer id) {
		try {
			return DtoWorker.listToDto(dialogService.getUserDialogs(id), DialogDto.class);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@GetMapping("/between/{firstId}/{secondId}")
	public Object getDialogBetweenUsers(@PathVariable Integer firstId, @PathVariable Integer secondId) {
		try {
			return dozer.map(dialogService.getDialogBetweenUsers(firstId, secondId), DialogDto.class);
		} catch (Exception e) {
			return JsonParser.convertToJson(e.getMessage());
		}
	}

}
