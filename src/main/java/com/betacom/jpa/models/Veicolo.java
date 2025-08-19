package com.betacom.jpa.models;

import jakarta.persistence.CascadeType;
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
@Table(name = "veicolo")
public class Veicolo {
	
	@Id
	@Column (name = "id_veicolo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVeicolo;
	
	@ManyToOne
	@JoinColumn (name = "id_tipo_veicolo"
			,referencedColumnName = "id_tipo_veicolo" // referencedColumnName è OPZIONALE
)
	private TipoVeicolo tipoVeicolo; // macchina, moto, bici
	
	@ManyToOne
	@JoinColumn (name = "id_alimentazione")
	private Alimentazione alimentazione;
	
	@ManyToOne
	@JoinColumn (name = "id_categoria")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn (name = "id_colore")
	private Colore colore;
	
	@ManyToOne
	@JoinColumn (name = "id_marca")
	private Marca marca;
	
	@Column (nullable = false, name = "numero_ruote" )
	private Integer numeroRuote;
	
	@Column (nullable = false, name = "anno_prod")
	private Integer annoProduzione;
	
	@Column (length = 100,nullable = false)
	private String modello;
	
	@OneToOne(mappedBy = "veicolo", cascade = CascadeType.ALL)
	private Macchina macchina;
	
	@OneToOne(mappedBy = "veicolo", cascade = CascadeType.ALL)
	private Moto moto;
	
	@OneToOne(mappedBy = "veicolo", cascade = CascadeType.ALL)
	private Bici bici;



	
}