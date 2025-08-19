package com.betacom.jpa.requests;

import lombok.Data;

@Data
public class MacchinaVeicoloReq {
	VeicoloReq datiVeicolo;
	MacchinaReq datiMacchina;
	MotoReq datiMoto;
	BiciReq datiBici;

}
