package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "HistorialCajaInv")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class HistorialCajaInv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idhistorialcaja")
	private long idhistorialcaja;
	@Column(name = "iniciocaja")
	private double iniciocaja;	
	@Column(name = "monedas")
	private double monedas;	
	@Column(name = "compras")
	private double compras;
	@Column(name = "faltante")
	private double faltante;
	@Column(name = "sobrante")
	private double sobrante;
	@Column(name = "totaltarjetas")
	private double totaltarjetas;
	@Column(name = "ventatotal")
	private double ventatotal;
	@Column(name = "ventasucursal")
	private double ventasucursal;
	@Column(name = "agricola")
	private double agricola;
	@Column(name = "credomatic")
	private double credomatic;
	@Column(name = "davivienda")
	private double davivienda;
	@Column(name = "efectivo")
	private double efectivo;
	@Column(name = "pagoenlinea")
	private double pagoenlinea;
	@Column(name = "cuscatlan")
	private double cuscatlan;
	@Column(name = "qrtransferencias")
	private double qrtransferencias;
	@Column(name = "reservas")
	private double reservas;
	@Column(name = "efectivoentregar")
	private double efectivoentregar;
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
	
	public long getIdhistorialcaja() {
		return idhistorialcaja;
	}
	public void setIdhistorialcaja(long idhistorialcaja) {
		this.idhistorialcaja = idhistorialcaja;
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
	public double getCompras() {
		return compras;
	}
	public void setCompras(double compras) {
		this.compras = compras;
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
	public double getTotaltarjetas() {
		return totaltarjetas;
	}
	public void setTotaltarjetas(double totaltarjetas) {
		this.totaltarjetas = totaltarjetas;
	}
	public double getVentatotal() {
		return ventatotal;
	}
	public void setVentatotal(double ventatotal) {
		this.ventatotal = ventatotal;
	}
	public double getVentasucursal() {
		return ventasucursal;
	}
	public void setVentasucursal(double ventasucursal) {
		this.ventasucursal = ventasucursal;
	}
	public double getAgricola() {
		return agricola;
	}
	public void setAgricola(double agricola) {
		this.agricola = agricola;
	}
	public double getCredomatic() {
		return credomatic;
	}
	public void setCredomatic(double credomatic) {
		this.credomatic = credomatic;
	}
	public double getDavivienda() {
		return davivienda;
	}
	public void setDavivienda(double davivienda) {
		this.davivienda = davivienda;
	}
	public double getEfectivo() {
		return efectivo;
	}
	public void setEfectivo(double efectivo) {
		this.efectivo = efectivo;
	}
	public double getPagoenlinea() {
		return pagoenlinea;
	}
	public void setPagoenlinea(double pagoenlinea) {
		this.pagoenlinea = pagoenlinea;
	}
	public double getCuscatlan() {
		return cuscatlan;
	}
	public void setCuscatlan(double cuscatlan) {
		this.cuscatlan = cuscatlan;
	}
	public double getQrtransferencias() {
		return qrtransferencias;
	}
	public void setQrtransferencias(double qrtransferencias) {
		this.qrtransferencias = qrtransferencias;
	}
	public double getReservas() {
		return reservas;
	}
	public void setReservas(double reservas) {
		this.reservas = reservas;
	}
	public double getEfectivoentregar() {
		return efectivoentregar;
	}
	public void setEfectivoentregar(double efectivoentregar) {
		this.efectivoentregar = efectivoentregar;
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
	public long getIdsucursal() {
		return idsucursal;
	}
	public void setIdsucursal(long idsucursal) {
		this.idsucursal = idsucursal;
	}
}
