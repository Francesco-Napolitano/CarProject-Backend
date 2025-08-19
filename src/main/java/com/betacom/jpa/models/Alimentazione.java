package com.betacom.jpa.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "alimentazione")
public class Alimentazione {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_alimentazione")
	private Integer idAlimentazione;
	
	@Column(nullable = false)
	private String descrizione;

	@OneToMany(mappedBy = "alimentazione", fetch = FetchType.EAGER)
	@ToString.Exclude
	private List<Veicolo> veicolo;
	
	@ManyToMany(
			mappedBy = "alimentazione",
			fetch = FetchType.EAGER
			)
	@ToString.Exclude
	private List<TipoVeicolo> tipoVeicolo;

}
