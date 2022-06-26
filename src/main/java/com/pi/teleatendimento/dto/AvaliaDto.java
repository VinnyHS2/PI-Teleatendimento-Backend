package com.pi.teleatendimento.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class AvaliaDto {
	
	@NotEmpty(message = "O campo RA não deve estar vazio")
	@JsonProperty("ra")
	private String ra;

	@NotNull
	@JsonProperty("avaliacao")
	private Integer avaliacao;
	
	@NotEmpty
	@JsonProperty("comentario")
	private String comentario;

}