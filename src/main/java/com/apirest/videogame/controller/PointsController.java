package com.apirest.videogame.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IPointsService;
import com.apirest.videogame.model.Points;
import com.apirest.videogame.model.PointsResponse;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/points")
public class PointsController {
	@Autowired
    IPointsService pointsService;
	
	@PostMapping("/getAllPoint")
	public Points getAllPoint(@RequestHeader("idUser") String idUser) {
		Points points = new Points();
		points = pointsService.pointsByUser(idUser);
		if(points == null) {
			points = new Points();
			points.setTotals(0);
		}
				
		return points;
	}
	
	@PostMapping("/getPointList")
	public List<PointsResponse> getPointList(@RequestHeader("idUser") String idUser, @RequestHeader("idRol") String idRol) {
		List<PointsResponse> pointsList = new ArrayList<PointsResponse>();
		pointsList = pointsService.getListPoints(idUser, idRol);
		return pointsList;
	}
	
	@PostMapping("/savePoints")
	public ResponseObject savePoints(@RequestBody Points points) {
		return pointsService.createPoints(points);
	}
	
	@PostMapping("/deletePointsById")
	public ResponseObject deletePointsById(@RequestHeader("idpoint") Long idpoint, @RequestHeader("iduseradd") int iduseradd) {
		return pointsService.deletePointsById(idpoint, iduseradd);
	}
}
