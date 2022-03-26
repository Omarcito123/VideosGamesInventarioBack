package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.NotasInv;
import com.apirest.videogame.model.ResponseObject;

public interface INotasService {
	public ResponseObject createNota(NotasInv nota);
    public ResponseObject getNotasListByUserAndDates(NotasInv nota);
    public ResponseObject findNotaById(NotasInv nota);
    public ResponseObject editNota(NotasInv nota);
    public ResponseObject deleteNotaById(NotasInv nota);
}