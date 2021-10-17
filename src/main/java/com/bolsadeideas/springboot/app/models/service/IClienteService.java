package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.Producto;

public interface IClienteService {
	public List<Cliente> findAll();
	public Page<Cliente> findAll(Pageable pageable);
	public void save(Cliente cliente);
	public Cliente findOne(Long id);
	public void delete(Long id);
	public List<Producto> finByNombre(String term);
	public void saveFactura (Factura factura);
	public Producto findProductoById(Long id);
	public Factura findFactruaById(Long id);
	public void deleteFactura(Long id);
	public Factura fetchFacturaByIdWithClienteWithItemFacturaWithProducto(Long id);
	public Cliente fetchByIdWithFacturas(Long id);
	/*implementaciones mikarfarma facturas*/
	public List<Factura> findAllFacturas();
	public Page<Factura> findAllFacturas(Pageable pageable);
	public Factura fetchFacturaByIdWithItemFacturaWithProducto(Long id);
	/*implementaciones mikarfarma productos*/
	public List<Producto> findAllProductosl();
	public Page<Producto> findAllProductos(Pageable pageable);
	public Producto findOneProducto(Long id);
	public void saveProducto(Producto producto);
	public void deleteProducto(Long id);
}
