package com.betacom.jpa.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sospensione")
public class Sospensione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sospensione")
	private Integer idSospensione;
	
	@Column(nullable = false)
	private String descrizione;
	
	@OneToMany(mappedBy = "sospensione", fetch = FetchType.EAGER)
	private List<Bici> bici;

}
