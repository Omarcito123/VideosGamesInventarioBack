package com.apirest.videogame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.ICategoriaInvService;
import com.apirest.videogame.model.CategoriaInv;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.CategoriaInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class CategoriasInvService implements ICategoriaInvService {

	@Autowired
	CategoriaInvRepository categoriaInvRepository;

	@Autowired
	IntegrationLogService integrationLogService;
	
	String errorEx = "";

	@Override
	public ResponseObject createCategoria(CategoriaInv categoria) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			categoria.setDateadd(fechaSal.getFecha());
			categoriaInvRepository.save(categoria);			
			response.setState("Success");
			response.setMessage("Categoria creada exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createCategoria");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("categoria: " + String.valueOf(categoria));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getCategoriasList() {
		ResponseObject response = new ResponseObject();
		List<CategoriaInv> categoriasList = new ArrayList<CategoriaInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			categoriasList = (List<CategoriaInv>) categoriaInvRepository.findAll();
			response.setState("Success");
			response.setMessage("Categorias cargadas exitosamente");
			response.setData(categoriasList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getCategoriasList");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("sin parametros: ");
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findCategoriasById(CategoriaInv categoria) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<CategoriaInv> categoriaFind = categoriaInvRepository.findById(categoria.getIdcategoria());
			if(categoriaFind.isPresent()) {
				response.setState("Success");
				response.setMessage("Categoria encontrada exitosamente");
				response.setData(categoriaFind);
			}else {
				response.setState("Error");
				response.setMessage("Compra no pudo ser encontrado");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findCategoriasById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcategoria: " + categoria.getIdcategoria());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject editCategoria(CategoriaInv categoria) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<CategoriaInv> categoriaUpdate = categoriaInvRepository.findById(categoria.getIdcategoria());
			if (categoriaUpdate.isPresent()) {
				categoria.setDatemod(fechaSal.getFecha());
				categoriaInvRepository.save(categoria);
				response.setState("Success");
				response.setMessage("Categoria actualizada exitosamente");
			} else {
				response.setState("Error");
				response.setMessage("Categoria no pudo ser actualizado");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("editCategoria");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcategoria: " + String.valueOf(categoria));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteCategoriaById(CategoriaInv categoria) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			categoriaInvRepository.deleteById(categoria.getIdcategoria());
			response.setState("Success");
			response.setMessage("Categoria eliminada exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteCategoriaById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcategoria: " + categoria.getIdcategoria());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

}
