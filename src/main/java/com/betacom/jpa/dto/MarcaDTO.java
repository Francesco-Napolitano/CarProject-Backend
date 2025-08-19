package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Builder

public class MarcaDTO {
	private Integer idMarca;
	private String descrizione;
}
