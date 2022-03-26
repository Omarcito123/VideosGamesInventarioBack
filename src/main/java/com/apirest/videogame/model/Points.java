package com.apirest.videogame.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;

@Entity
@Table(name = "Points")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Points {
	@Id
	@Column(name = "idpoint")
    private long idpoint;
	@Column(name = "iduser")
    private long iduser;
    @Column(name = "points")
	private int points;
    @Column(name = "totals")
    private int totals;    
    @Column(name = "codefactura")
	private String codefactura;
    @Column(name = "description")
	private String description;
    @Column(name = "iduseradd")
    private int iduseradd;
    @Column(name = "dateadd")
	private String dateadd;
    @Column(name = "idusermod")
    private int idusermod;
    @Column(name = "datemod")
	private String datemod;
    
	public long getIdpoint() {
		return idpoint;
	}
	public void setIdpoint(long idpoint) {
		this.idpoint = idpoint;
	}
	public long getIduser() {
		return iduser;
	}
	public void setIduser(long iduser) {
		this.iduser = iduser;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getTotals() {
		return totals;
	}
	public void setTotals(int totals) {
		this.totals = totals;
	}
	public String getCodefactura() {
		return codefactura;
	}
	public void setCodefactura(String codefactura) {
		this.codefactura = codefactura;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
