package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Colore;

public interface IColoreRepository extends JpaRepository<Colore, Integer>{
	Optional<Colore> findByDescrizione(String colore);
}
