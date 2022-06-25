package com.pi.teleatendimento.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atendimento")
public class Atendimento {

	@Id
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
	@GeneratedValue(generator = "UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@NotEmpty
	@Column(name = "ra", length = 255)
	private String ra;
	
	@NotEmpty
	@Column(name = "professor", length = 255)
	private String professor;
	
	@Column(name = "avaliacao")
	private Integer avaliacao;

    @JsonIgnore
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @ToString.Exclude
    private LocalDateTime createdDate;
}
