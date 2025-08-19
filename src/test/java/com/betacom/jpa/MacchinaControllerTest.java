package com.betacom.jpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.controller.MacchinaController;
import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.requests.MacchinaReq;
import com.betacom.jpa.requests.MacchinaVeicoloReq;
import com.betacom.jpa.requests.VeicoloReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MacchinaControllerTest {

	@Autowired
	private MacchinaController carC;
	
	
	

	@Test
	@Order(1)
	public void createMacchinaTest() {
		log.debug("Test create attività ");
		MacchinaVeicoloReq autoVeiC = new MacchinaVeicoloReq();

		VeicoloReq reqV = new VeicoloReq();
		reqV.setTipoVeicolo("macchina");
		reqV.setAlimentazione("Benzina");
		reqV.setCategoria("Sportiva");
		reqV.setColore("Rosso");
		reqV.setMarca("Ferrari");
		reqV.setNumeroRuote(4);
		reqV.setAnnoProduzione(2014);
		reqV.setModello("308GTB");
		
		autoVeiC.setDatiVeicolo(reqV);

		MacchinaReq req = new MacchinaReq();
		req.setNumeroPorte(4);
		req.setTarga("CK789EW");
		req.setIdVeicolo(2);
		req.setCilindrata(3000);

		autoVeiC.setDatiMacchina(req);

		ResponseBase r = carC.create(autoVeiC);
		log.debug("Mostrami cosa sto inviando -> " + autoVeiC.toString());
		log.debug(r.getMsg());
		Assertions.assertThat(r.getRc()).isEqualTo(true);
	}

//	@Test
//	@Order(2)
//	public void listAttivitaTest() {
//		log.debug("Test list Attivita");
//		ResponseList<VeicoloDTO> r = carC.listAllcar();
//		r.getDati().forEach(s -> log.debug(s.toString()));
//		Assertions.assertThat(r.getRc()).isEqualTo(true);
//		Assertions.assertThat(r.getDati().size()).isEqualTo(1);
//
//	}

}
