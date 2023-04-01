package com.bloging.application.exception;

import java.util.List;

public class ApiResponse {

	private String message;
	private List<?> responseList;
	private Object responseobject;
	private Object errorResponse;




	public ApiResponse() {
		super();
	}

	public ApiResponse(String message, List<?> responseList, Object responseobject, Object errorResponse) {
		super();
		this.message = message;
		this.responseList = responseList;
		this.responseobject = responseobject;
		this.errorResponse = errorResponse;
	}

	public Object getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(Object errorResponse) {
		this.errorResponse = errorResponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<?> responseList) {
		this.responseList = responseList;
	}

	public Object getResponseobject() {
		return responseobject;
	}

	public void setResponseobject(Object responseobject) {
		this.responseobject = responseobject;
	}

}
