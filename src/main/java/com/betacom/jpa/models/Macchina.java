package com.betacom.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "macchina")
public class Macchina {
	
	@Id
	@Column (name = "id_macchina")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMacchina;
		
	@Column(name = "numero_porte" , nullable = false)
	private Integer numeroPorte;
	
	@Column(unique = true, nullable = false)
	private String targa;
	
	@Column(nullable = false)
	private Integer cilindrata;
	
	@OneToOne
	@JoinColumn (name = "id_veicolo", referencedColumnName = "id_veicolo")
	private Veicolo veicolo;

	
}