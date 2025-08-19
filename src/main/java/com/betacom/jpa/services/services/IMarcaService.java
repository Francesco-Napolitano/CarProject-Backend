package com.betacom.jpa.services.services;

import java.util.List;

import com.betacom.jpa.dto.MarcaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.MarcaReq;

public interface IMarcaService {
	void create(MarcaReq marReq) throws AcademyException;
	void update (MarcaReq colReq) throws AcademyException;
	void delete(MarcaReq colReq) throws AcademyException;
	List<MarcaDTO> listAll() throws AcademyException;

}
