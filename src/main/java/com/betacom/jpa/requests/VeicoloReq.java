package com.betacom.jpa.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class VeicoloReq {
	private Integer idVeicolo;
	private String tipoVeicolo; // macchina, moto, bici
	private String alimentazione;
	private String categoria;
	private String colore;
	private String marca;
	private Integer numeroRuote;
	private Integer annoProduzione;	
	private String modello;	
	}
