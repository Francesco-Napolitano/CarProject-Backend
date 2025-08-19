package com.betacom.jpa.services.services;

import java.util.List;

import com.betacom.jpa.dto.AlimentazioneDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.AlimentazioneReq;

public interface IAlimentazioneService {
	void create(AlimentazioneReq aliReq) throws AcademyException;
	void update (AlimentazioneReq aliReq) throws AcademyException;
	void delete(AlimentazioneReq aliReq) throws AcademyException;
	List<AlimentazioneDTO> listAll() throws AcademyException;

}
