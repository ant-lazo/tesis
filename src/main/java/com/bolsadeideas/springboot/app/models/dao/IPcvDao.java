package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.PcvIndicador;

public interface IPcvDao extends PagingAndSortingRepository<PcvIndicador, Long>  {

}
