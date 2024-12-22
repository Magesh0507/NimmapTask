package com.assignment.Nimmap.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class BaseResponse {

	
	private String status;
	
	private List<? extends Object> respBody;
	
	private List<Map<String, Object>> errors;
	
	private String message;

	
	public BaseResponse() {
	}

	public BaseResponse(String status, List<? extends Object> respBody, List<Map<String, Object>> errors,
			String message) {
		this.status = status;
		this.respBody = respBody;
		this.errors = errors;
		this.message = message;
	}

}
