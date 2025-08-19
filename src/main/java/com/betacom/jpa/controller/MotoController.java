package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.MacchinaVeicoloReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.response.ResponseObject;
import com.betacom.jpa.services.services.IMotoService;
import com.betacom.jpa.services.services.IVeicoloService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/rest/moto")

public class MotoController {
	private IMotoService motoS;
	private IVeicoloService veiS;	
	
	public MotoController(IMotoService motoS, IVeicoloService veiS) {
		this.motoS = motoS;
		this.veiS = veiS;
	}

	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true)  MacchinaVeicoloReq datiVeicolo){
		ResponseBase r = new  ResponseBase();
		log.debug("Cos'è MOTOVEICOLOREQ" + datiVeicolo);
		try {
			motoS.create(datiVeicolo.getDatiMoto(), datiVeicolo.getDatiVeicolo());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true) MacchinaVeicoloReq datiVeicolo) {
		ResponseBase r = new ResponseBase();
		try {
			motoS.update(datiVeicolo.getDatiMoto() , datiVeicolo.getDatiVeicolo());
			r.setRc(true);
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true) MacchinaVeicoloReq datiVeicolo) {
		ResponseBase r = new ResponseBase();
		try {
			motoS.delete(datiVeicolo.getDatiMoto() , datiVeicolo.getDatiVeicolo());
			r.setRc(true);
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("listAll")
	public ResponseList<VeicoloDTO> listAll(){
		ResponseList<VeicoloDTO> r = new  ResponseList<VeicoloDTO>();
		try {
			r.setDati(motoS.listAllMoto());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("getSingleMoto")
	public ResponseObject<VeicoloDTO> getSingleMoto(@RequestParam (required = true) Integer idVeicolo) {
		ResponseObject<VeicoloDTO> r = new ResponseObject<VeicoloDTO>();
		try {
			r.setDati(motoS.getSingleMoto(idVeicolo));
			r.setRc(true);
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	
}