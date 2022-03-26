package com.apirest.videogame.interfaces;

import java.util.List;
import java.util.Optional;

import com.apirest.videogame.model.Points;
import com.apirest.videogame.model.PointsResponse;
import com.apirest.videogame.model.ResponseObject;

public interface IPointsService {
	public ResponseObject createPoints(Points points);
    public List<Points> getPoints();
    public List<PointsResponse> getListPoints(String idUser, String idRol);
    public Points pointsByUser(String idUser);
    public Optional<Points> findById(long id);
    public boolean update(Points points);
    public ResponseObject deletePointsById(long id, int iduseradd);
}