package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.jpa.models.Bici;
import com.betacom.jpa.models.Macchina;

public interface IBiciRepository extends JpaRepository<Bici, Integer> {
	@Query(name = "bici.id")
	Optional<Bici> findByIdVeicolo(@Param("idVeicolo") Integer idVeicolo);

}
