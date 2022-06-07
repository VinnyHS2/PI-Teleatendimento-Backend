package com.pi.teleatendimento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.teleatendimento.dto.RaDto;
import com.pi.teleatendimento.dto.WebSocketChannelDto;

import lombok.NonNull;

@Service
public class AtendimentoService {
	@Autowired
	WebSocketService socketService;
		
	public void finalizarProfessor(@NonNull RaDto dto) throws Exception {
		
		WebSocketChannelDto response = WebSocketChannelDto.builder()
				.tipo("finalizar-professor")
				.topico(dto.getRa())
				.build();
		
		socketService.notifyMessageChannel(response);
		
	}

	public void finalizarAluno(@NonNull RaDto dto) throws Exception {
		
		WebSocketChannelDto response = WebSocketChannelDto.builder()
				.tipo("finalizar-aluno")
				.topico(dto.getRa())
				.build();
		
		socketService.notifyMessageChannel(response);
		
	}
	
}
