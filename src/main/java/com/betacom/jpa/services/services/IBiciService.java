package com.betacom.jpa.services.services;

import java.util.List;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.BiciReq;
import com.betacom.jpa.requests.MotoReq;
import com.betacom.jpa.requests.VeicoloReq;

public interface IBiciService {
	List<VeicoloDTO> listAllBici() throws AcademyException;
	void create(BiciReq biciReq, VeicoloReq veiReq) throws AcademyException;
	void update (BiciReq biciReq, VeicoloReq veiReq) throws AcademyException;
	void delete(BiciReq biciReq, VeicoloReq vReq) throws AcademyException;
	VeicoloDTO getSingleBike(Integer idVeicolo) throws AcademyException;

}
