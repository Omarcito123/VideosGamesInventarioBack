package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.ControlHorasInv;
import com.apirest.videogame.model.ResponseObject;

public interface IControlHorasService {
	public ResponseObject getControlByUserFechaTurno(ControlHorasInv horas);
	public ResponseObject createControl(ControlHorasInv horas);
    public ResponseObject getControlByUserFecha(ControlHorasInv horas);
    public ResponseObject getControlByUserMonth(ControlHorasInv horas);
    public ResponseObject findById(ControlHorasInv horas);
    public ResponseObject update(ControlHorasInv horas);
    public ResponseObject deleteControlById(ControlHorasInv horas);
}