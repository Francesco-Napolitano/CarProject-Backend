package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

public class AlimentazioneDTO {
	private Integer idAlimentazione;
	private String descrizione;
}
