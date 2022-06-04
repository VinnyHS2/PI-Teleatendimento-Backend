package com.pi.teleatendimento.service;

import java.util.ArrayList;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.teleatendimento.dto.ChamarProximoDto;
import com.pi.teleatendimento.dto.RaDto;
import com.pi.teleatendimento.dto.PosicaoFilaDto;
import com.pi.teleatendimento.dto.QuantidadeFilaDto;
import com.pi.teleatendimento.dto.WebSocketChannelDto;
import com.pi.teleatendimento.exceptions.BadRequestException;
import com.pi.teleatendimento.exceptions.NotFoundException;

import lombok.NonNull;

@Service
public class FilaService {
	@Autowired
	WebSocketService socketService;
		
	private ArrayList<String> fila = new ArrayList<String>();
	
	public PosicaoFilaDto inserirFila(@NonNull RaDto dto) throws Exception {
		
		if (!NumberUtils.isCreatable(dto.getRa())) 
			throw new BadRequestException("RA não deve ser um número");
		
		Integer index = fila.indexOf(dto.getRa()); 

		if (index == -1) {
			fila.add(dto.getRa());
			index = fila.indexOf(dto.getRa()); 
		}
		
		PosicaoFilaDto response = PosicaoFilaDto.builder().posicao(index + 1).build();

		return response;
	}
	
	public RaDto ChamarProximo(@NonNull ChamarProximoDto dto) throws Exception {
		
		if(fila.isEmpty()) {
			throw new NotFoundException("A fila está vazia");
		}
		
		String ra = fila.get(0);
		fila.remove(ra);
		
		WebSocketChannelDto response = WebSocketChannelDto.builder()
				.tipo("iniciar")
				.topico(ra)
				.payload(dto.getIdSala())
				.build();
		
		socketService.notifyMessageChannel(response);
		
		RaDto responseDto = RaDto.builder()
				.ra(ra)
				.build();
		
		return responseDto;
		
	}
	
	public void limparFila() throws Exception {
		fila.clear();
	}

	public QuantidadeFilaDto quantidadeFila() throws Exception {
		
		QuantidadeFilaDto dto = QuantidadeFilaDto.builder()
				.quantidade(fila.size())
				.build();
		
		return dto;
		
	}

	public void sairFila(RaDto dto) throws Exception {
		fila.remove(dto.getRa());
	}
	
	public PosicaoFilaDto retornarFinalFila(RaDto dto) throws Exception {
		fila.remove(dto.getRa());
		
		if (!NumberUtils.isCreatable(dto.getRa())) 
			throw new BadRequestException("RA não deve ser um número");
		
		Integer index = fila.indexOf(dto.getRa()); 

		if (index == -1) {
			fila.add(dto.getRa());
			index = fila.indexOf(dto.getRa()); 
		}
		
		PosicaoFilaDto response = PosicaoFilaDto.builder().posicao(index + 1).build();

		return response;
	}
}
