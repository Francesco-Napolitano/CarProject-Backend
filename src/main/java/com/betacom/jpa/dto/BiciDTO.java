package com.betacom.jpa.dto;

import com.betacom.jpa.models.Sospensione;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BiciDTO {

	private Integer idBici;
	private Integer numeroMarce;
	private Boolean pieghevole;
	private String sospensione;

}
