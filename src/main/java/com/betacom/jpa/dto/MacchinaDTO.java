package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MacchinaDTO {

	private Integer idMacchina;
	private Integer numeroPorte;
	private String targa;
	private Integer cilindrata;

}
