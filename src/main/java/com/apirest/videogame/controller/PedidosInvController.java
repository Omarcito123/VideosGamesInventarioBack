package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IPedidosInvService;
import com.apirest.videogame.model.PedidosInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/pedidoInv")
public class PedidosInvController {
	
	@Autowired
	IPedidosInvService pedidosInvService;

	@PostMapping("/createPedido")
	public ResponseObject createPedido(@RequestBody PedidosInv pedido) {
		return pedidosInvService.createPedido(pedido);
	}
	
	@PostMapping("/savePedidosListInv")
	public ResponseObject savePedidosListInv(@RequestBody PedidosInv[] pedido) {
		return pedidosInvService.savePedidosListInv(pedido);
	}
	
	@PostMapping("/getListPedidosBySucursal")
	public ResponseObject getListPedidosBySucursal(@RequestBody PedidosInv pedido) {
		return pedidosInvService.getListPedidosBySucursal(pedido);
	}
	
	@PostMapping("/findPedidoById")
	public ResponseObject findPedidoById(@RequestBody PedidosInv pedido) {
		return pedidosInvService.findPedidoById(pedido);
	}
	
	@PostMapping("/editPedido")
	public ResponseObject editPedido(@RequestBody PedidosInv pedido) {
		return pedidosInvService.editPedido(pedido);
	}
	
	@PostMapping("/deletePedidoById")
	public ResponseObject deletePedidoById(@RequestBody PedidosInv pedido) {
		return pedidosInvService.deletePedidoById(pedido);
	}
}