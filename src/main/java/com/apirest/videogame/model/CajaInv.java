package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CajaInv")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CajaInv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcaja")
	private long idcaja;
	@Column(name = "idsucursal")
	private long idsucursal;
	@Column(name = "iniciocaja")
	private double iniciocaja;	
	@Column(name = "monedas")
	private double monedas;	
	@Column(name = "faltante")
	private double faltante;
	@Column(name = "sobrante")
	private double sobrante;	
	@Column(name = "iduseradd")
	private long iduseradd;
	@Column(name = "dateadd")
	private String dateadd;
	@Column(name = "idusermod")
	private long idusermod;
	@Column(name = "datemod")
	private String datemod;
	
	public long getIdcaja() {
		return idcaja;
	}
	public void setIdcaja(long idcaja) {
		this.idcaja = idcaja;
	}
	public long getIdsucursal() {
		return idsucursal;
	}
	public void setIdsucursal(long idsucursal) {
		this.idsucursal = idsucursal;
	}
	public double getIniciocaja() {
		return iniciocaja;
	}
	public void setIniciocaja(double iniciocaja) {
		this.iniciocaja = iniciocaja;
	}	
	public double getMonedas() {
		return monedas;
	}
	public void setMonedas(double monedas) {
		this.monedas = monedas;
	}
	public double getFaltante() {
		return faltante;
	}
	public void setFaltante(double faltante) {
		this.faltante = faltante;
	}
	public double getSobrante() {
		return sobrante;
	}
	public void setSobrante(double sobrante) {
		this.sobrante = sobrante;
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
