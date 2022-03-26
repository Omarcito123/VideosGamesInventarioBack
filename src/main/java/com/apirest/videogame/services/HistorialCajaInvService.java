package com.apirest.videogame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.IHistorialCajaInvService;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.HistorialCajaInv;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.HistorialCajaInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class HistorialCajaInvService implements IHistorialCajaInvService {

	@Autowired
	HistorialCajaInvRepository historialCajaInvRepository;
	
	String errorEx = "";
	
	@Autowired
	IntegrationLogService integrationLogService;

	@Override
	public ResponseObject createHistorialCaja(HistorialCajaInv caja) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			caja.setDateadd(fechaSal.getFechaCorta());
			List<HistorialCajaInv> historialCajaList = historialCajaInvRepository.findCajaByDateAndSucursal(caja.getIdsucursal(), caja.getDateadd());
			if(historialCajaList.size() == 0) {				
				historialCajaInvRepository.save(caja);
				response.setState("Success");
				response.setMessage("Cierre de caja creado exitosamente");
			}else {
				response.setState("Error");
				response.setMessage("Ya existe un cierre de caja para este dia");
			}			
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("saveSucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no recibe parametros: " + caja.toString());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findCajasMensuales(HistorialCajaInv caja) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		List<HistorialCajaInv> cajasList = new ArrayList<HistorialCajaInv>();
		try {
			cajasList = historialCajaInvRepository.findCajasMensuales(caja.getIdsucursal(), caja.getDateadd());
			response.setState("Success");
			response.setData(cajasList);
			response.setMessage("Cierre de caja encontrado exitosamente");
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("saveSucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no recibe parametros: " + caja.toString());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findCajaByDateAndSucursal(HistorialCajaInv caja) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<HistorialCajaInv> historialCajaList = historialCajaInvRepository.findCajaByDateAndSucursal(caja.getIdsucursal(), caja.getDateadd());
			response.setState("Success");
			response.setData(historialCajaList);
			response.setMessage("Cierre de caja encontrado exitosamente");
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("saveSucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no recibe parametros: " + caja.toString());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject updateHistorialCaja(HistorialCajaInv caja) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<HistorialCajaInv> historialCaja = historialCajaInvRepository.findById(caja.getIdhistorialcaja());
			if(historialCaja != null) {
				if(historialCaja.get() != null) {
					response.setState("Success");
					caja.setDatemod(fechaSal.getFecha());
					historialCajaInvRepository.save(caja);
					response.setMessage("Cierre de caja modificado exitosamente");
				}else {
					response.setState("Error");
					response.setMessage("Cierre de caja creado exitosamente");
				}
			}else {
				response.setState("Error");
				response.setMessage("Cierre de caja creado exitosamente");
			}			
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("saveSucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no recibe parametros: " + caja.toString());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteHistorialCajaById(HistorialCajaInv caja) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<HistorialCajaInv> historialCaja = historialCajaInvRepository.findById(caja.getIdhistorialcaja());
			if(historialCaja != null) {
				if(historialCaja.get() != null) {
					response.setState("Success");
					historialCajaInvRepository.deleteById(caja.getIdhistorialcaja());
					response.setMessage("Cierre de caja modificado exitosamente");
				}else {
					response.setState("Error");
					response.setMessage("Cierre de caja creado exitosamente");
				}
			}else {
				response.setState("Error");
				response.setMessage("Cierre de caja creado exitosamente");
			}			
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("saveSucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no recibe parametros: " + caja.toString());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findCajaByRangoDateAndSucursal(HistorialCajaInv caja) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<HistorialCajaInv> historialCajaList = historialCajaInvRepository.findCajaByRangoDateAndSucursal(caja.getIdsucursal(), caja.getDateadd(), caja.getDatemod());
			response.setState("Success");
			response.setData(historialCajaList);
			response.setMessage("Historial de cierres de caja encontrados exitosamente");			
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("saveSucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no recibe parametros: " + caja.toString());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
}
