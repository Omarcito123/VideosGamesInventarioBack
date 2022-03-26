package com.apirest.videogame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apirest.videogame.interfaces.ISucursalesService;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.Sucursales;
import com.apirest.videogame.repository.SucursalesRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class SucursalesService implements ISucursalesService {

	@Autowired
	SucursalesRepository sucursalesRepository;
	
	@Autowired
	IntegrationLogService integrationLogService;
	
	String errorEx = "";

	@Override
	public ResponseObject getSucursales() {
		ResponseObject response = new ResponseObject();
		List<Sucursales> sucursalesList = new ArrayList<Sucursales>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			sucursalesList =  (List<Sucursales>) sucursalesRepository.findAll();	
			response.setState("Success");
			response.setMessage("Sucursales cargadas exitosamente");
			response.setData(sucursalesList);
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getSucursales");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no recibe parametros: ");
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject saveSucursal(Sucursales sucu) {
		ResponseObject response = new ResponseObject();
		List<Sucursales> sucursalesList = new ArrayList<Sucursales>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			sucu.setDateadd(fechaSal.getFecha());
			sucursalesRepository.save(sucu);
			response.setState("Success");
			response.setMessage("Sucursales guardada exitosamente");
			response.setData(sucursalesList);
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("saveSucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no recibe parametros: " + sucu.toString());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getSucursalById(Sucursales sucu) {
		ResponseObject response = new ResponseObject();
		Optional<Sucursales> sucursales;
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			sucursales = sucursalesRepository.findById(sucu.getIdsucursal());
			if(sucursales.isPresent()) {
				response.setState("Success");
				response.setMessage("Sucursal encontrada exitosamente");
				response.setData(sucursales);
			}else {
				response.setState("Error");
				response.setMessage("Sucursal no encontra exitosamente");
			}
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getSucursalById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no recibe parametros: " + sucu.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject updateSucursal(Sucursales sucu) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<Sucursales> sucursales = sucursalesRepository.findById(sucu.getIdsucursal());
			if(sucursales.isPresent()) { 
				sucursales.get().setDatemod(fechaSal.getFecha());
				sucursales.get().setNombre(sucu.getNombre());
				sucursalesRepository.save(sucursales.get());
				response.setState("Success");
				response.setMessage("Sucursal modificada exitosamente");
			}else {
				response.setState("Error");
				response.setMessage("Sucursal no encontrada");
			}			
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("updateSucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no recibe parametros: " + sucu.toString());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteSucursal(Sucursales sucu) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<Sucursales> sucursales = sucursalesRepository.findById(sucu.getIdsucursal());
			if(sucursales.isPresent()) {
				sucursalesRepository.deleteById(sucursales.get().getIdsucursal());
				response.setState("Success");
				response.setMessage("Sucursal eliminada exitosamente");
			}else {
				response.setState("Error");
				response.setMessage("Sucursal no encontrada");
			}			
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteSucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no recibe parametros: " + sucu.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getIdSucursalBodeg() {
		ResponseObject response = new ResponseObject();
		Sucursales sucursal = new Sucursales();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			sucursal =  sucursalesRepository.getIdSucursalBodeg();	
			response.setState("Success");
			response.setMessage("Sucursales cargadas exitosamente");
			response.setData(sucursal.getIdsucursal());
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getSucursales");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no recibe parametros: ");
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

}
