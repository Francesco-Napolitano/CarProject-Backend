package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.ColoreDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.ColoreReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.services.IColoreService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("colore")
public class ColoreController {
	
	private IColoreService colS;

	public ColoreController(IColoreService colS) {
		this.colS = colS;
	}
	
	@GetMapping("listAll")
	public ResponseList<ColoreDTO> listAll() {
		ResponseList<ColoreDTO> r = new ResponseList<ColoreDTO>();
		try {
			r.setDati(colS.listAll());
			r.setRc(true);
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true)  ColoreReq colReq){
		ResponseBase r = new  ResponseBase();
		log.debug("Crea il colore : --> " + colReq);
		try {
			colS.create(colReq);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true) ColoreReq colReq) {
		ResponseBase r = new ResponseBase();
		try {
			colS.delete(colReq);
			r.setRc(true);
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}


}
