package com.apirest.videogame.repository;

import org.springframework.data.repository.CrudRepository;
import com.apirest.videogame.model.Roles;

public interface RolesRespository extends CrudRepository<Roles, Long>{
}