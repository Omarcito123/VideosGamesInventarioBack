package com.apirest.videogame.model;

public class PointsResponse {
    private String idpoint;
    private String username;
	private String points;
    private String totals;
    private String codefactura;
	private String description;
    private String useradd;
	private String dateadd;
	
	public String getIdpoint() {
		return idpoint;
	}
	public void setIdpoint(String idpoint) {
		this.idpoint = idpoint;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getTotals() {
		return totals;
	}
	public void setTotals(String totals) {
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
	public String getUseradd() {
		return useradd;
	}
	public void setUseradd(String useradd) {
		this.useradd = useradd;
	}
	public String getDateadd() {
		return dateadd;
	}
	public void setDateadd(String dateadd) {
		this.dateadd = dateadd;
	}
}
