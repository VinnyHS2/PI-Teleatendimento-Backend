package com.pi.teleatendimento.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.teleatendimento.dto.WebSocketChannelDto;
import com.pi.teleatendimento.service.WebSocketService;


@Validated
@RestController
@RequestMapping(path = { "/websocket" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class WebSocketController {
	
	@Autowired
	private WebSocketService webSocketService;
	
	@PostMapping("/message")
	public ResponseEntity<?> notifyMessageChannel(@Valid @RequestBody WebSocketChannelDto message) throws Exception {
		this.webSocketService.notifyMessageChannel(message);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/topic/all")
	public ResponseEntity<?> listAllTopic() throws Exception {
		
		this.webSocketService.listAllTopic();
		return ResponseEntity.ok().build();
	}
	
}
