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

import com.pi.teleatendimento.dto.ChamarProximoDto;
import com.pi.teleatendimento.dto.PosicaoFilaDto;
import com.pi.teleatendimento.dto.QuantidadeFilaDto;
import com.pi.teleatendimento.dto.RaDto;
import com.pi.teleatendimento.service.AtendimentoService;
import com.pi.teleatendimento.service.FilaService;


@Validated
@RestController
@RequestMapping(path = { "/atendimento" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class AtendimentoController {

	@Autowired
	AtendimentoService atendimentoService;
	
	@PostMapping("/sair-aluno")
	public ResponseEntity<QuantidadeFilaDto> sairAluno(@Valid @RequestBody RaDto dto) throws Exception {
		atendimentoService.finalizarAluno(dto);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/sair-professor")
	public ResponseEntity<QuantidadeFilaDto> sairProf(@Valid @RequestBody RaDto dto) throws Exception {
		atendimentoService.finalizarProfessor(dto);
		return ResponseEntity.ok().build();
	}
	
}
