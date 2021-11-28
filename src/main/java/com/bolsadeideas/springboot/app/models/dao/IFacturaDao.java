package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

//import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;

public interface IFacturaDao extends PagingAndSortingRepository<Factura, Long> {

	@Query("select f from Factura f join fetch f.cliente c join fetch f.items l join fetch l.producto where f.id=?1")
	public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);
	
	@Query("select f from Factura f join fetch f.cliente c join fetch f.items l join fetch l.producto where f.id=?1 and f.enabled=?2")
	public Factura fetchByIdWithClienteWithItemFacturaWithProductoEnabled(Long id, Boolean enabled);
	
	@Query("select f from Factura f join fetch f.items l join fetch l.producto where f.id=?1")
	public Factura fetchByIdWithItemFacturaWithProducto(Long id);
	
	/*@Query("select f from Factura f join fetch f.items l join fetch l.producto where f.id=?1 and f.enabled=?2")
	public Factura fetchByIdWithItemFacturaWithProductoEnabled(Long id, Boolean enabled);*/
	
	@Query(value = "select f from Factura f where f.enabled = true")
	public Page<Factura> fetchFacturaWhereEnabledTrue(Pageable pegeable);
	
	@Query(value = "select f from Factura f where f.enabled = false")
	public Page<Factura> fetchFacturaWhereEnabledFalse(Pageable pageable);
	
}
