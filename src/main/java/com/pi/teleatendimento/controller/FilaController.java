package com.pi.teleatendimento.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.teleatendimento.dto.InserirFilaDto;
import com.pi.teleatendimento.dto.WebSocketChannelDto;


@Validated
@RestController
@RequestMapping(path = { "/fila" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class FilaController {
	
	@PostMapping("/inserir")
	public ResponseEntity<?> inserirFila(@Valid @RequestBody InserirFilaDto dto) throws Exception {
		return ResponseEntity.ok().build();
	}
	
}
