package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Alimentazione;
import com.betacom.jpa.models.Categoria;

public interface IAlimentazioneRepository  extends JpaRepository<Alimentazione, Integer>{
	
	Optional<Alimentazione> findByDescrizione(String descrizione);

}
