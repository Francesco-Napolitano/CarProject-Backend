package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.jpa.models.Macchina;

public interface IMacchinaRepository extends JpaRepository<Macchina, Integer> {
	Optional<Macchina> findByTarga (String targa);

	@Query(name = "macchina.id")
	Optional<Macchina> findByIdVeicolo(@Param("idVeicolo") Integer idVeicolo);
}