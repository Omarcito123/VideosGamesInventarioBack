package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Sucursales")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Sucursales {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsucursal")
	private long idsucursal;
	@Column(name = "nombre")
	private String nombre;	
	@Column(name = "iduseradd")
	private int iduseradd;
	@Column(name = "dateadd")
	private String dateadd;
	@Column(name = "idusermod")
	private int idusermod;
	@Column(name = "datemod")
	private String datemod;
	
	public long getIdsucursal() {
		return idsucursal;
	}
	public void setIdsucursal(long idsucursal) {
		this.idsucursal = idsucursal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
