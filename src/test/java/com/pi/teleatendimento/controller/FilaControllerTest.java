package com.pi.teleatendimento.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi.teleatendimento.dto.InserirFilaDto;

@SpringBootTest
@AutoConfigureMockMvc
public class FilaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void inserirFila() throws Exception {
		
		InserirFilaDto dto = InserirFilaDto.builder().ra("2046458").build();
		
		ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);
		
        this.mockMvc.perform(
				MockMvcRequestBuilders.post("/fila/inserir")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.posicao").value("1"));

	}
	
	@Test
	void inserirRaIgualDuasVezes() throws Exception {
		
		InserirFilaDto dto = InserirFilaDto.builder().ra("2046458").build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(dto);
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/fila/inserir")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.characterEncoding("utf-8"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.posicao").value("1"));
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/fila/inserir")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.characterEncoding("utf-8"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.posicao").value("1"));
		
	}

	@Test
	void inserirRasDiferentes() throws Exception {
		
		InserirFilaDto dto = InserirFilaDto.builder().ra("2046458").build();
		InserirFilaDto dto2 = InserirFilaDto.builder().ra("2046326").build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(dto);
		String json2 = objectMapper.writeValueAsString(dto2);
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/fila/inserir")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.characterEncoding("utf-8"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.posicao").value("1"));
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/fila/inserir")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json2)
				.characterEncoding("utf-8"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.posicao").value("2"));
		
	}

	@Test
	void inserirRaInvalido() throws Exception {
		
		InserirFilaDto dto = InserirFilaDto.builder().ra("a2046458").build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(dto);
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/fila/inserir")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.characterEncoding("utf-8"))
		.andExpect(status().isBadRequest());
		
	}

	@Test
	void inserirVazio() throws Exception {
		
		InserirFilaDto dto = InserirFilaDto.builder().ra("").build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(dto);
		
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/fila/inserir")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.characterEncoding("utf-8"))
		.andExpect(status().isBadRequest());
		
	}
	
}
