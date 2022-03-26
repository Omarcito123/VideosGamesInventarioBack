package com.apirest.videogame.model;

public class ResponseObject {
	private String state;
	private String message;
    private boolean success;
    private Object data;
    
    public ResponseObject() {}
    
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
