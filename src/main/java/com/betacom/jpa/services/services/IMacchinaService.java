package com.betacom.jpa.services.services;

import java.util.List;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.MacchinaReq;
import com.betacom.jpa.requests.VeicoloReq;

public interface IMacchinaService {
	void create(MacchinaReq CarReq,VeicoloReq veiReq) throws AcademyException;
	void update (MacchinaReq carReq, VeicoloReq veiReq) throws AcademyException;
	void delete(MacchinaReq CarReq, VeicoloReq vReq) throws AcademyException;
	List<VeicoloDTO> listAllCar() throws AcademyException;
	VeicoloDTO getSingleCar(Integer idVeicolo) throws AcademyException;
}
