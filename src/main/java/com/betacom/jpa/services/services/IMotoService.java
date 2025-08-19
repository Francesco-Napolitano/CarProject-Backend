package com.betacom.jpa.services.services;

import java.util.List;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.MacchinaReq;
import com.betacom.jpa.requests.MotoReq;
import com.betacom.jpa.requests.VeicoloReq;

public interface IMotoService {
	void create(MotoReq mReq, VeicoloReq veiReq) throws AcademyException;
	void update (MotoReq motoReq, VeicoloReq veiReq) throws AcademyException;
	void delete(MotoReq motoReq, VeicoloReq vReq) throws AcademyException;
	List<VeicoloDTO> listAllMoto() throws AcademyException;
	VeicoloDTO getSingleMoto(Integer idVeicolo) throws AcademyException;

}
