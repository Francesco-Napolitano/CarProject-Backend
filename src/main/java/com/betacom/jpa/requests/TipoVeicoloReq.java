package com.betacom.jpa.requests;

import lombok.Data;

@Data
public class TipoVeicoloReq {
	private Integer idTipoVeicolo;
	private String descrizione;
	private String pattern;
	private String tipoAlimentazione;

}
