package com.apirest.videogame.services;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.ICanjesService;
import com.apirest.videogame.model.Canjes;
import com.apirest.videogame.model.CanjesResponse;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.Points;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.CanjesRespository;
import com.apirest.videogame.repository.ErroresRespository;
import com.apirest.videogame.repository.PointsRespository;

@Service
@Transactional
public class CanjesService implements ICanjesService {
	
	@Autowired
    CanjesRespository canjesRespository;
	@Autowired
    PointsRespository pointsRespository;
	@Autowired
	ErroresRespository erroresRespository;

	@Override
	public ResponseObject createCanjes(Canjes canjes) {
		ResponseObject responseObject = new ResponseObject();
		List<Points> pointsList = new ArrayList<Points>();
		Points points = new Points();
		Canjes canjeSave = new Canjes();
		Points puntosSave = new Points();
		try {
			
			pointsList = pointsRespository.pointsByUser(canjes.getIduser(), 1);
			if(pointsList.size() > 0) {
				points = pointsList.get(0);
				if(points.getTotals() >= canjes.getPointsprodct()) {
					Points pointsNew = new Points();
					pointsNew.setIduser(points.getIduser());
					pointsNew.setPoints(-canjes.getPointsprodct());
					pointsNew.setTotals(points.getTotals() - canjes.getPointsprodct());
					pointsNew.setCodefactura("Canje producto");
					pointsNew.setDescription(canjes.getNameproduct());
					pointsNew.setIduseradd(canjes.getIduseradd());
					pointsNew.setDateadd(canjes.getDateadd());
					Calendar cal = Calendar.getInstance();
				    Date date = cal.getTime();
				    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				    String formattedDate = dateFormat.format(date);
				    pointsNew.setDatemod(formattedDate);
					puntosSave = pointsRespository.save(pointsNew);
					
					Points puntosS = pointsRespository.pointsByCanje(pointsNew.getIduser(), pointsNew.getPoints(), pointsNew.getTotals(), pointsNew.getCodefactura(), pointsNew.getDescription(), pointsNew.getIduseradd(), pointsNew.getDateadd(), pointsNew.getDatemod());
					
					canjes.setTotalpointuser(points.getTotals() - canjes.getPointsprodct());
					canjes.setIdpoint(puntosS.getIdpoint());
					canjeSave = canjesRespository.save(canjes);
					
					responseObject.setMessage("Canje realizado exitosamente");
					responseObject.setSuccess(true);
				}else {
					responseObject.setMessage("Canje no realizado por insuficiencia de puntos");
					responseObject.setSuccess(false);
				}
			}
			return responseObject;
		}catch(Exception e) {
			if(canjeSave != null) {
				canjesRespository.deleteById(canjeSave.getIdcanje());
			}
			if(puntosSave != null) {
				pointsRespository.deleteById(puntosSave.getIdpoint());
			}
			responseObject.setMessage("Canje no Realizado: " + e.getMessage());
			responseObject.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("createCanjes");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros(canjes.getNameproduct() + " " + canjes.getDateadd() + " " + canjes.getIdpoint() + " " + canjes.getIdproduct() + " " + canjes.getIduser() + " " + canjes.getIduseradd() + " " + canjes.getPointsprodct() + " " + canjes.getPointuser() + " " + canjes.getTotalpointuser());
			erroresRespository.save(error);
			return responseObject;
		}
	}

	@Override
	public List<Canjes> getCanjes() {
		List<Canjes> canjesList = new ArrayList<Canjes>();
		try {
			return (List<Canjes>) canjesRespository.findAll();
		}catch(Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("getCanjes");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("Simple select");
			erroresRespository.save(error);
			return canjesList;
		}
	}

	@Override
	public List<CanjesResponse> getListCanjes(String idUser, String idRol) {
		List<CanjesResponse> canjesList = new ArrayList<CanjesResponse>();
		List<Object[]> objeto = new ArrayList<Object[]>();
		try {
			if(idRol.equals("1")) {
				objeto = canjesRespository.getListCanjes();
			}else if(idRol.equals("2")){
				long idU = Long.parseLong(idUser);
				objeto = canjesRespository.getListCanjesVendedor(idU);
			} else if(idRol.equals("3")) {
				long idU = Long.parseLong(idUser);
				objeto = canjesRespository.getListCanjesUser(idU);
			}	
			
			for(int i = 0; i < objeto.size(); i++) {
				CanjesResponse point = new CanjesResponse();
				Object[] obj = objeto.get(i);
				point.setIdcanje(obj[0].toString());				
				point.setIduser(obj[1].toString());
				point.setIdproduct(obj[2].toString());
				point.setNameproduct(obj[3].toString());
				point.setPointsprodct(obj[4].toString());
				point.setPointuser(obj[5].toString());
				point.setTotalpointuser(obj[6].toString());
				point.setIduseradd(obj[7].toString());
				point.setDateadd(obj[8].toString());
				point.setUsername(obj[10].toString());
				point.setUseradd(obj[9].toString());
				canjesList.add(point);
			}
			
			return canjesList;
		}catch(Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("getListCanjes");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("idUser: " + idUser + " idUser: " + idRol);
			erroresRespository.save(error);
			return canjesList = new ArrayList<CanjesResponse>();
		}
	}

	@Override
	public Optional<Canjes> findById(long id) {
		Optional<Canjes> canjes = null;
		try {
			return canjesRespository.findById(id);
		}catch(Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("findByIdCanjes");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("id: " + id);
			erroresRespository.save(error);
			return canjes;
		}
	}

	@Override
	public boolean update(Canjes canjes) {
		boolean success = false;
		try {
			canjesRespository.save(canjes);
			return success = true;
		}catch(Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("updateCanjes");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros(canjes.getNameproduct() + " " + canjes.getDateadd() + " " + canjes.getIdpoint() + " " + canjes.getIdproduct() + " " + canjes.getIduser() + " " + canjes.getIduseradd() + " " + canjes.getPointsprodct() + " " + canjes.getPointuser() + " " + canjes.getTotalpointuser());
			erroresRespository.save(error);
			return success;
		}
	}

	@Override
	public ResponseObject deleteCanjesById(long id) {
		ResponseObject responseObject = new ResponseObject();
		try {
			Optional<Canjes> canje = canjesRespository.findById(id);
			if(canje != null) {
				pointsRespository.deleteById(canje.get().getIdpoint());
				canjesRespository.deleteById(id);
				responseObject.setMessage("Canje eliminado exitosamente");
				responseObject.setSuccess(true);
			}			
			return responseObject;
		}catch(Exception e) {
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("deleteCanjesById");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("id: " + id);
			erroresRespository.save(error);
			return responseObject;
		}
	}

}
