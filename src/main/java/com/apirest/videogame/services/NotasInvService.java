package com.apirest.videogame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.INotasService;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.NotasInv;
import com.apirest.videogame.model.NotasObject;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.NotasInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class NotasInvService implements INotasService {

	@Autowired
	NotasInvRepository notasInvRepository;

	@Autowired
	IntegrationLogService integrationLogService;

	String errorEx = "", codeCupon = "";

	@Override
	public ResponseObject createNota(NotasInv nota) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			nota.setDateadd(fechaSal.getFecha());
			notasInvRepository.save(nota);			
			response.setState("Success");
			response.setMessage("Nota creada exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createNota");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("nota: " + String.valueOf(nota));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getNotasListByUserAndDates(NotasInv nota) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		List<NotasObject> notasList  = new ArrayList<>();
		try {
			List<Object[]> objeto = notasInvRepository.getNotasListByUserAndDates(nota.getIduseradd(), nota.getDateadd(), nota.getDatemod());			
			for (int i = 0; i < objeto.size(); i++) {
				NotasObject notaObj = new NotasObject();
				Object[] obj = objeto.get(i);
				notaObj.setIdnota(obj[0] == null ? 0 : Long.parseLong(obj[0].toString()));
				notaObj.setNota(obj[1] == null ? "" : obj[1].toString());				
				notaObj.setIduseradd(obj[2] == null ? 0 : Long.parseLong(obj[2].toString()));
				notaObj.setDateadd(obj[3] == null ? "" : obj[3].toString());
				notaObj.setIdusermod(obj[4] == null ? 0 : Long.parseLong(obj[4].toString()));
				notaObj.setDatemod(obj[5] == null ? "" : obj[5].toString());
				notaObj.setUsuario(obj[6] == null ? "" : obj[6].toString());
				notasList.add(notaObj);
			}
			response.setState("Success");
			response.setMessage("Nota creada exitosamente");
			response.setData(notasList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createNota");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("nota: " + String.valueOf(nota));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findNotaById(NotasInv nota) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		Optional<NotasInv> notaFind;
		try {			
			notaFind =  notasInvRepository.findById(nota.getIdnota());
			if(notaFind != null) {
				if(notaFind.get() != null) {
					response.setState("Success");
					response.setMessage("Nota encontrada exitosamente");
					response.setData(notaFind);
				}else {
					response.setState("Success");
					response.setMessage("Nota no encontrada");
				}
			}else {
				response.setState("Success");
				response.setMessage("Nota no encontrada");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findNotaById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("nota: " + nota.getIdnota());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject editNota(NotasInv nota) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		Optional<NotasInv> notaFind;
		try {			
			notaFind =  notasInvRepository.findById(nota.getIdnota());
			if(notaFind != null) {
				if(notaFind.get() != null) {
					notaFind.get().setNota(nota.getNota());
					notaFind.get().setIdusermod(nota.getIdusermod());
					notaFind.get().setDatemod(nota.getDatemod());
					notasInvRepository.save(notaFind.get());
					response.setState("Success");
					response.setMessage("Nota actualizada exitosamente");
				}else {
					response.setState("Success");
					response.setMessage("Nota no encontrada");
				}
			}else {
				response.setState("Success");
				response.setMessage("Nota no encontrada");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("editNota");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("nota: " + String.valueOf(nota));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteNotaById(NotasInv nota) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		Optional<NotasInv> notaFind;
		try {			
			notaFind =  notasInvRepository.findById(nota.getIdnota());
			if(notaFind != null) {
				if(notaFind.get() != null) {
					notasInvRepository.deleteById(nota.getIdnota());
					response.setState("Success");
					response.setMessage("Nota eliminada exitosamente");
				}else {
					response.setState("Success");
					response.setMessage("Nota no encontrada");
				}
			}else {
				response.setState("Success");
				response.setMessage("Nota no encontrada");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteNotaById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("nota: " + String.valueOf(nota));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

}
