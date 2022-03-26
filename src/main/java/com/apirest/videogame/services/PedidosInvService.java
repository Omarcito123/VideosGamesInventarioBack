package com.apirest.videogame.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.IPedidosInvService;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.PedidosInv;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.PedidoInvRepository;
import com.apirest.videogame.repository.SucursalesRepository;
import com.apirest.videogame.repository.UserInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class PedidosInvService implements IPedidosInvService {

	@Autowired
	PedidoInvRepository pedidoInvRepository;

	@Autowired
	UserInvRepository userInvRepository;
	
	@Autowired
	SucursalesRepository sucursalesRepository;

	@Autowired
	IntegrationLogService integrationLogService;

	String errorEx = "";

	@Override
	public ResponseObject createPedido(PedidosInv pedido) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			pedido.setDateadd(fechaSal.getFecha());
			pedido.setEstado("Solicitado");
			pedidoInvRepository.save(pedido);
			response.setState("Success");
			response.setMessage("Pedido creado exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createPedido");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("objeto: " + String.valueOf(pedido));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject savePedidosListInv(PedidosInv[] pedidoList) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			if(pedidoList != null) {
				for (PedidosInv pedido: pedidoList) {
					pedido.setDateadd(fechaSal.getFecha());
					pedidoInvRepository.save(pedido);
					response.setState("Success");
					response.setMessage("Pedido creado exitosamente");				
				}
			}			
			response.setState("Success");
			response.setMessage("Pedido creado exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("savePedidosListInv");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("objeto: " + String.valueOf(pedidoList));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject getListPedidosBySucursal(PedidosInv pedido) {
		ResponseObject response = new ResponseObject();
		List<PedidosInv> pedidoList = new ArrayList<PedidosInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<Object[]> objeto = pedidoInvRepository.getListPedidosBySucursal(pedido.getIdsucursal());
			for (int i = 0; i < objeto.size(); i++) {
				PedidosInv ped = new PedidosInv();
				Object[] obj = objeto.get(i);
				ped.setIdpedidoinv(obj[0] != null ? Long.parseLong(("" + obj[0])) : 0);
				ped.setEstado(obj[1] != null ? obj[1].toString() : "");
				ped.setCantidad(obj[2] != null ? Integer.parseInt(("" + obj[2])) : 0);
				ped.setIdprodinv(obj[3] != null ? Integer.parseInt(("" + obj[3])) : 0);
				ped.setNombreproducto(obj[4] != null ? obj[4].toString() : "");
				ped.setExistencia(obj[5] != null ? Integer.parseInt(("" + obj[5])) : 0);
				ped.setExistenciabodega(obj[6] != null ? Integer.parseInt(("" + obj[6])) : 0);
				ped.setIdsucursal(obj[7] != null ? Long.parseLong(("" + obj[7])) : 0);
				ped.setFechaentrega(obj[8] != null ? obj[8].toString() : "");
				ped.setIduseradd(obj[9] != null ? Long.parseLong(("" + obj[9])) : 0);
				ped.setDateadd(obj[10] != null ? obj[10].toString() : "");
				ped.setIdusermod(obj[11] != null ? Long.parseLong(("" + obj[11])) : 0);
				ped.setDatemod(obj[12] != null ? obj[12].toString() : "");
				pedidoList.add(ped);
			}
			response.setState("Success");
			response.setMessage("Pedidos cargados exitosamente");
			response.setData(pedidoList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListPedidosBySucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + pedido.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject findPedidoById(PedidosInv pedido) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<PedidosInv> pedidoFind = pedidoInvRepository.findById(pedido.getIdpedidoinv());
			response.setState("Success");
			response.setMessage("Pedido cargado exitosamente");
			response.setData(pedidoFind);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findPedidoById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idpedido: " + pedido.getIdpedidoinv());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject editPedido(PedidosInv pedido) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<PedidosInv> pedidoFind = pedidoInvRepository.findById(pedido.getIdpedidoinv());
			if(pedidoFind != null) {
				if(pedidoFind.get() != null) {
					pedidoFind.get().setFechaentrega(pedido.getFechaentrega());
					pedidoFind.get().setEstado(pedido.getEstado());
					pedidoFind.get().setCantidad(pedido.getCantidad());
					pedidoFind.get().setIdprodinv(pedido.getIdprodinv());
					pedidoFind.get().setNombreproducto(pedido.getNombreproducto());
					pedidoFind.get().setDatemod(fechaSal.getFecha());
					pedidoInvRepository.save(pedidoFind.get());
					response.setState("Success");
					response.setMessage("Pedido actualizado exitosamente");
				}else {
					response.setState("Error");
					response.setMessage("Pedido no encontrado");
				}	
			}else {
				response.setState("Error");
				response.setMessage("Pedido no encontrado");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("editPedido");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idpedido: " + pedido.getIdpedidoinv());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject deletePedidoById(PedidosInv pedido) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<PedidosInv> pedidoFind = pedidoInvRepository.findById(pedido.getIdpedidoinv());
			if(pedidoFind != null) {
				if(pedidoFind.get() != null) {
					pedidoInvRepository.deleteById(pedido.getIdpedidoinv());
					response.setState("Success");
					response.setMessage("Pedido eliminado exitosamente");
				}else {
					response.setState("Error");
					response.setMessage("Pedido no encontrado");
				}	
			}else {
				response.setState("Error");
				response.setMessage("Pedido no encontrado");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deletePedidoById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idpedido: " + pedido.getIdpedidoinv());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
}
