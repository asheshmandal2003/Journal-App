package com.ashesh.journalApp.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData {
	private String message;
	private Object data;

	public ResponseData(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
}
