package com.apirest.videogame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.IComisionVendedorInvService;
import com.apirest.videogame.model.ComisionVendedorInv;
import com.apirest.videogame.model.ComisionVendedorResponse;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.ComisionVendedorInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class ComisionVendedorInvService implements IComisionVendedorInvService {

	@Autowired
	ComisionVendedorInvRepository comisionVendedorInvRepository;

	@Autowired
	IntegrationLogService integrationLogService;

	String errorEx = "";

	@Override
	public ResponseObject createComisionVendedor(ComisionVendedorInv comisionV) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			comisionV.setDateadd(fechaSal.getFecha());
			comisionVendedorInvRepository.save(comisionV);			
			response.setState("Success");
			response.setMessage("Comision creada exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createComision");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("createComisionVendedor: " + String.valueOf(comisionV));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getComisionesByVendedorList(ComisionVendedorInv comisionV) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			String[] fecha = comisionV.getDateadd().split(" ");
			String anio = fecha[1];
			List<Object[]> objeto = comisionVendedorInvRepository.getComisionesByVendedorList(comisionV.getIdusuario(), comisionV.getMes(), anio);
			response.setState("Success");
			response.setMessage("Comisiones cargadas exitosamente");
			List<ComisionVendedorResponse> listCV = new ArrayList<ComisionVendedorResponse>();
			if(objeto != null) {
				for (int i = 0; i < objeto.size(); i++) {
					ComisionVendedorResponse comiV = new ComisionVendedorResponse();
					Object[] obj = objeto.get(i);
					comiV.setIdcomisionvendedor(obj[0].toString());
					comiV.setComision(Double.parseDouble(obj[1].toString()));
					comiV.setDateadd(obj[2].toString());
					comiV.setDatemod(obj[3] == null ? "" : obj[3].toString());
					comiV.setIdsucursal(obj[4].toString());
					comiV.setIduseradd(obj[5].toString());
					comiV.setIdusermod(obj[6].toString());
					comiV.setIdusuario(obj[7].toString());
					comiV.setTipocomision(obj[8].toString());
					comiV.setVendedor(obj[9].toString());
					comiV.setSucursal(obj[10].toString());
					comiV.setProducto(obj[11] == null ? "" : obj[11].toString());
					comiV.setVentatotal(obj[12] == null ? 0 : Double.parseDouble(obj[12].toString()));
					listCV.add(comiV);
				}
			}
			response.setData(listCV);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getComisionesByVendedorList");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idusuario: " + comisionV.getIdusuario());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getComisionesByVendedorAndSucursalList(ComisionVendedorInv comisionV) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<Object[]> objeto = comisionVendedorInvRepository.getComisionesByVendedorAndSucursalList(comisionV.getIdusuario(), comisionV.getIdsucursal());
			response.setState("Success");
			response.setMessage("Comisiones cargadas exitosamente");
			List<ComisionVendedorResponse> listCV = new ArrayList<ComisionVendedorResponse>();
			if(objeto != null) {
				for (int i = 0; i < objeto.size(); i++) {
					ComisionVendedorResponse comiV = new ComisionVendedorResponse();
					Object[] obj = objeto.get(i);
					comiV.setIdcomisionvendedor(obj[0].toString());
					comiV.setComision(Double.parseDouble(obj[1].toString()));
					comiV.setDateadd(obj[2].toString());
					comiV.setDatemod(obj[3].toString());
					comiV.setIdsucursal(obj[4].toString());
					comiV.setIduseradd(obj[5].toString());
					comiV.setIdusermod(obj[6].toString());
					comiV.setIdusuario(obj[7].toString());
					comiV.setTipocomision(obj[8].toString());
					comiV.setVendedor(obj[9].toString());
					comiV.setSucursal(obj[10].toString());
					listCV.add(comiV);
				}
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getComisionesByVendedorAndSucursalList");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idusuario: " + comisionV.getIdusuario() + " idsucursal: " + comisionV.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findComisionVendedorById(ComisionVendedorInv comisionV) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<ComisionVendedorInv> comisionVFind = comisionVendedorInvRepository.findById(comisionV.getIdcomisionvendedor());
			if(comisionVFind.isPresent()) {
				response.setState("Success");
				response.setMessage("Comision encontrada exitosamente");
				response.setData(comisionVFind);
			}else {
				response.setState("Error");
				response.setMessage("Compra no pudo ser encontrado");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findComisionVendedorById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcomisionvendedor: " + comisionV.getIdcomisionvendedor());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject editComisionVendedor(ComisionVendedorInv comisionV) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<ComisionVendedorInv> comisionVUpdate = comisionVendedorInvRepository.findById(comisionV.getIdcomisionvendedor());
			if (comisionVUpdate.isPresent()) {
				comisionV.setDatemod(fechaSal.getFecha());
				comisionVendedorInvRepository.save(comisionV);
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
			error.setServicio("editComisionVendedor");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("parametros: " + String.valueOf(comisionV));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteComisionVendedorById(ComisionVendedorInv comisionV) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			comisionVendedorInvRepository.deleteById(comisionV.getIdcomisionvendedor());
			response.setState("Success");
			response.setMessage("Comision eliminada exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteComisionVendedorById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("dcomisionvendedor: " + comisionV.getIdcomisionvendedor());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getComisionesByIdVenta(long id) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			ComisionVendedorInv comisionVendedor = comisionVendedorInvRepository.getComisionesByIdVenta(id);
			if(comisionVendedor != null) {
				response.setData(comisionVendedor);
				response.setState("Success");
				response.setMessage("Comision encontrada exitosamente");
			}else {
				response.setData(comisionVendedor);
				response.setState("Success");
				response.setMessage("No existe comision para esta venta");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteComisionVendedorById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("getComisionesByIdVenta: " + id);
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

}
