package com.betacom.jpa.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class MacchinaReq {

	private Integer numeroPorte;
	private String targa;
	private Integer cilindrata;
	private Integer idVeicolo;
}
