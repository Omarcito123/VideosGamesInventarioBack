package com.apirest.videogame.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;

@Entity
@Table(name = "Products")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Products {
	
	@Id
	@Column(name = "idproduct")
	private long idproduct;
	@Column(name = "nameproduct")
	private String nameproduct;
	@Column(name = "price")
	private Double price;
	@Column(name = "points")
	private int points;
	@Column(name = "quantity")
	private int quantity;
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
	@Column(name = "image")
	private String image;
	@Column(name = "promocion")
	private String promocion;
	
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPromocion() {
		return promocion;
	}
	public void setPromocion(String promocion) {
		this.promocion = promocion;
	}
}
