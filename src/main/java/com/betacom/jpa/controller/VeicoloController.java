package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.services.IVeicoloService;


@RestController
@RequestMapping("/rest/veicolo")
public class VeicoloController {
	private IVeicoloService service;

	public VeicoloController(IVeicoloService service) {
		this.service = service;
	}

	@GetMapping("listAll")
	public ResponseList<VeicoloDTO> listAll() {
		ResponseList<VeicoloDTO> r = new ResponseList<VeicoloDTO>();
		try {
			r.setDati(service.listAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

}
