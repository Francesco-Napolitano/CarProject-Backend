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
@Table (name = "marca")
public class Marca {
	
	@Id
	@Column (name = "id_marca")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMarca;
	
	@Column (nullable = false)
	private String descrizione;
	
	@OneToMany(mappedBy = "marca", fetch = FetchType.EAGER)
	private List<Veicolo> veicolo;
	
}
