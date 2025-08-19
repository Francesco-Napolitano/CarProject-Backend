package com.betacom.jpa.services.services;

import java.util.List;

import com.betacom.jpa.dto.TipoVeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.AlimentazioneReq;
import com.betacom.jpa.requests.CategoriaReq;
import com.betacom.jpa.requests.TipoVeicoloReq;

public interface ITipoVeicoloService {
	void createAlimentazioneTipoVeicolo(AlimentazioneReq aliReq) throws AcademyException;
	void create(TipoVeicoloReq tipoReq) throws AcademyException;
	void collegaCategoriaTipoVeicolo(CategoriaReq cateReq) throws AcademyException;
	
	List<TipoVeicoloDTO> listall() ;
}