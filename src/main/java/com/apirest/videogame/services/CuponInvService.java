package com.apirest.videogame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.ICuponInvService;
import com.apirest.videogame.model.CuponInv;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.CuponInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class CuponInvService implements ICuponInvService {

	@Autowired
	CuponInvRepository cuponInvRepository;

	@Autowired
	IntegrationLogService integrationLogService;

	String errorEx = "", codeCupon = "";

	@Override
	public ResponseObject createCupon(CuponInv cupon) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			cupon.setDateadd(fechaSal.getFecha());
			generateRandomCode(15);
			cupon.setCupon(this.codeCupon);
			cuponInvRepository.save(cupon);			
			response.setState("Success");
			response.setMessage("Cupon creado exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createCupon");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("cupon: " + String.valueOf(cupon));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	public void generateRandomCode(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		
		CuponInv cupon = cuponInvRepository.findCuponByCodigo(sb.toString());
		if(cupon == null) {
			this.codeCupon = sb.toString();
		}else {
			this.codeCupon = "";
			generateRandomCode(15);
		}		
	}

	@Override
	public ResponseObject getCuponesList() {
		ResponseObject response = new ResponseObject();
		List<CuponInv> cuponesList = new ArrayList<CuponInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			cuponesList = (List<CuponInv>) cuponInvRepository.findAll();
			response.setState("Success");
			response.setMessage("Cupones cargados exitosamente");
			response.setData(cuponesList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getCuponesList");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("sin parametros: ");
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findCuponById(CuponInv cupon) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<CuponInv> cuponFind = cuponInvRepository.findById(cupon.getIdcupon());
			if(cuponFind.isPresent()) {
				response.setState("Success");
				response.setMessage("Cupon encontrado exitosamente");
				response.setData(cuponFind);
			}else {
				response.setState("Error");
				response.setMessage("Cupon no pudo ser encontrado");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findCuponById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcupon: " + cupon.getIdcupon());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findCuponByCodigoAndDate(CuponInv cupon) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {			
			CuponInv cuponFind = cuponInvRepository.findCuponByCodigoAndDate(cupon.getCupon(), cupon.getFechavalido());
			if(cuponFind != null) {
				if(cupon.getCategoria().toUpperCase().contains(cuponFind.getCategoria())) {
					if(cuponFind.getTipocupon().equals("Con limite de uso")) {
						int limite = (int) cuponFind.getLimiteuso();
						int uso = (int) cuponFind.getNumerouso() + 1;
						if(uso <= limite) {
							response.setState("Success");
							response.setMessage("Cupon encontrado exitosamente");
							response.setData(cuponFind);
						}else {
							response.setState("Error");
							response.setMessage("Cupon excedido en numero de uso");
						}
					}else {
						if(cupon.getCategoria().contains(cuponFind.getCategoria())) {
							response.setState("Success");
							response.setMessage("Cupon encontrado exitosamente");
							response.setData(cuponFind);
						}else {
							response.setState("Error");
							response.setMessage("Cupon invalido o expirado");
						}	
					}
				}else if(cuponFind.getCategoria().equals("TODAS")){
					if(cuponFind.getTipocupon().equals("Con limite de uso")) {
						int limite = (int) cuponFind.getLimiteuso();
						int uso = (int) cuponFind.getNumerouso() + 1;
						if(uso <= limite) {
							response.setState("Success");
							response.setMessage("Cupon encontrado exitosamente");
							response.setData(cuponFind);
						}else {
							response.setState("Error");
							response.setMessage("Cupon excedido en numero de uso");
						}
					}else {
						if(cupon.getCategoria().contains(cuponFind.getCategoria())) {
							response.setState("Success");
							response.setMessage("Cupon encontrado exitosamente");
							response.setData(cuponFind);
						}else {
							response.setState("Error");
							response.setMessage("Cupon invalido o expirado");
						}	
					}
				}else {
					response.setState("Error");
					response.setMessage("Cupon invalido o expirado");
				}							
			}else {
				response.setState("Error");
				response.setMessage("Cupon invalido o expirado");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findCuponByCodigo");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("cupon: " + cupon.getCupon());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject editCupon(CuponInv cupon) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<CuponInv> cuponUpdate = cuponInvRepository.findById(cupon.getIdcupon());
			if (cuponUpdate.isPresent()) {
				cupon.setDatemod(fechaSal.getFecha());
				cuponInvRepository.save(cupon);
				response.setState("Success");
				response.setMessage("Cupon actualizado exitosamente");
			} else {
				response.setState("Error");
				response.setMessage("Cupon no pudo ser actualizado");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("editCupon");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcupon: " + String.valueOf(cupon));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteCuponById(CuponInv cupon) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			cuponInvRepository.deleteById(cupon.getIdcupon());
			response.setState("Success");
			response.setMessage("Cupon eliminado exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteCuponById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcupon: " + cupon.getIdcupon());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

}
