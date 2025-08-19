package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Marca;
import com.betacom.jpa.models.Moto;

public interface IMarcaRepository extends JpaRepository<Marca, Integer>{
	Optional<Marca> findByDescrizione (String descrizione);
	
}
