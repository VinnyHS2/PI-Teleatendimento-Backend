package com.pi.teleatendimento.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.teleatendimento.dto.AtendimentoDto;
import com.pi.teleatendimento.dto.HistoricoAtendimentoDto;
import com.pi.teleatendimento.dto.RaDto;
import com.pi.teleatendimento.dto.RegistrarAtendimentoDto;
import com.pi.teleatendimento.dto.WebSocketChannelDto;
import com.pi.teleatendimento.model.Atendimento;
import com.pi.teleatendimento.repository.AtendimentoRepository;

import lombok.NonNull;

@Service
public class AtendimentoService {
	
	@Autowired
	WebSocketService socketService;
		
	@Autowired
	AtendimentoRepository atendimentoRepository;
	
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

	public void registrar(@NonNull RegistrarAtendimentoDto dto) throws Exception {
		
		Atendimento atend = Atendimento.builder()
				.professor(dto.getProfessor())
				.ra(dto.getRa())
				.createdDate(LocalDateTime.now())
				.build();
		
		atendimentoRepository.save(atend);
		
	}

	public HistoricoAtendimentoDto buscaRa(@NonNull RaDto dto) throws Exception {
		
		List<Atendimento> atendimento = atendimentoRepository.findByRa(dto.getRa());
		
		List<AtendimentoDto> atendimentoDto = new ArrayList<AtendimentoDto>();
		
		atendimento.forEach(atend -> {
			
			AtendimentoDto atendDto = AtendimentoDto.builder()
					.ra(atend.getRa())
					.professor(atend.getProfessor())
					.createDate(atend.getCreatedDate())
					.avaliacao(atend.getAvaliacao())
					.build();
			
			atendimentoDto.add(atendDto);
			
		});
		
		HistoricoAtendimentoDto histDto = HistoricoAtendimentoDto.builder()
				.historico(atendimentoDto)
				.build();
		
		return histDto;
		
	}
	
}
