package com.apirest.videogame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.IComisionInvService;
import com.apirest.videogame.model.ComisionInv;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.ComisionInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class ComisionInvService implements IComisionInvService {

	@Autowired
	ComisionInvRepository comisionInvRepository;

	@Autowired
	IntegrationLogService integrationLogService;

	String errorEx = "";

	@Override
	public ResponseObject createComision(ComisionInv comision) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			comision.setDateadd(fechaSal.getFecha());
			comisionInvRepository.save(comision);			
			response.setState("Success");
			response.setMessage("Comision creada exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createComision");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("comision: " + String.valueOf(comision));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getComisionesList() {
		ResponseObject response = new ResponseObject();
		List<ComisionInv> comisionesList = new ArrayList<ComisionInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			comisionesList = (List<ComisionInv>) comisionInvRepository.findAll();
			response.setState("Success");
			response.setMessage("Comisiones cargadas exitosamente");
			response.setData(comisionesList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getComisionesList");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("sin parametros: ");
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ComisionInv findComisionByTipoComision(String tipocomision) {
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			ComisionInv comisionFind = comisionInvRepository.findComisionByTipoComision(tipocomision);
			if(comisionFind != null) {
				return comisionFind;
			}else {
				return null;
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findComisionByTipoComision");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("tipocomision: " + tipocomision);
			integrationLogService.save(error);
			return null;
		}
	}

	@Override
	public ResponseObject findComisionById(ComisionInv comision) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<ComisionInv> comisionFind = comisionInvRepository.findById(comision.getIdcomision());
			if(comisionFind.isPresent()) {
				response.setState("Success");
				response.setMessage("Comision encontrada exitosamente");
				response.setData(comisionFind);
			}else {
				response.setState("Error");
				response.setMessage("Compra no pudo ser encontrado");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findComisionById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcomision: " + comision.getIdcomision());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject editComision(ComisionInv comision) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<ComisionInv> comisionUpdate = comisionInvRepository.findById(comision.getIdcomision());
			if (comisionUpdate.isPresent()) {
				comision.setDatemod(fechaSal.getFecha());
				comisionInvRepository.save(comision);
				response.setState("Success");
				response.setMessage("Comision actualizada exitosamente");
			} else {
				response.setState("Error");
				response.setMessage("Comision no pudo ser actualizado");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("editComision");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcomision: " + String.valueOf(comision));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteComisionById(ComisionInv comision) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			comisionInvRepository.deleteById(comision.getIdcomision());
			response.setState("Success");
			response.setMessage("Comision eliminada exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteComisionById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcomision: " + comision.getIdcomision());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

}
