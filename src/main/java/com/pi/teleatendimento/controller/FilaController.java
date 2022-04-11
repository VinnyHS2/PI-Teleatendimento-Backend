package com.pi.teleatendimento.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping(path = { "/fila" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class FilaController {
	
	@GetMapping("/chamar-proximo")
	public ResponseEntity<?> chamarProximo() throws Exception {
		return ResponseEntity.ok().build();
	}
	
}