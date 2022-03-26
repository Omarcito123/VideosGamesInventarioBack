package com.apirest.videogame.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.IComisionInvService;
import com.apirest.videogame.interfaces.IComisionVendedorInvService;
import com.apirest.videogame.interfaces.ICuponInvService;
import com.apirest.videogame.interfaces.IProductoInvService;
import com.apirest.videogame.interfaces.ISucursalesService;
import com.apirest.videogame.interfaces.IVentaInvService;
import com.apirest.videogame.model.ComisionInv;
import com.apirest.videogame.model.ComisionVendedorInv;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ProductoInv;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.VentaInv;
import com.apirest.videogame.model.VentasReporte;
import com.apirest.videogame.repository.ProductoInvRepository;
import com.apirest.videogame.repository.VentaInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class VentaInvService implements IVentaInvService {

	@Autowired
	VentaInvRepository ventaInvRepository;
	
	@Autowired
	IComisionVendedorInvService comisionVendedorInvService;
	
	@Autowired
	ProductoInvRepository productoInvRepository;
	
	@Autowired
	IProductoInvService productoInvService;
	
	@Autowired
	ISucursalesService sucursalesService;
	
	@Autowired
	IComisionInvService comisionInvService;
	
	@Autowired
	ICuponInvService cuponInvService;

	@Autowired
	IntegrationLogService integrationLogService;

	String errorEx = "";

	@Override
	public ResponseObject createVenta(VentaInv[] ventaList) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		VentaInv ventaSave = new VentaInv();
		try {
			if(ventaList != null) {
				ProductoInv producto = new ProductoInv();
				for (VentaInv venta: ventaList) {
					if(venta.getTipoventa().equals("Reparacion")) {
						ResponseObject sucursal = sucursalesService.getIdSucursalBodeg();
						long idSuc = (long) sucursal.getData();
						if(venta.getSerie() != null && !venta.getSerie().equals("")) {
							producto = productoInvService.findProductByCodeAndSucursalAndName(idSuc, venta.getSerie(), venta.getNombreproducto());
						}else {
							producto = productoInvService.findProductbyName(idSuc, venta.getNombreproducto());
						}						
					}else {
						if(venta.getSerie() != null && !venta.getSerie().equals("")) {
							producto = productoInvService.findProductByCodeAndSucursalAndName(venta.getIdsucursal(), venta.getSerie(), venta.getNombreproducto());
						}else {
							producto = productoInvService.findProductbyName(venta.getIdsucursal(), venta.getNombreproducto());
						}
					}					
					if(producto != null) {
						if(producto.getExistencia() >= venta.getCantidad()) {
							venta.setDateadd(fechaSal.getFecha());
							venta.setExistencia(producto.getExistencia() - venta.getCantidad());
							ventaSave = ventaInvRepository.save(venta);
							int existencia = producto.getExistencia() - venta.getCantidad();
							producto.setExistencia(existencia);
							productoInvService.editProduct(producto);
							ComisionVendedorInv newComi = new ComisionVendedorInv();
							ComisionInv comision = new ComisionInv();
							if(venta.getCategoria().equals("cod") && venta.getTipopago().equals("QR o Transferencias")) {
								comision = comisionInvService.findComisionByTipoComision("Codigo");
							}else if(venta.getCategoria().equals("cod") && venta.getTipopago().equals("Efectivo")){
								comision = comisionInvService.findComisionByTipoComision("Codigo");
							}else if(venta.getCategoria().equals("cod") && venta.getTipopago().equals("Tarjeta")){
								comision = new ComisionInv();
							}else if(venta.getCategoria().equals("cod") && venta.getTipopago().equals("Pago en linea retira en sucursal")){
								comision = new ComisionInv();
							}else if(venta.getTipopago().equals("QR o Transferencias")){
								comision = comisionInvService.findComisionByTipoComision("Efectivo");
							}else {
								comision = comisionInvService.findComisionByTipoComision(venta.getTipopago());
							}
							if(comision == null) {
								comision = new ComisionInv();
							}
							double valorComision = 0;
							newComi.setIdventa(ventaSave.getIdventa()); 
							valorComision = comision.getValor() * venta.getPreciototal(); 
							newComi.setComision(valorComision);
							newComi.setProducto(venta.getNombreproducto());
							newComi.setVentatotal(venta.getPreciototal());
							newComi.setIduseradd(venta.getIduseradd());
							newComi.setIdusermod(0);
							newComi.setDatemod("");
							newComi.setIdsucursal(venta.getIdsucursal());
							newComi.setIdusuario(venta.getIduseradd());
							newComi.setTipocomision(venta.getTipopago());
							comisionVendedorInvService.createComisionVendedor(newComi);
							response.setState("Success");
							response.setMessage("Venta creada exitosamente");
						}else {
							response.setState("Error");
							response.setMessage("No hay suficientes productos disponibles");
						}
					}else {
						if(venta.getIdproducto() == 0 && venta.getTipoventa().equals("Reserva")) {
							venta.setDateadd(fechaSal.getFecha());
							ventaSave = ventaInvRepository.save(venta);
							ComisionVendedorInv newComi = new ComisionVendedorInv();
							ComisionInv comision = new ComisionInv();
							if(venta.getCategoria().equals("cod") && venta.getTipopago().equals("QR o Transferencias")) {
								comision = comisionInvService.findComisionByTipoComision("Codigo");
							}else if(venta.getCategoria().equals("cod") && venta.getTipopago().equals("Efectivo")){
								comision = comisionInvService.findComisionByTipoComision("Codigo");
							}else if(venta.getCategoria().equals("cod") && venta.getTipopago().equals("Tarjeta")){
								comision = new ComisionInv();
							}else if(venta.getCategoria().equals("cod") && venta.getTipopago().equals("Pago en linea retira en sucursal")){
								comision = new ComisionInv();
							}else if(venta.getTipopago().equals("QR o Transferencias")){
								comision = comisionInvService.findComisionByTipoComision("Efectivo");
							}else {
								comision = comisionInvService.findComisionByTipoComision(venta.getTipopago());
							}
							if(comision == null) {
								comision = new ComisionInv();
							}
							double valorComision = 0;
							newComi.setIdventa(ventaSave.getIdventa()); 
							valorComision = comision.getValor() * venta.getPreciototal(); 
							newComi.setComision(valorComision);
							newComi.setProducto(venta.getNombreproducto());
							newComi.setVentatotal(venta.getPreciototal());
							newComi.setIduseradd(venta.getIduseradd());
							newComi.setIdusermod(0);
							newComi.setDatemod("");
							newComi.setIdsucursal(venta.getIdsucursal());
							newComi.setIdusuario(venta.getIduseradd());
							newComi.setTipocomision(venta.getTipopago());
							comisionVendedorInvService.createComisionVendedor(newComi);
							response.setState("Success");
							response.setMessage("Venta creada exitosamente");
						}else {
							response.setState("Error");
							response.setMessage("Producto no encontrado en la base de datos");
						}						
					}					
				}
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createVenta");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + String.valueOf(ventaList));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getListVentaBySucursal(VentaInv venta) {
		ResponseObject response = new ResponseObject();
		List<VentaInv> ventasList = new ArrayList<VentaInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			ventasList = ventaInvRepository.getListVentaBySucursal(venta.getIdsucursal(), venta.getDateadd());
			response.setState("Success");
			response.setMessage("Ventas cargadas exitosamente");
			response.setData(ventasList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListVentaBySucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + venta.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getListVentaReservaBySucursal(VentaInv venta) {
		ResponseObject response = new ResponseObject();
		List<VentaInv> ventasList = new ArrayList<VentaInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			ventasList = ventaInvRepository.getListVentaReservaBySucursal(venta.getIdsucursal(), venta.getDateadd(), venta.getDatemod());
			response.setState("Success");
			response.setMessage("Ventas cargadas exitosamente");
			response.setData(ventasList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListVentaBySucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + venta.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject getListVentaBySucursalAndByVendedor(VentaInv venta) {
		ResponseObject response = new ResponseObject();
		List<VentaInv> ventasList = new ArrayList<VentaInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate now = LocalDate.now(ZoneId.of("America/El_Salvador"));
			String hoy = dtf.format(now);
			ventasList = ventaInvRepository.getListVentaBySucursalAndByVendedor(venta.getIduseradd(), venta.getIdsucursal(), hoy);
			response.setState("Success");
			response.setMessage("Ventas cargadas exitosamente");
			response.setData(ventasList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListVentaBySucursalAndByVendedor");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + venta.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject getListVentaReservaOnlyBySucursal(VentaInv venta) {
		ResponseObject response = new ResponseObject();
		List<VentaInv> ventasList = new ArrayList<VentaInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			ventasList = ventaInvRepository.getListVentaReservaOnlyBySucursal(venta.getIdsucursal());
			response.setState("Success");
			response.setMessage("Ventas cargadas exitosamente");
			response.setData(ventasList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListVentaReservaOnlyBySucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + venta.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject getListVentaReservaBySucursalAndByVendedor(VentaInv venta) {
		ResponseObject response = new ResponseObject();
		List<VentaInv> ventasList = new ArrayList<VentaInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate now = LocalDate.now(ZoneId.of("America/El_Salvador"));
			String hoy = dtf.format(now);
			ventasList = ventaInvRepository.getListVentaReservaBySucursalAndByVendedor(venta.getIduseradd(), venta.getIdsucursal(), hoy);
			response.setState("Success");
			response.setMessage("Ventas cargadas exitosamente");
			response.setData(ventasList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListVentaBySucursalAndByVendedor");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + venta.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findVentaById(VentaInv venta) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<VentaInv> ventaFind = ventaInvRepository.findById(venta.getIdventa());
			if(ventaFind.isPresent()) {
				response.setState("Success");
				response.setMessage("Venta encontrada exitosamente");
				response.setData(ventaFind);
			}else {
				response.setState("Error");
				response.setMessage("Venta no pudo ser encontrada");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findVentaById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idprducto: " + venta.getIdventa());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject editVenta(VentaInv venta) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<VentaInv> ventaUpdate = ventaInvRepository.findById(venta.getIdventa());
			if (ventaUpdate.isPresent()) {
				venta.setDatemod(fechaSal.getFecha());
				ventaInvRepository.save(venta);
				response.setState("Success");
				response.setMessage("Venta actualizada exitosamente");
			} else {
				response.setState("Error");
				response.setMessage("Venta no pudo ser actualizada");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("editVenta");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + String.valueOf(venta));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteVentaById(VentaInv venta) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			response = comisionVendedorInvService.getComisionesByIdVenta(venta.getIdventa());
			if(response != null) {
				if(response.getMessage().equals("Comision encontrada exitosamente")) {
					ComisionVendedorInv comisionVendedor = (ComisionVendedorInv) response.getData();
					comisionVendedorInvService.deleteComisionVendedorById(comisionVendedor);
					if(venta.getIdproducto() == 0 && venta.getTipoventa().equals("Reserva")) {
						response.setState("Success");
						response.setMessage("Venta eliminada exitosamente");
					}else {
						Optional<ProductoInv> producto = productoInvRepository.findById(venta.getIdproducto());
						if(producto != null) {
							if(producto.get() != null) {
								int cantidad = producto.get().getExistencia() + venta.getCantidad();
								producto.get().setExistencia(cantidad);
								productoInvRepository.save(producto.get());
								ventaInvRepository.deleteById(venta.getIdventa());
								response.setState("Success");
								response.setMessage("Venta eliminada exitosamente");
							}else {
								response.setState("Error");
								response.setMessage("Venta no pudo ser eliminada");
							}
						}else {
							response.setState("Error");
							response.setMessage("Venta no pudo ser eliminada");
						}
					}					
				}else if(response.getMessage().equals("No existe comision para esta venta")) {
					if(venta.getIdproducto() == 0 && venta.getTipoventa().equals("Reserva")) {
						ventaInvRepository.deleteById(venta.getIdventa());
						response.setState("Success");
						response.setMessage("Venta eliminada exitosamente");
					}else {
						Optional<ProductoInv> producto = productoInvRepository.findById(venta.getIdproducto());
						if(producto != null) {
							if(producto.get() != null) {
								int cantidad = producto.get().getExistencia() + venta.getCantidad();
								producto.get().setExistencia(cantidad);
								productoInvRepository.save(producto.get());
								ventaInvRepository.deleteById(venta.getIdventa());
								response.setState("Success");
								response.setMessage("Venta eliminada exitosamente");
							}else {
								response.setState("Error");
								response.setMessage("Venta no pudo ser eliminada");
							}
						}else {
							response.setState("Error");
							response.setMessage("Venta no pudo ser eliminada");
						}
					}
				}else {
					response.setState("Error");
					response.setMessage("Venta no pudo ser eliminada");
				}
			}else {
				response = new ResponseObject();
				response.setState("Error");
				response.setMessage("Venta no pudo ser eliminada");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteVentaById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idproducto: " + venta.getIdventa());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject entregarReserva(VentaInv venta) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<VentaInv> ventaFind = ventaInvRepository.findById(venta.getIdventa());
			if(ventaFind != null) {
				if(ventaFind.get() != null) {
					ventaFind.get().setEstadoreserva("");
					ventaInvRepository.save(ventaFind.get());
					response.setState("Success");
					response.setMessage("Reserva entregada exitosamente");
				}else {
					response = new ResponseObject();
					response.setState("Error");
					response.setMessage("Reserva no pudo ser entregada");
				}
			}else {
				response = new ResponseObject();
				response.setState("Error");
				response.setMessage("Reserva no pudo ser entregada");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteVentaById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idproducto: " + venta.getIdventa());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject getListVentaMensualBySucursal(VentaInv venta) {
		List<VentaInv> ventasList = new ArrayList<VentaInv>();
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			String[] fecha = venta.getDatemod().split(" ");
			String anio = fecha[1];
			List<Object[]> objeto = ventaInvRepository.getListVentaMensualBySucursal(venta.getIdsucursal(), venta.getDateadd(), anio);
			for (int i = 0; i < objeto.size(); i++) {
				VentaInv ventaObj = new VentaInv();
				Object[] obj = objeto.get(i);
				ventaObj.setPreciototal(obj[0] == null ? 0 : Double.parseDouble(obj[0].toString()));
				ventaObj.setDateadd(obj[1].toString());
				ventasList.add(ventaObj);
			}
			response.setState("Success");
			response.setMessage("Ventas cargadas exitosamente");
			response.setData(ventasList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListVentaBySucursalAndByVendedor");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + venta.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
	
	@Override
	public ResponseObject getVentasReporte(VentaInv venta) {
		List<VentasReporte> ventasReporteList = new ArrayList<VentasReporte>();
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<Object[]> objeto = ventaInvRepository.getVentasReporte(venta.getIdsucursal(), venta.getDateadd(), venta.getDatemod());
			List<Object[]> objeto2 = ventaInvRepository.getVentasReporteProductoNoExiste(venta.getIdsucursal(), venta.getDateadd(), venta.getDatemod());
			for (int i = 0; i < objeto.size(); i++) {
				VentasReporte ventaObj = new VentasReporte();
				Object[] obj = objeto.get(i);
				ventaObj.setIdventa(obj[0] == null ? 0 : Long.parseLong(obj[0].toString()));
				ventaObj.setFactura(obj[1] == null ? "" : obj[1].toString());
				ventaObj.setRecibo(obj[2] == null ? "" : obj[2].toString());
				ventaObj.setNombreproducto(obj[3] == null ? "" : obj[3].toString());
				ventaObj.setCantidad(obj[4] == null ? 0 : Long.parseLong(obj[4].toString()));
				ventaObj.setPreciocosto(obj[5] == null ? 0 : Double.parseDouble(obj[5].toString()));
				ventaObj.setPrecioventa(obj[6] == null ? 0 : Double.parseDouble(obj[6].toString()));
				ventaObj.setGanancia(obj[7] == null ? 0 : Double.parseDouble(obj[7].toString()));
				ventaObj.setVendedor(obj[8] == null ? "" : obj[8].toString());
				ventaObj.setCategoria(obj[9] == null ? "" : obj[9].toString());
				ventaObj.setFechaventa(obj[10] == null ? "" : obj[10].toString());
				ventasReporteList.add(ventaObj);
			}
			for (int i = 0; i < objeto2.size(); i++) {
				VentasReporte ventaObj = new VentasReporte();
				Object[] obj = objeto2.get(i);
				ventaObj.setIdventa(obj[0] == null ? 0 : Long.parseLong(obj[0].toString()));
				ventaObj.setFactura(obj[1] == null ? "" : obj[1].toString());
				ventaObj.setRecibo(obj[2] == null ? "" : obj[2].toString());
				ventaObj.setNombreproducto(obj[3] == null ? "" : obj[3].toString());
				ventaObj.setCantidad(obj[4] == null ? 0 : Long.parseLong(obj[4].toString()));
				ventaObj.setPreciocosto(obj[5] == null ? 0 : Double.parseDouble(obj[5].toString()));
				ventaObj.setPrecioventa(obj[6] == null ? 0 : Double.parseDouble(obj[6].toString()));
				ventaObj.setGanancia(obj[7] == null ? 0 : Double.parseDouble(obj[7].toString()));
				ventaObj.setVendedor(obj[8] == null ? "" : obj[8].toString());
				ventaObj.setCategoria(obj[9] == null ? "" : obj[9].toString());
				ventaObj.setFechaventa(obj[10] == null ? "" : obj[10].toString());
				ventasReporteList.add(ventaObj);
			}
			response.setState("Success");
			response.setMessage("Ventas cargadas exitosamente");
			response.setData(ventasReporteList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getListVentaBySucursalAndByVendedor");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + venta.getIdsucursal());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
}
