package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.PedidosInv;
import com.apirest.videogame.model.ResponseObject;

public interface IPedidosInvService {
	public ResponseObject createPedido(PedidosInv pedido);
	public ResponseObject savePedidosListInv(PedidosInv[] pedidos);
    public ResponseObject getListPedidosBySucursal(PedidosInv pedido);
    public ResponseObject findPedidoById(PedidosInv pedido);
    public ResponseObject editPedido(PedidosInv pedido);
    public ResponseObject deletePedidoById(PedidosInv pedido);	
}