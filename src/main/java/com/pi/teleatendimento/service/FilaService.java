package com.pi.teleatendimento.service;

import java.util.ArrayList;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.pi.teleatendimento.dto.InserirFilaDto;
import com.pi.teleatendimento.dto.PosicaoFilaDto;
import com.pi.teleatendimento.exceptions.BadRequestException;

import lombok.NonNull;

@Service
public class FilaService {
	
	
//	@Autowired
//	UtilitiesService utilitiesService;
	
	private ArrayList<String> fila = new ArrayList<String>();
	
	public PosicaoFilaDto inserirFila(@NonNull InserirFilaDto dto) throws Exception {
		
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
