package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.TipoVeicolo;

public interface ITipoVeicoloRepository extends JpaRepository<TipoVeicolo, Integer>{
	Optional<TipoVeicolo> findByDescrizione(String descrizione);

}
