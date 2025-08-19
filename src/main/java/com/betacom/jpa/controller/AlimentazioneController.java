package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.AlimentazioneDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.AlimentazioneReq;
import com.betacom.jpa.requests.CategoriaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.services.IAlimentazioneService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("alimentazione")
public class AlimentazioneController {
	
	private IAlimentazioneService aliS;

	public AlimentazioneController(IAlimentazioneService aliS) {
		this.aliS = aliS;
	}
	
	@GetMapping("listAll")
	public ResponseList<AlimentazioneDTO> listAll() {
		ResponseList<AlimentazioneDTO> r = new ResponseList<AlimentazioneDTO>();
		try {
			r.setDati(aliS.listAll());
			r.setRc(true);
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true)  AlimentazioneReq aliReq){
		ResponseBase r = new  ResponseBase();
		log.debug("Crea la marca: --> " + aliReq);
		try {
			aliS.create(aliReq);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true) AlimentazioneReq aliReq) {
		ResponseBase r = new ResponseBase();
		try {
			aliS.delete(aliReq);
			r.setRc(true);
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}


}
