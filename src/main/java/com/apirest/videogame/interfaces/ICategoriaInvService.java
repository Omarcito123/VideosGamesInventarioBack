package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.CategoriaInv;
import com.apirest.videogame.model.ResponseObject;

public interface ICategoriaInvService {
	public ResponseObject createCategoria(CategoriaInv categoria);
    public ResponseObject getCategoriasList();
    public ResponseObject findCategoriasById(CategoriaInv categoria);
    public ResponseObject editCategoria(CategoriaInv categoria);
    public ResponseObject deleteCategoriaById(CategoriaInv categoria);
}