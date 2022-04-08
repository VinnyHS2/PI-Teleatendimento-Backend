package com.pi.teleatendimento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi.teleatendimento.dto.WebSocketChannelDto;

import lombok.NonNull;

@Service
public class WebSocketService {

	@Autowired
	SimpMessagingTemplate websocketTemplate;

	@Autowired
	ObjectMapper mapper;
	
	
	public void notifyMessageChannel(@NonNull WebSocketChannelDto message) throws Exception {
		websocketTemplate.convertAndSend("/" + message.getTopico(), mapper.writeValueAsString(message));
	}
	
	public void listAllTopic() throws Exception {
		
	}
	
}
