package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ComisionVendedorInv")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ComisionVendedorInv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcomisionvendedor")
	private long idcomisionvendedor;
	@Column(name = "tipocomision")
	private String tipocomision;
	@Column(name = "comision")
	private double comision;
	@Column(name = "idventa")
	private long idventa;
	@Column(name = "producto")
	private String producto;
	@Column(name = "ventatotal")
	private double ventatotal;
	@Column(name = "idusuario")
	private long idusuario;
	@Column(name = "idsucursal")
	private long idsucursal;
	@Column(name = "iduseradd")
	private long iduseradd;
	@Column(name = "dateadd")
	private String dateadd;
	@Column(name = "idusermod")
	private long idusermod;
	@Column(name = "datemod")
	private String datemod;
	
	@Transient
	private int mes;

	public long getIdcomisionvendedor() {
		return idcomisionvendedor;
	}

	public void setIdcomisionvendedor(long idcomisionvendedor) {
		this.idcomisionvendedor = idcomisionvendedor;
	}

	public String getTipocomision() {
		return tipocomision;
	}

	public void setTipocomision(String tipocomision) {
		this.tipocomision = tipocomision;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public double getVentatotal() {
		return ventatotal;
	}

	public void setVentatotal(double ventatotal) {
		this.ventatotal = ventatotal;
	}

	public long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(long idusuario) {
		this.idusuario = idusuario;
	}

	public long getIdsucursal() {
		return idsucursal;
	}

	public void setIdsucursal(long idsucursal) {
		this.idsucursal = idsucursal;
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

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public long getIdventa() {
		return idventa;
	}

	public void setIdventa(long idventa) {
		this.idventa = idventa;
	}
}
