package com.bolsadeideas.springboot.app.models.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {
	
	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")	
	public Cliente fetchByIdWithFacturas(Long id);
	
	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1 and f.enabled=true")	
	public Cliente fetchByIdWithFacturasEnabled(Long id);
	
	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1 and f.enabled=false")	
	public Cliente fetchByIdWithFacturasDisabled(Long id);
	
	
}
