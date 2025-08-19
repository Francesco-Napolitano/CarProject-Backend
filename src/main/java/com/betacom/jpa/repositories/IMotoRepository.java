package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.jpa.models.Bici;
import com.betacom.jpa.models.Moto;

public interface IMotoRepository extends JpaRepository<Moto, Integer> {
	Optional<Moto> findByTarga (String targa);

	@Query(name = "moto.id")
	Optional<Moto> findByIdVeicolo(@Param("idVeicolo") Integer idVeicolo);

}
