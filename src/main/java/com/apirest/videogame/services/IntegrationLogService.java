package com.apirest.videogame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.model.Errores;
import com.apirest.videogame.repository.ErroresRespository;

@Service
public class IntegrationLogService {
	
	private final ErroresRespository erroresRespository;

    @Autowired
    public IntegrationLogService(ErroresRespository erroresRespository) {
        this.erroresRespository = erroresRespository;
    }

    public void save(Errores log) {
        this.erroresRespository.save(log);
    }
}