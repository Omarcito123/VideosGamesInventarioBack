package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ControlHorasInv")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ControlHorasInv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcontrol")
	private long idcontrol;
	@Column(name = "iduser")
	private int iduser;
	@Column(name = "datecontrol")
	private String datecontrol;
	@Column(name = "turno")
	private String turno;
	@Column(name = "iduseradd")
	private int iduseradd;
	@Column(name = "dateadd")
	private String dateadd;
	@Column(name = "idusermod")
	private int idusermod;
	@Column(name = "datemod")
	private String datemod;
	
	public long getIdcontrol() {
		return idcontrol;
	}
	public void setIdcontrol(long idcontrol) {
		this.idcontrol = idcontrol;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public String getDatecontrol() {
		return datecontrol;
	}
	public void setDatecontrol(String datecontrol) {
		this.datecontrol = datecontrol;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public int getIduseradd() {
		return iduseradd;
	}
	public void setIduseradd(int iduseradd) {
		this.iduseradd = iduseradd;
	}
	public String getDateadd() {
		return dateadd;
	}
	public void setDateadd(String dateadd) {
		this.dateadd = dateadd;
	}
	public int getIdusermod() {
		return idusermod;
	}
	public void setIdusermod(int idusermod) {
		this.idusermod = idusermod;
	}
	public String getDatemod() {
		return datemod;
	}
	public void setDatemod(String datemod) {
		this.datemod = datemod;
	}
}
