package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MotoDTO {

	private Integer idMoto;
	private String targa;
	private Integer cilindrata;

}
