package com.betacom.jpa.services.services;

import java.util.List;

import com.betacom.jpa.dto.CategoriaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.CategoriaReq;

public interface ICategoriaService {
	void create(CategoriaReq catReq) throws AcademyException;
	void update (CategoriaReq catReq) throws AcademyException;
	void delete(CategoriaReq catReq) throws AcademyException;
	List<CategoriaDTO> listAll() throws AcademyException;
}
