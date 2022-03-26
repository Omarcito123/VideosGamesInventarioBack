package com.apirest.videogame.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.IReparacionEncInvService;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ReparacionEncInv;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.ReparacionEncInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class ReparacionEncInvService implements IReparacionEncInvService {

	@Autowired
	ReparacionEncInvRepository reparacionEncInvRepository;

	@Autowired
	IntegrationLogService integrationLogService;

	String errorEx = "";

	@Override
	public ResponseObject createReparacionEnc(ReparacionEncInv rep) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			rep.setDateadd(fechaSal.getFecha());
			reparacionEncInvRepository.save(rep);
			response.setState("Success");
			response.setMessage("Reparacion agregado exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createReparacionEnc");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("objeto: " + rep.toString());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findReparacionByIdAndStatus(ReparacionEncInv rep) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			ReparacionEncInv repFind = reparacionEncInvRepository.findReparacionByIdAndStatus(rep.getIdreparacionenc(), rep.getEstado());
			response.setState("Success");
			response.setMessage("Reparacion encontrada exitosamente");
			response.setData(repFind);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findReparacionByIdAndStatus");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idreparacionenc: " + rep.getIdreparacionenc());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject updateReparacionEnc(ReparacionEncInv rep) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			rep.setDatemod(fechaSal.getFecha());
			reparacionEncInvRepository.save(rep);
			response.setState("Success");
			response.setMessage("Reparacion modificada exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("updateReparacionEnc");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("objeto: " + rep.toString());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteReparacionEncById(ReparacionEncInv rep) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			reparacionEncInvRepository.deleteById(rep.getIdreparacionenc());
			response.setState("Success");
			response.setMessage("Reparacion eliminada exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteReparacionEncById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idreparacionenc: " + rep.getIdreparacionenc());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findAllByStatusAndSucursal(ReparacionEncInv rep) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<ReparacionEncInv> repFind = reparacionEncInvRepository.findAllByStatusAndSucursal(rep.getEstado(), rep.getIdsucursal());
			response.setState("Success");
			response.setMessage("Reparacion encontrada exitosamente");
			response.setData(repFind);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findAllByStatus");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idreparacionenc: " + rep.getIdreparacionenc());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
}
