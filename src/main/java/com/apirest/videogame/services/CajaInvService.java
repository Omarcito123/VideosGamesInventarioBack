package com.apirest.videogame.services;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.ICajaInvService;
import com.apirest.videogame.model.CajaInv;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.UserInv;
import com.apirest.videogame.repository.CajaInvRepository;
import com.apirest.videogame.repository.CompraInvRepository;
import com.apirest.videogame.repository.UserInvRepository;
import com.apirest.videogame.repository.VentaInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

@Service
public class CajaInvService implements ICajaInvService {

	@Autowired
	CajaInvRepository cajaInvRepository;

	@Autowired
	IntegrationLogService integrationLogService;
	
	@Autowired
	VentaInvRepository ventaInvRepository;
	
	@Autowired
	UserInvRepository userInvRespository;
	
	@Autowired
	CompraInvRepository compraInvRepository;

	String errorEx = "";

	@Override
	public ResponseObject createCajaInicial(CajaInv caja) {
		ResponseObject response = new ResponseObject();
		CajaInv cajaHoy = new CajaInv();
		CajaInv cajaAyer = new CajaInv();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			String fechaHoy = fechaSal.getFecha();
			LocalDate yesterday = LocalDate.now(ZoneId.of("America/El_Salvador")).minusDays(1);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			String fechaAyer = dtf.format(yesterday);
			cajaHoy = cajaInvRepository.findCajaByDateAndSucursal(caja.getIdsucursal(), fechaHoy);
			cajaAyer = cajaInvRepository.findCajaByDateAndSucursal(caja.getIdsucursal(), fechaAyer);
			if(cajaHoy == null) {
				if(cajaAyer != null) {
					caja.setDateadd(fechaHoy);
					caja.setIniciocaja(cajaAyer.getIniciocaja());
					caja.setMonedas(cajaAyer.getMonedas());
					caja.setFaltante(0);
					caja.setSobrante(0);
					cajaInvRepository.save(caja);			
					response.setState("Success");
					response.setMessage("Registro creado exitosamente");
				}
				else {
					response.setState("Error");
					response.setMessage("Falta la configuracion inicial de la caja");
				}
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createCaja");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("cupon: " + String.valueOf(caja));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findCajasMensuales(CajaInv caja) {
		ResponseObject response = new ResponseObject();
		List<CajaInv> cajasList = new ArrayList<CajaInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			cajasList = (List<CajaInv>) cajaInvRepository.findCajasMensuales(caja.getIdsucursal(), caja.getDateadd());
			response.setState("Success");
			response.setMessage("Registros cargados exitosamente");
			response.setData(cajasList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findCajasMensuales");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + caja.getIdsucursal() + " feha: " + caja.getDateadd());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findCajaByDateAndSucursal(CajaInv caja) {
		ResponseObject response = new ResponseObject();
		CajaInv cajaObj = new CajaInv();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			String fechaHoy = fechaSal.getFechaSimple();
			if(caja.getDateadd() != null && !caja.getDateadd().equals("")) {
				fechaHoy = caja.getDateadd();
			}else {
				fechaHoy = fechaSal.getFechaSimple();
			}
			cajaObj = cajaInvRepository.findCajaByDateAndSucursal(caja.getIdsucursal(), fechaHoy);
			if(cajaObj != null) {
				response.setState("Success");
				response.setMessage("Registro cargado exitosamente");
				response.setData(cajaObj);
			}else {
				cajaObj = cajaInvRepository.findLastCajaBySucursal(caja.getIdsucursal());
				if(cajaObj != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					Date lastDayCaja = sdf.parse(cajaObj.getDateadd());
					Date date2 = sdf.parse(fechaHoy);
					
					long date1InMs = lastDayCaja.getTime();
					long date2InMs = date2.getTime();
					long daysBet = date2InMs - date1InMs;
					int daysDiff = (int) (daysBet / (1000 * 60 * 60* 24));
					CajaInv newCaja = new CajaInv();
					for(int i = 0; i < daysDiff; i++) {
						newCaja = new CajaInv();
						newCaja.setMonedas(cajaObj.getMonedas());
						newCaja.setIniciocaja(cajaObj.getIniciocaja());
						newCaja.setFaltante(0);
						newCaja.setSobrante(0);
						newCaja.setIdsucursal(cajaObj.getIdsucursal());
						newCaja.setIduseradd(cajaObj.getIduseradd());
						newCaja.setIdusermod(0);
						Calendar c = Calendar.getInstance(); 
						c.setTime(lastDayCaja); 
						c.add(Calendar.DATE, i + 1);
						Date dayAddCaja = c.getTime();	
						Format formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						String fechaAdd = formatter.format(dayAddCaja);						
						newCaja.setDateadd(fechaAdd);
						cajaInvRepository.save(newCaja);
					}
					response.setState("Success");
					response.setMessage("Registro cargado exitosamente");
					response.setData(newCaja);
				}else {
					response.setState("Error");
					response.setMessage("Falta la configuracion inicial de la caja");
				}				
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findCajaByDateAndSucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + caja.getIdsucursal() + " feha: " + caja.getDateadd());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject updateCajaIC(CajaInv caja) {
		ResponseObject response = new ResponseObject();
		//List<VentaInv> ventasList = new ArrayList<VentaInv>();
		CajaInv cajaAyer = new CajaInv();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<CajaInv> cajaUpdate = cajaInvRepository.findById(caja.getIdcaja());
			if (cajaUpdate.isPresent()) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				//String now = fechaSal.getFechaCorta();
				LocalDate yesterdayDate = LocalDate.now(ZoneId.of("America/El_Salvador")).minusDays(1);
				String fechaAyer = dtf.format(yesterdayDate);
				cajaAyer = cajaInvRepository.findCajaByDateAndSucursal(caja.getIdsucursal(), fechaAyer);
				if(cajaAyer != null) {
					/*ventasList = ventaInvRepository.getListVentaBySucursal(caja.getIdsucursal(), now);
					double ventasTotal = 0;
					if(ventasList != null) {
						ventasTotal = ventasList.stream().filter(v -> v.getTipopago().equals("Efectivo"))
							      .mapToDouble(o -> o.getPreciototal())
							      .sum();
					}*/
					cajaUpdate.get().setDatemod(fechaSal.getFecha());					
					cajaUpdate.get().setMonedas(cajaAyer.getMonedas());
					cajaUpdate.get().setIdusermod(caja.getIdusermod());
					cajaInvRepository.save(cajaUpdate.get());
					response.setState("Success");
					response.setMessage("Registro actualizado exitosamente");
				}else {
					response.setState("Error");
					response.setMessage("Registro no pudo ser actualizado");
				}
			} else {
				response.setState("Error");
				response.setMessage("Registro no pudo ser actualizado");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("updateCajaIC");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcaja: " + String.valueOf(caja));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject deleteCajaById(CajaInv caja) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			cajaInvRepository.deleteById(caja.getIdcaja());
			response.setState("Success");
			response.setMessage("Registro eliminado exitosamente");
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteCajaById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcaja: " + caja.getIdcaja());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject findCajaByDateAndSucursalAyer(CajaInv caja) {
		ResponseObject response = new ResponseObject();
		CajaInv cajaAyer = new CajaInv();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime yesterday = LocalDateTime.now(ZoneId.of("America/El_Salvador")).minusDays(1);
			String fechaAyer = dtf.format(yesterday);
			cajaAyer = cajaInvRepository.findCajaByDateAndSucursal(caja.getIdsucursal(), fechaAyer);
			response.setState("Success");
			response.setMessage("Registro cargado exitosamente");
			response.setData(cajaAyer);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findCajaByDateAndSucursal");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idsucursal: " + caja.getIdsucursal() + " feha: " + caja.getDateadd());
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject updateCajaFS(CajaInv caja) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<CajaInv> cajaUpdate = cajaInvRepository.findById(caja.getIdcaja());
			if (cajaUpdate.isPresent()) {
				cajaUpdate.get().setDatemod(fechaSal.getFecha());				
				cajaUpdate.get().setSobrante(caja.getSobrante());
				cajaUpdate.get().setFaltante(caja.getFaltante());
				cajaUpdate.get().setIdusermod(caja.getIdusermod());
				cajaInvRepository.save(cajaUpdate.get());
				response.setState("Success");
				response.setMessage("Registro actualizado exitosamente");
				response.setSuccess(true);
			} else {
				response.setState("Error");
				response.setMessage("Registro no pudo ser actualizado");
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("updateCaja");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcaja: " + String.valueOf(caja));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject updateInicioCierreCaja(CajaInv caja) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<UserInv> user = userInvRespository.findById(caja.getIdusermod());
			if(user.isPresent()) {
				Optional<CajaInv> cajaUpdate = cajaInvRepository.findById(caja.getIdcaja());
				if(user.get().getRolname().equals("SuperAdmin") || user.get().getRolname().equals("Administrador")) {
					if (cajaUpdate.isPresent()) {
						cajaUpdate.get().setDatemod(fechaSal.getFecha());				
						cajaUpdate.get().setIniciocaja(caja.getIniciocaja());
						cajaUpdate.get().setMonedas(caja.getMonedas());
						cajaUpdate.get().setIdusermod(caja.getIdusermod());
						cajaInvRepository.save(cajaUpdate.get());
						response.setState("Success");
						response.setMessage("Caja actualizada exitosamente");
						response.setSuccess(true);
					} else {
						response.setState("Error");
						response.setMessage("Registro no pudo ser actualizado");
					}
				}else {
					response.setState("Error");
					response.setMessage("Usuario no permitido para realizar la modificacion en la caja");
				}				
			}else {
				response.setState("Error");
				response.setMessage("Usuario no permitido para realizar la modificacion en la caja");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("updateCaja");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcaja: " + String.valueOf(caja));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject saveInicioCajaMonedas(CajaInv caja) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<UserInv> user = userInvRespository.findById(caja.getIdusermod());
			if(user.isPresent()) {
				Optional<CajaInv> cajaUpdate = cajaInvRepository.findById(caja.getIdcaja());
				if(user.get().getRolname().equals("SuperAdmin") || user.get().getRolname().equals("Administrador")) {
					if (cajaUpdate.isPresent()) {
						cajaUpdate.get().setDatemod(fechaSal.getFecha());				
						cajaUpdate.get().setIniciocaja(caja.getIniciocaja());
						cajaUpdate.get().setMonedas(caja.getMonedas());
						cajaUpdate.get().setIdusermod(caja.getIdusermod());
						cajaInvRepository.save(cajaUpdate.get());
						response.setState("Success");
						response.setMessage("Caja actualizada exitosamente");
						response.setSuccess(true);
					} else {
						caja.setIduseradd(caja.getIdusermod());
						caja.setIdusermod(0);
						caja.setDateadd(fechaSal.getFecha());
						cajaInvRepository.save(caja);			
						response.setState("Success");
						response.setMessage("Registro creado exitosamente");
					}
				}else {
					response.setState("Error");
					response.setMessage("Usuario no permitido para realizar la modificacion en la caja");
				}				
			}else {
				response.setState("Error");
				response.setMessage("Usuario no permitido para realizar la modificacion en la caja");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("updateCaja");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcaja: " + String.valueOf(caja));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}

	@Override
	public ResponseObject saveMonedas(CajaInv caja) {
		ResponseObject response = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<CajaInv> cajaUpdate = cajaInvRepository.findById(caja.getIdcaja());
			if (cajaUpdate.isPresent()) {
				cajaUpdate.get().setDatemod(fechaSal.getFecha());
				cajaUpdate.get().setMonedas(caja.getMonedas());
				cajaUpdate.get().setIdusermod(caja.getIdusermod());
				cajaInvRepository.save(cajaUpdate.get());
				response.setState("Success");
				response.setMessage("Monedas actualizadas exitosamente");
				response.setSuccess(true);
			} else {
				CajaInv newCaja = new CajaInv();
				newCaja.setIduseradd(0);
				newCaja.setIniciocaja(0);
				newCaja.setFaltante(0);
				newCaja.setSobrante(0);
				newCaja.setIdsucursal(caja.getIdsucursal());
				newCaja.setIdusermod(caja.getIdusermod());
				newCaja.setMonedas(caja.getMonedas());
				newCaja.setDateadd(fechaSal.getFecha());
				cajaInvRepository.save(caja);			
				response.setState("Success");
				response.setMessage("Registro creado exitosamente");
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("updateCaja");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("idcaja: " + String.valueOf(caja));
			integrationLogService.save(error);
			response.setState("Error");
			response.setMessage(errorEx);
		}
		return response;
	}
}
