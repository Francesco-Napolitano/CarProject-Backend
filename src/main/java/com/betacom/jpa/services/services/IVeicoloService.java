package com.betacom.jpa.services.services;

import java.util.List;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.MacchinaReq;
import com.betacom.jpa.requests.MotoReq;
import com.betacom.jpa.requests.VeicoloReq;

public interface IVeicoloService {
	void create(VeicoloReq vReq) throws AcademyException;
	void update(VeicoloReq vReq) throws AcademyException;
	void delete(VeicoloReq vReq) throws AcademyException;	
	List<VeicoloDTO> listAll() throws AcademyException;
}
