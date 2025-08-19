package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Builder

public class CategoriaDTO {
	private Integer idCategoria;
	private String descrizione;
}
