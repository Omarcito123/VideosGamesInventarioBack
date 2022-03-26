package com.apirest.videogame.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.ICanjesService;
import com.apirest.videogame.model.Canjes;
import com.apirest.videogame.model.CanjesResponse;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/canjes")
public class CanjesController {
	@Autowired
	ICanjesService canjesService;

	@PostMapping("/getCanjesList")
	public List<CanjesResponse> getPointList(@RequestHeader("idUser") String idUser, @RequestHeader("idRol") String idRol) {
		List<CanjesResponse> canjesList = new ArrayList<CanjesResponse>();
		canjesList = canjesService.getListCanjes(idUser, idRol);
		return canjesList;
	}

	@PostMapping("/saveCanje")
	public ResponseObject saveCanje(@RequestBody Canjes canjes) {
		return canjesService.createCanjes(canjes);
	}
	
	@PostMapping("/deleteCanjesById")
	public ResponseObject deleteCanjesById(@RequestHeader("idcanje") Long idcanje) {
		return canjesService.deleteCanjesById(idcanje);
	}
}