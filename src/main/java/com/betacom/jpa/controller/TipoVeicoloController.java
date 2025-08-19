package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.TipoVeicoloDTO;
import com.betacom.jpa.requests.AlimentazioneReq;
import com.betacom.jpa.requests.CategoriaReq;
import com.betacom.jpa.requests.TipoVeicoloReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.services.ITipoVeicoloService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/tipoVeicolo")

public class TipoVeicoloController {
	private ITipoVeicoloService tipoS;	
	
	public TipoVeicoloController(ITipoVeicoloService tipoS) {
		this.tipoS = tipoS;
	}

	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true)  TipoVeicoloReq tipoVReq){
		ResponseBase r = new  ResponseBase();
		log.debug("Crea il tipo Veicolo req: --> " + tipoVReq);
		try {
			tipoS.create(tipoVReq);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("collegamento/alimentazione")
	public ResponseBase create(@RequestBody (required = true)  AlimentazioneReq aliReq){
		ResponseBase r = new  ResponseBase();
		log.debug("Crea il collegamento alimentazione - Veicolo: --> " + aliReq);
		try {
			tipoS.createAlimentazioneTipoVeicolo(aliReq);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("collegamento/categoria")
	public ResponseBase create(@RequestBody (required = true)  CategoriaReq catReq){
		ResponseBase r = new  ResponseBase();
		log.debug("Crea il collegamento Categoria - Veicolo: --> " + catReq);
		try {
			tipoS.collegaCategoriaTipoVeicolo(catReq);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@GetMapping("listAll")
	public ResponseList<TipoVeicoloDTO> listAll(){
		ResponseList<TipoVeicoloDTO> r = new  ResponseList<TipoVeicoloDTO>();
		try {
			r.setDati(tipoS.listall());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	

	
}