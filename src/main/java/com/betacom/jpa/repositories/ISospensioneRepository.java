package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Sospensione;

public interface ISospensioneRepository extends JpaRepository<Sospensione, Integer>{
	Optional<Sospensione> findByDescrizione(String descrizione);

}
