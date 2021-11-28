package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.dao.IFacturaDao;
import com.bolsadeideas.springboot.app.models.dao.IProductoDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.Producto;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		
		return (List<Cliente>)clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	public List<Producto> finByNombre(String term) {
		return productoDao.findByNombre(term);
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findFactruaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Factura fetchFacturaByIdWithClienteWithItemFacturaWithProducto(Long id) {
		return facturaDao.fetchByIdWithClienteWithItemFacturaWithProducto(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Factura fetchFacturaByIdWithClienteWithItemFacturaWithProductoEnabled(Long id, Boolean enabled) {
		return facturaDao.fetchByIdWithClienteWithItemFacturaWithProductoEnabled(id, enabled);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente fetchByIdWithFacturas(Long id) {
		return clienteDao.fetchByIdWithFacturas(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cliente fetchByIdWithFacturasEnabled(Long id, Boolean enabled) {
		return clienteDao.fetchByIdWithFacturasEnabled(id, enabled);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Factura> findAllFacturas() {
		return (List<Factura>)facturaDao.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Factura> findAllFacturas(Pageable pageable) {
		return facturaDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Factura fetchFacturaByIdWithItemFacturaWithProducto(Long id) {
		return facturaDao.fetchByIdWithItemFacturaWithProducto(id);
	}
	
	/*@Override
	@Transactional(readOnly = true)
	public Factura fetchFacturaByIdWithItemFacturaWithProductoEnabled(Long id, Boolean enabled) {
		return facturaDao.fetchByIdWithItemFacturaWithProductoEnabled(id, enabled);
	}*/

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAllProductosl() {
		return (List<Producto>)productoDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Producto> findAllProductos(Pageable pageable) {
		return productoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findOneProducto(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	public void saveProducto(Producto producto) {
		productoDao.save(producto);
	}

	@Override
	public void deleteProducto(Long id) {
		productoDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Factura> findAllFacturasHabilitadas(Pageable pageable) {
		return facturaDao.fetchFacturaWhereEnabledTrue(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Factura> findAllFacturasDeshabilitadas(Pageable pageable) {
		return facturaDao.fetchFacturaWhereEnabledFalse(pageable);
	}

	@Override
	public Page<Producto> findByNombreP(String nombre, Pageable pageable) {
		return productoDao.findByNombre(nombre, pageable);
	}



}
