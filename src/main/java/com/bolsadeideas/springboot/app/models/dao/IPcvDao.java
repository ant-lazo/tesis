package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.PcvIndicador;

public interface IPcvDao extends CrudRepository<PcvIndicador, Long>  {

}
