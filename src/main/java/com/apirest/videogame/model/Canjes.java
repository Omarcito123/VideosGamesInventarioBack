package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Canjes")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Canjes {
	@Id
	@Column(name = "idcanje")
	private long idcanje;
	@Column(name = "iduser")
	private long iduser;
	@Column(name = "idproduct")
	private long idproduct;
	@Column(name = "nameproduct")
	private String nameproduct;	
	@Column(name = "pointsprodct")
	private int pointsprodct;
	@Column(name = "pointuser")
	private int pointuser;
	@Column(name = "totalpointuser")
	private int totalpointuser;
	@Column(name = "iduseradd")
	private int iduseradd;
	@Column(name = "dateadd")
	private String dateadd;
	@Column(name = "idusermod")
	private int idusermod;
	@Column(name = "datemod")
	private String datemod;
	@Column(name = "idpoint")
	private long idpoint;
	
	public long getIdcanje() {
		return idcanje;
	}
	public void setIdcanje(long idcanje) {
		this.idcanje = idcanje;
	}
	public long getIduser() {
		return iduser;
	}
	public void setIduser(long iduser) {
		this.iduser = iduser;
	}
	public long getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(long idproduct) {
		this.idproduct = idproduct;
	}
	public String getNameproduct() {
		return nameproduct;
	}
	public void setNameproduct(String nameproduct) {
		this.nameproduct = nameproduct;
	}
	public int getPointsprodct() {
		return pointsprodct;
	}
	public void setPointsprodct(int pointsprodct) {
		this.pointsprodct = pointsprodct;
	}
	public int getPointuser() {
		return pointuser;
	}
	public void setPointuser(int pointuser) {
		this.pointuser = pointuser;
	}
	public int getTotalpointuser() {
		return totalpointuser;
	}
	public void setTotalpointuser(int totalpointuser) {
		this.totalpointuser = totalpointuser;
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
	public long getIdpoint() {
		return idpoint;
	}
	public void setIdpoint(long idpoint) {
		this.idpoint = idpoint;
	}
}
