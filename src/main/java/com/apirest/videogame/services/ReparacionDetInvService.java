package com.apirest.videogame.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.IReparacionDetInvService;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ReparacionDetInv;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.ReparacionDetInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class ReparacionDetInvService implements IReparacionDetInvService {

	@Autowired
	ReparacionDetInvRepository reparacionDetInvRepository;

	@Autowired
	IntegrationLogService integrationLogService;

	String errorEx = "";

	@Override
	public ResponseObject createReparacionDet(ReparacionDetInv rep) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			rep.setDateadd(fechaSal.getFecha());
			reparacionDetInvRepository.save(rep);
			response.setState("Success");
			response.setMessage("Repuesto agregado exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createReparacionDet");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("objeto: " + rep.toString());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findReparacionDetById(ReparacionDetInv rep) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<ReparacionDetInv> repFind = reparacionDetInvRepository.findById(rep.getIdreparaciondet());
			response.setState("Success");
			response.setMessage("Repuesto encontrado exitosamente");
			response.setData(repFind);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findReparacionDetById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idreparaciondet: " + rep.getIdreparaciondet());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findReparacionDetByIdEnc(ReparacionDetInv rep) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<ReparacionDetInv> repFind = reparacionDetInvRepository.findReparacionDetByIdEnc(rep.getIdreparaciondet(), rep.getIdreparacionenc());
			response.setState("Success");
			response.setMessage("Repuestos encontrados exitosamente");
			response.setData(repFind);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findReparacionDetByIdEnc");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idreparaciondet: " + rep.getIdreparaciondet());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject findDetallesByIdEnc(ReparacionDetInv rep) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<ReparacionDetInv> repFind = reparacionDetInvRepository.findDetallesByIdEnc(rep.getIdreparacionenc());
			response.setState("Success");
			response.setMessage("Repuestos encontrados exitosamente");
			response.setData(repFind);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findReparacionDetByIdEnc");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idreparaciondet: " + rep.getIdreparaciondet());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject updateReparacionDet(ReparacionDetInv rep) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			rep.setDatemod(fechaSal.getFecha());
			reparacionDetInvRepository.save(rep);
			response.setState("Success");
			response.setMessage("Repuesto actualizado exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("updateReparacionDet");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("objeto: " + rep.toString());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteReparacionDetById(ReparacionDetInv rep) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			reparacionDetInvRepository.deleteById(rep.getIdreparaciondet());
			response.setState("Success");
			response.setMessage("Repuesto eliminada exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteReparacionDetById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idreparaciondet: " + rep.getIdreparaciondet());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
}
