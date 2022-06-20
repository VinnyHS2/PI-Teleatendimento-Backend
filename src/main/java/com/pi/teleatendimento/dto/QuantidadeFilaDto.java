package com.pi.teleatendimento.dto;

import java.util.List;

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
public class QuantidadeFilaDto {
	
	@JsonProperty("quantidade")
	private Integer quantidade;
	
	@JsonProperty("ra")
	private List<String> ra;
	

}