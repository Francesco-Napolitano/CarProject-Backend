package com.betacom.jpa.requests;

import lombok.Data;

@Data
public class BiciReq {

	private Integer idBici;
	private Integer numeroMarce;
	private Boolean pieghevole;
	private String sospensione;
	private Integer idVeicolo;

}
