package com.example.DemoSpringBootAPI.Controller.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage {
	private String message;

	public ResponseMessage(String message) {
		this.message = message;
	}
}
