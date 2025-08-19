package com.betacom.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.jpa.models.Veicolo;

public interface IVeicoloRepository extends JpaRepository<Veicolo, Integer> {
	
	@Query(name = "veicolo.macchina")
	List<Veicolo> findAllByDescrizione(@Param("id_tipo") String id_tipo);

}
