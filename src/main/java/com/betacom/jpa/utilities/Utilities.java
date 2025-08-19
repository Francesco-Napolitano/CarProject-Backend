package com.betacom.jpa.utilities;

import com.betacom.jpa.dto.BiciDTO;
import com.betacom.jpa.dto.MacchinaDTO;
import com.betacom.jpa.dto.MotoDTO;
import com.betacom.jpa.dto.SospensioneDTO;
import com.betacom.jpa.models.Bici;
import com.betacom.jpa.models.Macchina;
import com.betacom.jpa.models.Moto;
import com.betacom.jpa.models.Sospensione;

public class Utilities {
    
	public MacchinaDTO buildMacchinaDTO(Macchina car) {

		return MacchinaDTO.builder().idMacchina(car.getIdMacchina()).numeroPorte(car.getNumeroPorte())
				.targa(car.getTarga()).cilindrata(car.getCilindrata()).build();
	}

	
	public MotoDTO buildMotoDTO(Moto bike) {

		return MotoDTO.builder().idMoto(bike.getIdMoto())
				.targa(bike.getTarga()).cilindrata(bike.getCilindrata()).build();
		
	}
	
	public BiciDTO buildBiciDTO(Bici vélo) {

		return BiciDTO.builder().idBici(vélo.getIdBici())
				.pieghevole(vélo.getPieghevole()).numeroMarce(vélo.getNumeroMarce())
				.sospensione(vélo.getSospensione().getDescrizione()).build();
	}




}