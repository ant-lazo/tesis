package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.PcvIndicador;

public interface IPcvDao extends PagingAndSortingRepository<PcvIndicador, Long>  {

	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN 'true' ELSE 'false' END FROM PcvIndicador p WHERE p.fecha = ?1")
    public Boolean existsByCreateAt(String fecha);
}
