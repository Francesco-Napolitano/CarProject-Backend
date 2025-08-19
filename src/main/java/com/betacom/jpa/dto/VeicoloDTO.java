package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

public class VeicoloDTO {
	private Integer idVeicolo;
	private String tipoVeicolo; // macchina, moto, bici
	private Integer numeroRuote;
	private String categoria;
	private String alimentazione;
	private String colore;
	private String marca;
	private Integer annoProduzione;	
	private String modello;	
	private MacchinaDTO macchina;
	private MotoDTO moto;
	private BiciDTO bici;
}
