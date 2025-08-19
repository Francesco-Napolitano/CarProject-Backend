package com.betacom.jpa.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ResponseBase {
	private Boolean rc;
	private String msg;

}
