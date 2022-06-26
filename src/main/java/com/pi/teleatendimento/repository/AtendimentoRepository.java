package com.pi.teleatendimento.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pi.teleatendimento.model.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, UUID> {
	
	Optional<Atendimento> findById(UUID id);
	
	@Query(nativeQuery = true, 
			value = "   SELECT * "
					+ " FROM atendimento at "
					+ " WHERE at.ra = :ra ")
	List<Atendimento> findByRa(String ra);

	@Query(nativeQuery = true, 
			value = "   SELECT * "
					+ " FROM atendimento at "
					+ " WHERE at.ra = :ra "
					+ " ORDER BY at.created_date DESC "
					+ " LIMIT 1 ")
	Optional<Atendimento> findLastByRa(String ra);

}
