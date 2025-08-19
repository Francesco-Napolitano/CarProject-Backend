package com.betacom.jpa.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

public class TipoVeicoloDTO {

	private Integer idTipoVeicolo;
	private String descrizione;
	
	private List<AlimentazioneDTO> alimentazione;
	private List<CategoriaDTO> categoria;	
}
