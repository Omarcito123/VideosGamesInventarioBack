package com.apirest.videogame.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class metodosGenericos {

	public metodosGenericos() {
	}
	
	public String getFecha() {
		String fecha = "";
		Instant nowUtc = Instant.now();
		ZoneId americaElSal = ZoneId.of("America/El_Salvador");
		ZonedDateTime nowElSal = ZonedDateTime.ofInstant(nowUtc, americaElSal);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		fecha = dtf.format(nowElSal);
		return fecha;
	}
	
	public String getFechaSimple() {
		String fecha = "";
		Instant nowUtc = Instant.now();
		ZoneId americaElSal = ZoneId.of("America/El_Salvador");
		ZonedDateTime nowElSal = ZonedDateTime.ofInstant(nowUtc, americaElSal);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		fecha = dtf.format(nowElSal);
		return fecha;
	}
	
	public String getFechaCorta() {
		String fecha = "";
		Instant nowUtc = Instant.now();
		ZoneId americaElSal = ZoneId.of("America/El_Salvador");
		ZonedDateTime nowElSal = ZonedDateTime.ofInstant(nowUtc, americaElSal);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		fecha = dtf.format(nowElSal);
		return fecha;
	}
}
