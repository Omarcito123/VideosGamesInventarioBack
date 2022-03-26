package com.apirest.videogame.model;

public class paramsGeneric {
	private long id;
    private String passActual;
    private String newPass;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassActual() {
		return passActual;
	}
	public void setPassActual(String passActual) {
		this.passActual = passActual;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
}
