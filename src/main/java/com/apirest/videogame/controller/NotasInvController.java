package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.INotasService;
import com.apirest.videogame.model.NotasInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/notasInv")
public class NotasInvController {
	
	@Autowired
	INotasService notasService;

	@PostMapping("/getNotasListByUserAndDates")
	public ResponseObject getNotasListByUserAndDates(@RequestBody NotasInv nota) {
		return notasService.getNotasListByUserAndDates(nota);
	}
	
	@PostMapping("/createNota")
	public ResponseObject createNota(@RequestBody NotasInv nota) {
		return notasService.createNota(nota);
	}
	
	@PostMapping("/editNota")
	public ResponseObject editNota(@RequestBody NotasInv nota) {
		return notasService.editNota(nota);
	}
	
	@PostMapping("/findNotaById")
	public ResponseObject findNotaById(@RequestBody NotasInv nota) {
		return notasService.findNotaById(nota);
	}
	
	@PostMapping("/deleteNotaById")
	public ResponseObject deleteNotaById(@RequestBody NotasInv nota) {
		return notasService.deleteNotaById(nota);
	}
}