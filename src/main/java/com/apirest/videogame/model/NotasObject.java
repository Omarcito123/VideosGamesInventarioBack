package com.apirest.videogame.model;

public class NotasObject {
	private long idnota;
	private String nota;
	private String usuario;
	private long iduseradd;
	private String dateadd;
	private long idusermod;
	private String datemod;
	
	public long getIdnota() {
		return idnota;
	}
	public void setIdnota(long idnota) {
		this.idnota = idnota;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public long getIduseradd() {
		return iduseradd;
	}
	public void setIduseradd(long iduseradd) {
		this.iduseradd = iduseradd;
	}
	public String getDateadd() {
		return dateadd;
	}
	public void setDateadd(String dateadd) {
		this.dateadd = dateadd;
	}
	public long getIdusermod() {
		return idusermod;
	}
	public void setIdusermod(long idusermod) {
		this.idusermod = idusermod;
	}
	public String getDatemod() {
		return datemod;
	}
	public void setDatemod(String datemod) {
		this.datemod = datemod;
	}
}
