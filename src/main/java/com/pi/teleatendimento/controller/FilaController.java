package com.pi.teleatendimento.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.teleatendimento.dto.InserirFilaDto;
import com.pi.teleatendimento.dto.PosicaoFilaDto;
import com.pi.teleatendimento.service.FilaService;


@Validated
@RestController
@RequestMapping(path = { "/fila" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class FilaController {

	@Autowired
	FilaService filaService;
	
	@GetMapping("/chamar-proximo")
	public ResponseEntity<?> chamarProximo() throws Exception {
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<PosicaoFilaDto> inserirFila(@Valid @RequestBody InserirFilaDto dto) throws Exception {
		return ResponseEntity.ok(filaService.inserirFila(dto));
	}
	
}
