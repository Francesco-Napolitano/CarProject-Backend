package com.betacom.jpa.services.services;

import java.util.List;

import com.betacom.jpa.dto.ColoreDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.ColoreReq;

public interface IColoreService {
	void create(ColoreReq colReq) throws AcademyException;
	void update (ColoreReq colReq) throws AcademyException;
	void delete(ColoreReq colReq) throws AcademyException;
	List<ColoreDTO> listAll() throws AcademyException;

}
