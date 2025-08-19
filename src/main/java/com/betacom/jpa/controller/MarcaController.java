package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.MarcaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.MarcaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.services.IMarcaService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("marca")
public class MarcaController {
	
	private IMarcaService marS;

	public MarcaController(IMarcaService marS) {
		this.marS = marS;
	}
	
	@GetMapping("listAll")
	public ResponseList<MarcaDTO> listAll() {
		ResponseList<MarcaDTO> r = new ResponseList<MarcaDTO>();
		try {
			r.setDati(marS.listAll());
			r.setRc(true);
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true)  MarcaReq marReq){
		ResponseBase r = new  ResponseBase();
		log.debug("Crea la marca: --> " + marReq);
		try {
			marS.create(marReq);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true) MarcaReq marReq) {
		ResponseBase r = new ResponseBase();
		try {
			marS.delete(marReq);
			r.setRc(true);
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}


}
