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
public class AtendimentoDto {
	
	@JsonProperty("ra")
	private String ra;

	@JsonProperty("professor")
	private String professor;
	
	@JsonProperty("create_date")
	private LocalDateTime createDate;

	@JsonProperty("avaliacao")
	private Integer avaliacao;
	
	@JsonProperty("comentario_avaliacao")
	private String comentarioAvaliacao;

}