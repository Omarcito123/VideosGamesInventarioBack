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

import com.apirest.videogame.interfaces.IPointsService;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.Points;
import com.apirest.videogame.model.PointsResponse;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.Users;
import com.apirest.videogame.repository.ErroresRespository;
import com.apirest.videogame.repository.PointsRespository;
import com.apirest.videogame.repository.UserRespository;

@Service
@Transactional
public class PointsService implements IPointsService {

	@Autowired
	PointsRespository pointsRespository;
	@Autowired
	UserRespository usersRespository;
	@Autowired
	ErroresRespository erroresRespository;

	@Override
	public ResponseObject createPoints(Points points) {
		ResponseObject responseObject = new ResponseObject();
		List<Points> pointsList = new ArrayList<Points>();
		try {
			Optional<Users> user = usersRespository.findById(points.getIduser());
			if (user != null) {
				String bornUser = user.get().getDateborn();
				// String bornUserPoints = points.getDateadd();
				String mesUSer = bornUser.split("/")[1];
				Date date = Calendar.getInstance().getTime();
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String today = formatter.format(date);
				String mesUserBorn = today.split("/")[1];
				if (mesUSer.equals(mesUserBorn)) {
					points.setPoints(points.getPoints() * 2);
					points.setDescription("Puntos dobles: " + points.getDescription());
				}
			}
			int totalPuntos = points.getPoints();
			pointsList = pointsRespository.pointsByUser(points.getIduser(), 1);
			if (pointsList.size() > 0) {
				points.setTotals(totalPuntos + pointsList.get(0).getTotals());
			} else {
				points.setTotals(totalPuntos);
			}

			pointsRespository.save(points);
			responseObject.setMessage("Puntos agregados exitosamente");
			responseObject.setSuccess(true);
			return responseObject;
		} catch (Exception e) {
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("createPoints");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros(points.getCodefactura() + " " + points.getDateadd() + " " + points.getDescription() + " " + points.getIduseradd() + " " + points.getPoints() + " " + points.getTotals());
			erroresRespository.save(error);
			return responseObject;
		}
	}

	@Override
	public Points pointsByUser(String idUser) {
		List<Points> pointsList = new ArrayList<Points>();
		Points points = new Points();
		try {
			long idU = Long.parseLong(idUser);
			pointsList = pointsRespository.pointsByUser(idU, 1);
			if (pointsList.size() > 0) {
				points = pointsList.get(0);
			}
			return points;
		} catch (Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("pointsByUser");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("idUser: " + idUser);
			erroresRespository.save(error);
			return points;
		}
	}

	@Override
	public List<PointsResponse> getListPoints(String idUser, String idRol) {
		List<PointsResponse> pointsList = new ArrayList<PointsResponse>();
		List<Object[]> objeto = new ArrayList<Object[]>();
		try {
			if (idRol.equals("1")) {
				objeto = pointsRespository.getListPoints();
			} else if (idRol.equals("2")) {
				long idU = Long.parseLong(idUser);
				objeto = pointsRespository.getListPointsVendedor(idU);
			} else if (idRol.equals("3")) {
				long idU = Long.parseLong(idUser);
				objeto = pointsRespository.getListPointsUser(idU);
			}

			for (int i = 0; i < objeto.size(); i++) {
				PointsResponse point = new PointsResponse();
				Object[] obj = objeto.get(i);
				point.setIdpoint(obj[0].toString());
				point.setUsername(obj[1].toString());
				point.setPoints(obj[2].toString());
				point.setTotals(obj[3].toString());
				point.setCodefactura(obj[4].toString());
				point.setDescription(obj[5].toString());
				point.setUseradd(obj[6].toString());
				point.setDateadd(obj[7].toString());
				pointsList.add(point);
			}

			return pointsList;
		} catch (Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("getListPoints");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("idUser: " + idUser + " idRol " + idRol);
			erroresRespository.save(error);
			return pointsList = new ArrayList<PointsResponse>();
		}
	}

	@Override
	public boolean update(Points points) {
		boolean success = false;
		try {
			pointsRespository.save(points);
			return success = true;
		} catch (Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("updatePoints");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("idUser: ");
			erroresRespository.save(error);
			return success;
		}
	}

	@Override
	public ResponseObject deletePointsById(long id, int iduseradd) {
		ResponseObject responseObject = new ResponseObject();
		Points points = new Points();
		Points newPoints = new Points();
		try {
			Optional<Points> pointsDelete = pointsRespository.findById(id);			
			if (pointsDelete != null) {
				List<Points> pointsList = pointsRespository.pointsByUser(pointsDelete.get().getIduser(), 1);
				if (pointsList.size() > 0) {
					points = pointsList.get(0);
					int totalPoints = points.getTotals();
					int deletePoints = pointsDelete.get().getPoints();
					int restantes = totalPoints - deletePoints;
					if (restantes >= 0) {
						newPoints.setIduser(points.getIduser());
						newPoints.setPoints(-deletePoints);
						newPoints.setTotals(restantes);
						newPoints.setCodefactura("Eliminacion de puntos");
						newPoints.setDescription(pointsDelete.get().getDescription());
						newPoints.setIduseradd(iduseradd);
						Calendar cal = Calendar.getInstance();
						Date date = cal.getTime();
						DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
						DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
						String formattedDate = dateFormat.format(date);
						String formattedDate2 = dateFormat2.format(date);
						newPoints.setDateadd(formattedDate2);
						newPoints.setDatemod(formattedDate);
						pointsRespository.save(newPoints);

						pointsRespository.deleteById(id);
						responseObject.setMessage("Puntos eliminados exitosamente");
						responseObject.setSuccess(true);
					} else {
						responseObject.setMessage("No puedes eliminar estos puntos porque ya fueron utilizados");
						responseObject.setSuccess(false);
					}
				} else {
					responseObject.setMessage("Puntos no eliminados");
					responseObject.setSuccess(false);
				}
			} else {
				responseObject.setMessage("Puntos no eliminados");
				responseObject.setSuccess(false);
			}

			return responseObject;
		} catch (Exception e) {
			Points puntosS = pointsRespository.pointsByCanje(newPoints.getIduser(), newPoints.getPoints(),
					newPoints.getTotals(), newPoints.getCodefactura(), newPoints.getDescription(),
					newPoints.getIduseradd(), newPoints.getDateadd(), newPoints.getDatemod());
			pointsRespository.deleteById(puntosS.getIdpoint());
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("deletePointsById");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("id: " + id + " iduseradd " + iduseradd);
			erroresRespository.save(error);
			return responseObject;
		}
	}

	@Override
	public List<Points> getPoints() {
		List<Points> pointsList = new ArrayList<Points>();
		try {
			return (List<Points>) pointsRespository.findAll();
		} catch (Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("getPoints");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("Simple select");
			erroresRespository.save(error);
			return pointsList;
		}
	}

	@Override
	public Optional<Points> findById(long id) {
		Optional<Points> points = null;
		try {
			return pointsRespository.findById(id);
		} catch (Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("findByIdPoints");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("id: " + id);
			erroresRespository.save(error);
			return points;
		}
	}
}
