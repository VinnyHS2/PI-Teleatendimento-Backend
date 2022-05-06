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
import com.pi.teleatendimento.service.FilaService;


@Validated
@RestController
@RequestMapping(path = { "/fila" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class FilaController {

	@Autowired
	FilaService filaService;
	
	@PostMapping("/chamar-proximo")
	public ResponseEntity<?> chamarProximo(@Valid @RequestBody ChamarProximoDto dto) throws Exception {
		filaService.ChamarProximo(dto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<PosicaoFilaDto> inserirFila(@Valid @RequestBody RaDto dto) throws Exception {
		return ResponseEntity.ok(filaService.inserirFila(dto));
	}
	
	@GetMapping("/limpar")
	public ResponseEntity<PosicaoFilaDto> limparFila() throws Exception {
		filaService.limparFila();
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/quantidade")
	public ResponseEntity<QuantidadeFilaDto> quantidadeFila() throws Exception {
		return ResponseEntity.ok(filaService.quantidadeFila());
	}

	@PostMapping("/sair")
	public ResponseEntity<QuantidadeFilaDto> sairFila(@Valid @RequestBody RaDto dto) throws Exception {
		filaService.sairFila(dto);
		return ResponseEntity.ok().build();
	}
	
}
