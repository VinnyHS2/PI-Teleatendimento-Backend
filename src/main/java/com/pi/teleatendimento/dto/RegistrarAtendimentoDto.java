package com.pi.teleatendimento.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarAtendimentoDto {
	
	@NotEmpty(message = "O campo RA não deve estar vazio")
	@JsonProperty("ra")
	private String ra;

	@NotEmpty(message = "O nome do professor não deve estar vazio")
	@JsonProperty("professor")
	private String professor;

}