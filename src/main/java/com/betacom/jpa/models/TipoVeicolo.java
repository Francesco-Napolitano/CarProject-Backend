package com.betacom.jpa.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table (name = "tipo_veicolo")
public class TipoVeicolo {
	
	@Id
	@Column (name = "id_tipo_veicolo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoVeicolo;
	
	@Column (nullable = false, unique = true)
	private String pattern;
	
	@Column (nullable = false)
	private String descrizione;

	@OneToMany(mappedBy = "tipoVeicolo", fetch = FetchType.EAGER)
	private List<Veicolo> veicolo;

	@ManyToMany
	@JoinTable(
			name="alimentazione_tipo_veicolo",
			joinColumns = @JoinColumn(name = "id_tipo_veicolo"),
			inverseJoinColumns =  @JoinColumn (name = "id_alimentazione")
			)
	@ToString.Exclude
	private List<Alimentazione> alimentazione;
	
	@ManyToMany
	@JoinTable(
			name="categoria_tipo_veicolo",
			joinColumns = @JoinColumn(name = "id_tipo_veicolo"),
			inverseJoinColumns =  @JoinColumn (name = "id_categoria")
			)
	@ToString.Exclude
	private List<Categoria> categoria;


}
