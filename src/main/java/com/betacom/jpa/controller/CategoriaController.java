package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.CategoriaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.CategoriaReq;
import com.betacom.jpa.requests.MarcaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.services.ICategoriaService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("categoria")
public class CategoriaController {
	
	private ICategoriaService catS;

	public CategoriaController(ICategoriaService catS) {
		this.catS = catS;
	}
	
	@GetMapping("listAll")
	public ResponseList<CategoriaDTO> listAll() {
		ResponseList<CategoriaDTO> r = new ResponseList<CategoriaDTO>();
		try {
			r.setDati(catS.listAll());
			r.setRc(true);
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true)  CategoriaReq catReq){
		ResponseBase r = new  ResponseBase();
		log.debug("Crea la marca: --> " + catReq);
		try {
			catS.create(catReq);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true) CategoriaReq catReq) {
		ResponseBase r = new ResponseBase();
		try {
			catS.delete(catReq);
			r.setRc(true);
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

}
