package com.betacom.jpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.controller.VeicoloController;
import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.models.Veicolo;
import com.betacom.jpa.response.ResponseList;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VeicoloControllerTest {
	
	@Autowired
	private VeicoloController veiC;
	
	@Test
	public void listVeicoliTest() {
		log.debug("Test listVeicoli");
		ResponseList<VeicoloDTO> v = veiC.listAll();
		v.getDati().forEach(vei -> log.debug(vei.toString()));
		Assertions.assertThat(v.getRc()).isEqualTo(true);
		Assertions.assertThat(v.getDati().size()).isEqualTo(0);

	}


}
