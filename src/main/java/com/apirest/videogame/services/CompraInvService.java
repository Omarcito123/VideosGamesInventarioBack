package com.apirest.videogame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.ICompraInvService;
import com.apirest.videogame.model.CompraInv;
import com.apirest.videogame.model.ComprasReporte;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.CajaInvRepository;
import com.apirest.videogame.repository.CompraInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class CompraInvService implements ICompraInvService {

	@Autowired
	CompraInvRepository compraInvRepository;

	@Autowired
	IntegrationLogService integrationLogService;
	
	@Autowired
	CajaInvRepository cajaInvRepository;

	String errorEx = "";

	@Override
	public ResponseObject createCompra(CompraInv[] compraList) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			if(compraList != null) {
				for (CompraInv compra: compraList) {
					compra.setDateadd(fechaSal.getFecha());
					compraInvRepository.save(compra);
					/*CajaInv cajaNow = cajaInvRepository.findCajaByDateAndSucursal(compra.getIdsucursal(), fechaSal.getFecha());
					cajaNow.setIniciocaja(cajaNow.getIniciocaja() - compra.getTotal());
					cajaNow.setCierrecaja(cajaNow.getCierrecaja() - compra.getTotal());
					cajaInvRepository.save(cajaNow);*/
				}
			}			
			response.setState("Success");
			response.setMessage("Compra creado exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createCompra");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + String.valueOf(compraList));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getListComprasBySucursal(CompraInv compra) {
		ResponseObject response = new ResponseObject();
		List<CompraInv> comprasList = new ArrayList<CompraInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			String now = "";
			if(compra.getDateadd() != null && !compra.getDateadd().equals("")) {
				now = compra.getDateadd();
			}else {
				now = fechaSal.getFechaSimple();
			}			
			comprasList = compraInvRepository.getListVentaBySucursal(compra.getIdsucursal(), now);
			response.setState("Success");
			response.setMessage("Compras cargado exitosamente");
			response.setData(comprasList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListComprasBySucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + compra.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getListComprasBySucursalAndByVendedor(CompraInv compra) {
		ResponseObject response = new ResponseObject();
		List<CompraInv> comprasList = new ArrayList<CompraInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			comprasList = compraInvRepository.getListVentaBySucursalAndByVendedor(compra.getIduseradd(), compra.getIdsucursal(), compra.getDateadd(), compra.getDatemod());
			response.setState("Success");
			response.setMessage("Compras cargadas exitosamente");
			response.setData(comprasList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListComprasBySucursalAndByVendedor");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + compra.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	public ResponseObject getListComprasBySucursalAndByVendedorMensuales(CompraInv compra) {
		ResponseObject response = new ResponseObject();
		List<CompraInv> comprasList = new ArrayList<CompraInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			String[] fecha = compra.getDatemod().split(" ");
			String anio = fecha[1];
			comprasList = compraInvRepository.getListComprasBySucursalAndByVendedorMensuales(compra.getIdsucursal(), compra.getDateadd(), anio);
			response.setState("Success");
			response.setMessage("Compras cargadas exitosamente");
			response.setData(comprasList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListComprasBySucursalAndByVendedor");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + compra.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject findCompraById(CompraInv compra) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<CompraInv> compraFind = compraInvRepository.findById(compra.getIdcompra());
			if(compraFind.isPresent()) {
				response.setState("Success");
				response.setMessage("Compra encontrado exitosamente");
				response.setData(compraFind);
			}else {
				response.setState("Error");
				response.setMessage("Compra no pudo ser encontrado");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findCompraById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcompra: " + compra.getIdcompra());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject editCompra(CompraInv compra) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<CompraInv> compraUpdate = compraInvRepository.findById(compra.getIdcompra());
			if (compraUpdate.isPresent()) {
				compra.setDatemod(fechaSal.getFecha());
				compraInvRepository.save(compra);
				response.setState("Success");
				response.setMessage("Compra actualizada exitosamente");
			} else {
				response.setState("Error");
				response.setMessage("Compra no pudo ser actualizado");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("editCompra");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + String.valueOf(compra));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteCompraById(CompraInv compra) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			compraInvRepository.deleteById(compra.getIdcompra());
			response.setState("Success");
			response.setMessage("Compra eliminada exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteCompraById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcompra: " + compra.getIdcompra());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getComprasReporte(CompraInv compra) {
		List<ComprasReporte> comprasReporteList = new ArrayList<ComprasReporte>();
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<Object[]> objeto = compraInvRepository.getComprasReporte(compra.getIdsucursal(), compra.getDateadd(), compra.getDatemod());
			for (int i = 0; i < objeto.size(); i++) {
				ComprasReporte compraObj = new ComprasReporte();
				Object[] obj = objeto.get(i);
				compraObj.setIdcompra(obj[0] == null ? 0 : Long.parseLong(obj[0].toString()));
				compraObj.setFactura(obj[1] == null ? "" : obj[1].toString());
				compraObj.setRecibo(obj[2] == null ? "" : obj[2].toString());
				compraObj.setNombre(obj[3] == null ? "" : obj[3].toString());
				compraObj.setCantidad(obj[4] == null ? 0 : Long.parseLong(obj[4].toString()));
				compraObj.setPreciounidad(obj[5] == null ? 0 : Double.parseDouble(obj[5].toString()));
				compraObj.setTotal(obj[6] == null ? 0 : Double.parseDouble(obj[6].toString()));
				compraObj.setVendedor(obj[7] == null ? "" : obj[7].toString());
				compraObj.setFechaventa(obj[8] == null ? "" : obj[8].toString());
				comprasReporteList.add(compraObj);
			}
			response.setState("Success");
			response.setMessage("Ventas cargadas exitosamente");
			response.setData(comprasReporteList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getComprasReporte");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + compra.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
}
