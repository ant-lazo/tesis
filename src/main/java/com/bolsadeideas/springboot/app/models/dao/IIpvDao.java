package com.bolsadeideas.springboot.app.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.IpvIndicador;

public interface IIpvDao extends PagingAndSortingRepository<IpvIndicador, Long> {
	
	@Query("SELECT CASE WHEN COUNT(i) > 0 THEN 'true' ELSE 'false' END FROM IpvIndicador i WHERE i.fecha = ?1")
    public Boolean existsByCreateAt(String fecha);
}
