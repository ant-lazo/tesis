package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.IpvIndicador;

public interface IIpvDao extends CrudRepository<IpvIndicador, Long> {

}
