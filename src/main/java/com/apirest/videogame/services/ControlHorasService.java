package com.apirest.videogame.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.IControlHorasService;
import com.apirest.videogame.model.ControlHorasInv;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.ControlHorasRespository;
import com.apirest.videogame.repository.ErroresRespository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
//@Transactional
public class ControlHorasService implements IControlHorasService {
	
	@Autowired
    ControlHorasRespository controlHorasRespository;
	
	@Autowired
	ErroresRespository erroresRespository;

	@Override
	public ResponseObject createControl(ControlHorasInv horas) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Instant nowUtc = Instant.now();
			ZoneId americaElSal = ZoneId.of("America/El_Salvador");
			ZonedDateTime nowElSal = ZonedDateTime.ofInstant(nowUtc, americaElSal);
			String fechaBus = fechaSal.getFechaSimple();
			ControlHorasInv control = controlHorasRespository.getControlByUserFechaTurno(horas.getIduser(), fechaBus, horas.getTurno());
			if(control == null) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
				horas.setDateadd(dtf.format(nowElSal));
				horas.setDatecontrol(dtf.format(nowElSal));
				controlHorasRespository.save(horas);
				response.setState("Success");
				response.setMessage("Registro creado exitosamente");
			}else {
				response.setState("Error");
				response.setMessage("Registro ya existe");
			}
		}catch(Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("createConrol"); 
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("id: " + horas.toString());
			erroresRespository.save(error);			
		}
		return response;
	}

	@Override
	public ResponseObject getControlByUserFecha(ControlHorasInv horas) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<ControlHorasInv> controlList = (List<ControlHorasInv>) controlHorasRespository.getControlByUserFecha(horas.getIduser(), horas.getDatecontrol());
			response.setState("Success");
			response.setMessage("Registro encontrado exitosamente");
			response.setData(controlList);
		}catch(Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("getControlByUserFecha");  
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("parametros: " + horas.getIduser() + " " + horas.getDatecontrol());
			erroresRespository.save(error);			
		}
		return response;
	}

	@Override
	public ResponseObject findById(ControlHorasInv horas) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<ControlHorasInv> control = controlHorasRespository.findById(horas.getIdcontrol());
			if(control.isPresent()) {
				response.setState("Success");
				response.setMessage("Registro encontrado exitosamente");
				response.setData(control);
			}else {
				response.setState("Success");
				response.setMessage("Registro no encontrado");
				response.setData(control);
			}			
		}catch(Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("findById");  
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("id: " + horas.getIdcontrol());
			erroresRespository.save(error);			
		}
		return response;
	}

	@Override
	public ResponseObject deleteControlById(ControlHorasInv horas) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			controlHorasRespository.deleteById(horas.getIdcontrol());
			response.setState("Success");
			response.setMessage("Registro eliminado exitosamente");
		}catch(Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("deleteControlById");  
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("id: " + horas.getIdcontrol());
			erroresRespository.save(error);			
		}
		return response;
	}

	@Override
	public ResponseObject update(ControlHorasInv horas) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<ControlHorasInv> control = controlHorasRespository.findById(horas.getIdcontrol());
			if(control.isPresent()) {
				horas.setIdcontrol(control.get().getIdcontrol());
				horas.setIduseradd(control.get().getIduseradd());
				horas.setDateadd(control.get().getDateadd());
				horas.setDatemod(fechaSal.getFecha());
				controlHorasRespository.save(horas);
				response.setState("Success");
				response.setMessage("Registro actualizado exitosamente");
			}else {
				response.setState("Success");
				response.setMessage("Registro no pudo ser actualizado");
				response.setData(control);
			}
		}catch(Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("update");  
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("id: " + horas.getIdcontrol());
			erroresRespository.save(error);			
		}
		return response;
	}

	@Override
	public ResponseObject getControlByUserFechaTurno(ControlHorasInv horas) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			ControlHorasInv control = controlHorasRespository.getControlByUserFechaTurno(horas.getIduser(), horas.getDatecontrol(), horas.getTurno());
			response.setState("Success");
			response.setMessage("Registro encontrado exitosamente");
			response.setData(control);
		}catch(Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("getControlByUserFecha"); 
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("parametros: " + horas.getIduser() + " " + horas.getDatecontrol());
			erroresRespository.save(error);			
		}
		return response;
	}

	@Override
	public ResponseObject getControlByUserMonth(ControlHorasInv horas) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<ControlHorasInv> controlList = (List<ControlHorasInv>) controlHorasRespository.getControlByUserMonth(horas.getIduser(), horas.getDateadd());
			response.setState("Success");
			response.setMessage("Registro encontrado exitosamente");
			response.setData(controlList);
		}catch(Exception e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("getControlByUserFecha"); 
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("parametros: " + horas.getIduser() + " " + horas.getDatecontrol());
			erroresRespository.save(error);			
		}
		return response;
	}

}
