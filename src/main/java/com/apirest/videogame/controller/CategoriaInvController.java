package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.ICategoriaInvService;
import com.apirest.videogame.model.CategoriaInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/categoriaInv")
public class CategoriaInvController {
	
	@Autowired
	ICategoriaInvService categoriaInvService;

	@PostMapping("/createCategoria")
	public ResponseObject createCategoria(@RequestBody CategoriaInv categoria) {
		return categoriaInvService.createCategoria(categoria);
	}
	
	@GetMapping("/getCategoriasList")
	public ResponseObject getCategoriasList() {
		return categoriaInvService.getCategoriasList();
	}
	
	@PostMapping("/findCategoriasById")
	public ResponseObject findCategoriasById(@RequestBody CategoriaInv categoria) {
		return categoriaInvService.findCategoriasById(categoria);
	}
	
	@PostMapping("/editCategoria")
	public ResponseObject editCategoria(@RequestBody CategoriaInv categoria) {
		return categoriaInvService.editCategoria(categoria);
	}

	@PostMapping("/deleteCategoriaById")
	public ResponseObject deleteCategoriaById(@RequestBody CategoriaInv categoria) {
		return categoriaInvService.deleteCategoriaById(categoria);
	}
}