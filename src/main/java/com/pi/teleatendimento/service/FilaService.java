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
		
		WebSocketChannelDto mensagem = WebSocketChannelDto.builder()
				.tipo("quantidade")
				.topico("fila")
				.payload(fila.size())
				.build();
		
		socketService.notifyMessageChannel(mensagem);

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
		
		WebSocketChannelDto mensagem = WebSocketChannelDto.builder()
				.tipo("chamar")
				.topico("fila")
				.payload(fila.size())
				.build();
		
		socketService.notifyMessageChannel(mensagem);
		
		WebSocketChannelDto mensagem2 = WebSocketChannelDto.builder()
				.tipo("quantidade")
				.topico("fila")
				.payload(fila.size())
				.build();
		
		socketService.notifyMessageChannel(mensagem2);
		
		RaDto responseDto = RaDto.builder()
				.ra(ra)
				.build();
		
		return responseDto;
		
	}
	
	public void limparFila() throws Exception {
		fila.clear();
		
		WebSocketChannelDto mensagem = WebSocketChannelDto.builder()
				.tipo("quantidade")
				.topico("fila")
				.payload(fila.size())
				.build();
		
		socketService.notifyMessageChannel(mensagem);
	}

	public QuantidadeFilaDto quantidadeFila() throws Exception {
		
		QuantidadeFilaDto dto = QuantidadeFilaDto.builder()
				.quantidade(fila.size())
				.ra(fila)
				.build();
		
		return dto;
		
	}
	
	public Integer posicaoFila(String ra) {
		return fila.indexOf(ra);
	}

	public void sairFila(RaDto dto) throws Exception {
		Integer pos = posicaoFila(dto.getRa());
		fila.remove(dto.getRa());
		WebSocketChannelDto mensagem = WebSocketChannelDto.builder()
				.tipo("sair")
				.topico("fila")
				.payload(pos + 1)
				.build();
		
		socketService.notifyMessageChannel(mensagem);
		
		WebSocketChannelDto mensagem2 = WebSocketChannelDto.builder()
				.tipo("quantidade")
				.topico("fila")
				.payload(fila.size())
				.build();
		
		socketService.notifyMessageChannel(mensagem2);
		
	}
	
	public PosicaoFilaDto retornarFinalFila(RaDto dto) throws Exception {
		
		if (!NumberUtils.isCreatable(dto.getRa())) 
			throw new BadRequestException("RA não deve ser um número");
		
		fila.remove(dto.getRa());

		Integer index = fila.indexOf(dto.getRa()); 

		if (index == -1) {
			fila.add(dto.getRa());
			index = fila.indexOf(dto.getRa()); 
		}
		
		PosicaoFilaDto response = PosicaoFilaDto.builder().posicao(index + 1).build();

		return response;
	}
}
