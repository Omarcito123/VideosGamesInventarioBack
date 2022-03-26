package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ComisionInv")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ComisionInv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcomision")
	private long idcomision;
	@Column(name = "tipocomision")
	private String tipocomision;
	@Column(name = "valor")
	private double valor;
	@Column(name = "unidad")
	private String unidad;	
	@Column(name = "iduseradd")
	private long iduseradd;
	@Column(name = "dateadd")
	private String dateadd;
	@Column(name = "idusermod")
	private long idusermod;
	@Column(name = "datemod")
	private String datemod;
	
	public long getIdcomision() {
		return idcomision;
	}
	public void setIdcomision(long idcomision) {
		this.idcomision = idcomision;
	}
	public String getTipocomision() {
		return tipocomision;
	}
	public void setTipocomision(String tipocomision) {
		this.tipocomision = tipocomision;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
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
