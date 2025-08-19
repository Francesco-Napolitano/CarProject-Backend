package com.betacom.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bici")
public class Bici {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bici")
	private Integer idBici;

	@Column(nullable = false, name = "numero_marce")
	private Integer numeroMarce;

	@Column(nullable = false)
	private Boolean pieghevole;
	
	@OneToOne
	@JoinColumn(name = "id_veicolo", referencedColumnName = "id_veicolo")
	private Veicolo veicolo;

	@ManyToOne
	@JoinColumn (name = "tipo_sospensione")
	private Sospensione sospensione;
}