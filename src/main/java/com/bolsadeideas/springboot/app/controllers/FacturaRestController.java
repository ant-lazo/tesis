package com.bolsadeideas.springboot.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.app.models.dao.IIpvDao;
import com.bolsadeideas.springboot.app.models.dao.IPcvDao;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.IpvIndicador;
import com.bolsadeideas.springboot.app.models.entity.PcvIndicador;
import com.bolsadeideas.springboot.app.models.service.IClienteService;

@RestController
@RequestMapping("/api/facturas")
public class FacturaRestController {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IIpvDao ipvservice;
	
	@Autowired
	private IPcvDao pcvservice;
	

	@GetMapping(value= "/listar")
	public List<Factura> listarRest(){
		return clienteService.findAllFacturas();
	}
	
	@SuppressWarnings({ "null", "unchecked" })
	@GetMapping(value = "/ipv")
	public List<IpvIndicador> listarIpvRest() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String hoy= format.format(today);
		IpvIndicador ipv = new IpvIndicador();
		Double vrd = 0.0;
		List<Factura> listfactura = new ArrayList<Factura>();
		listfactura = em.createQuery("select f from Factura f where f.createAt ='"+hoy+"'").getResultList();
		
		if(listfactura==null || listfactura.size()==0) {
			if(ipvservice.existsByCreateAt(hoy)) {
				ipv.setFecha(hoy);
				ipv.setValorReciente(vrd);
				ipv.setResultado(vrd/12);
				ipvservice.save(ipv);
				return (List<IpvIndicador>) ipvservice.findAll();
			}
			return (List<IpvIndicador>) ipvservice.findAll();
		}else{
			for(Factura f : listfactura) {
				vrd+=f.getPreciototal();
			}
			List<IpvIndicador> listIpv = new ArrayList<IpvIndicador>();
			listIpv = em.createQuery("select i from IpvIndicador i where i.fecha ='"+hoy+"'").getResultList();
			Long idflat = null;
			for(IpvIndicador i : listIpv) {
				idflat = i.getId();
			}
			ipv.setId(idflat);
			ipv.setFecha(hoy);
			ipv.setValorReciente(vrd);
			ipv.setResultado(vrd/12);
			ipvservice.save(ipv);
			return (List<IpvIndicador>) ipvservice.findAll();
		}
	}
	
	
	@SuppressWarnings({ "null", "unchecked" })
	@GetMapping(value = "/pcv")
	public List<PcvIndicador> listarPcvRest() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String hoy= format.format(today);
		Date ayert = new Date(today.getTime() + TimeUnit.DAYS.toMillis(-1));
		String ayer= format.format(ayert);
		
		Date manana = new Date(today.getTime() + TimeUnit.DAYS.toMillis(+1));
		String m= format.format(manana);

		PcvIndicador pcv = new PcvIndicador();
		Double vrd = 0.0;
		Double vad = 0.0;
			List<IpvIndicador> listIpv = new ArrayList<IpvIndicador>();
			listIpv = em.createQuery("select i from IpvIndicador i where i.fecha ='"+ayer+"'").getResultList();
			System.out.println("listIpv: "+listIpv);
			for(IpvIndicador i : listIpv) {
				vad = i.getValorReciente();
				System.out.println("vad: "+i.getValorReciente().toString());
			}
			List<Factura> listfactura = new ArrayList<Factura>();
			listfactura = em.createQuery("select f from Factura f where f.createAt ='"+hoy+"'").getResultList();
				if(listfactura==null || listfactura.size()==0) {
					pcv.setFecha(hoy);
					pcv.setValorAnterior(vad);
					pcv.setValorReciente(vrd);
					if(vad<=0) {
						pcv.setResultado(100.0);
					}else{
						pcv.setResultado(((vrd/vad)-1)*100);
					}
					pcvservice.save(pcv);
					System.out.println("HOla3");
					return (List<PcvIndicador>) pcvservice.findAll();
				}else {
					for(Factura f : listfactura) {
						vrd+=f.getPreciototal();
					}
					List<PcvIndicador> listPcv = new ArrayList<PcvIndicador>();
					listPcv = em.createQuery("select i from PcvIndicador i where i.fecha ='"+hoy+"'").getResultList();
					Long idflat = null;
					for(PcvIndicador p : listPcv) {
						idflat = p.getId();
					}
					pcv.setId(idflat);
					pcv.setFecha(hoy);
					pcv.setValorAnterior(vad);
					pcv.setValorReciente(vrd);
					if(vad<=0) {
						pcv.setResultado(100.0);
					}else{
						pcv.setResultado(((vrd/vad)-1)*100);
					}
					pcvservice.save(pcv);
					System.out.println("HOla4");
					return (List<PcvIndicador>) pcvservice.findAll();
				}
		
	}
	
	@SuppressWarnings({ "null", "unchecked" })
	@GetMapping(value = "/ipv/10")
	public List<IpvIndicador> listarIpvRest10(
			@RequestParam(name = "page", defaultValue = "0") int page
			) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String hoy= format.format(today);
		IpvIndicador ipv = new IpvIndicador();
		Double vrd = 0.0;
		List<Factura> listfactura = new ArrayList<Factura>();
		listfactura = em.createQuery("select f from Factura f where f.createAt ='"+hoy+"'").getResultList();
		
		if(listfactura==null || listfactura.size()==0) {
			ipv.setFecha(hoy);
			ipv.setValorReciente(vrd);
			ipv.setResultado(vrd/12);
			ipvservice.save(ipv);
			
			Pageable pageRequest = PageRequest.of(page, 10,Sort.by(Sort.Direction.DESC,"id"));
			Page<IpvIndicador> topPage = ipvservice.findAll(pageRequest);
			List<IpvIndicador> topIpvList = topPage.getContent();
			return topIpvList;
		}else{
			for(Factura f : listfactura) {
				vrd+=f.getPreciototal();
			}
			List<IpvIndicador> listIpv = new ArrayList<IpvIndicador>();
			listIpv = em.createQuery("select i from IpvIndicador i where i.fecha ='"+hoy+"'").getResultList();
			Long idflat = null;
			for(IpvIndicador i : listIpv) {
				idflat = i.getId();
			}
			ipv.setId(idflat);
			ipv.setFecha(hoy);
			ipv.setValorReciente(vrd);
			ipv.setResultado(vrd/12);
			ipvservice.save(ipv);
			
			Pageable pageRequest = PageRequest.of(page, 10,Sort.by(Sort.Direction.DESC,"id"));
			Page<IpvIndicador> topPage = ipvservice.findAll(pageRequest);
			List<IpvIndicador> topIpvList = topPage.getContent();
			return topIpvList;
		}
	}
	
	@SuppressWarnings({ "null", "unchecked" })
	@GetMapping(value = "/pcv/10")
	public List<PcvIndicador> listarPcvRest10(
			@RequestParam(name = "page", defaultValue = "0") int page
			) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String hoy= format.format(today);
		Date ayert = new Date(today.getTime() + TimeUnit.DAYS.toMillis(-1));
		String ayer= format.format(ayert);
		
		Date manana = new Date(today.getTime() + TimeUnit.DAYS.toMillis(+1));
		String m= format.format(manana);

		PcvIndicador pcv = new PcvIndicador();
		Double vrd = 0.0;
		Double vad = 0.0;
			List<IpvIndicador> listIpv = new ArrayList<IpvIndicador>();
			listIpv = em.createQuery("select i from IpvIndicador i where i.fecha ='"+ayer+"'").getResultList();
			System.out.println("listIpv: "+listIpv);
			for(IpvIndicador i : listIpv) {
				vad = i.getValorReciente();
				System.out.println("vad: "+i.getValorReciente().toString());
			}
			List<Factura> listfactura = new ArrayList<Factura>();
			listfactura = em.createQuery("select f from Factura f where f.createAt ='"+hoy+"'").getResultList();
				if(listfactura==null || listfactura.size()==0) {
					pcv.setFecha(hoy);
					pcv.setValorAnterior(vad);
					pcv.setValorReciente(vrd);
					if(vad<=0) {
						pcv.setResultado(100.0);
					}else{
						pcv.setResultado(((vrd/vad)-1)*100);
					}
					pcvservice.save(pcv);
					Pageable pageRequest = PageRequest.of(page, 10,Sort.by(Sort.Direction.DESC,"id"));
					Page<PcvIndicador> topPage = pcvservice.findAll(pageRequest);
					List<PcvIndicador> topPcvList = topPage.getContent();
					return topPcvList;
				}else {
					for(Factura f : listfactura) {
						vrd+=f.getPreciototal();
					}
					List<PcvIndicador> listPcv = new ArrayList<PcvIndicador>();
					listPcv = em.createQuery("select i from PcvIndicador i where i.fecha ='"+hoy+"'").getResultList();
					Long idflat = null;
					for(PcvIndicador p : listPcv) {
						idflat = p.getId();
					}
					pcv.setId(idflat);
					pcv.setFecha(hoy);
					pcv.setValorAnterior(vad);
					pcv.setValorReciente(vrd);
					if(vad<=0) {
						pcv.setResultado(100.0);
					}else{
						pcv.setResultado(((vrd/vad)-1)*100);
					}
					pcvservice.save(pcv);
					
					Pageable pageRequest = PageRequest.of(page, 10,Sort.by(Sort.Direction.DESC,"id"));
					Page<PcvIndicador> topPage = pcvservice.findAll(pageRequest);
					List<PcvIndicador> topPcvList = topPage.getContent();
					return topPcvList;
				}
		
	}
	
	@SuppressWarnings({ "null", "unchecked" })
	@GetMapping(value = "/ipv/14")
	public List<IpvIndicador> listarIpvRest14(
			@RequestParam(name = "page", defaultValue = "0") int page
			) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String hoy= format.format(today);
		IpvIndicador ipv = new IpvIndicador();
		Double vrd = 0.0;
		List<Factura> listfactura = new ArrayList<Factura>();
		listfactura = em.createQuery("select f from Factura f where f.createAt ='"+hoy+"'").getResultList();
		
		if(listfactura==null || listfactura.size()==0) {
			ipv.setFecha(hoy);
			ipv.setValorReciente(vrd);
			ipv.setResultado(vrd/12);
			ipvservice.save(ipv);
			
			Pageable pageRequest = PageRequest.of(page, 14,Sort.by(Sort.Direction.DESC,"id"));
			Page<IpvIndicador> topPage = ipvservice.findAll(pageRequest);
			List<IpvIndicador> topIpvList = topPage.getContent();
			return topIpvList;
		}else{
			for(Factura f : listfactura) {
				vrd+=f.getPreciototal();
			}
			List<IpvIndicador> listIpv = new ArrayList<IpvIndicador>();
			listIpv = em.createQuery("select i from IpvIndicador i where i.fecha ='"+hoy+"'").getResultList();
			Long idflat = null;
			for(IpvIndicador i : listIpv) {
				idflat = i.getId();
			}
			ipv.setId(idflat);
			ipv.setFecha(hoy);
			ipv.setValorReciente(vrd);
			ipv.setResultado(vrd/12);
			ipvservice.save(ipv);
			
			Pageable pageRequest = PageRequest.of(page, 14,Sort.by(Sort.Direction.DESC,"id"));
			Page<IpvIndicador> topPage = ipvservice.findAll(pageRequest);
			List<IpvIndicador> topIpvList = topPage.getContent();
			return topIpvList;
		}
	}
	
	@SuppressWarnings({ "null", "unchecked" })
	@GetMapping(value = "/pcv/14")
	public List<PcvIndicador> listarPcvRest14(
			@RequestParam(name = "page", defaultValue = "0") int page
			) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String hoy= format.format(today);
		Date ayert = new Date(today.getTime() + TimeUnit.DAYS.toMillis(-1));
		String ayer= format.format(ayert);
		
		Date manana = new Date(today.getTime() + TimeUnit.DAYS.toMillis(+1));
		String m= format.format(manana);

		PcvIndicador pcv = new PcvIndicador();
		Double vrd = 0.0;
		Double vad = 0.0;
			List<IpvIndicador> listIpv = new ArrayList<IpvIndicador>();
			listIpv = em.createQuery("select i from IpvIndicador i where i.fecha ='"+ayer+"'").getResultList();
			System.out.println("listIpv: "+listIpv);
			for(IpvIndicador i : listIpv) {
				vad = i.getValorReciente();
				System.out.println("vad: "+i.getValorReciente().toString());
			}
			List<Factura> listfactura = new ArrayList<Factura>();
			listfactura = em.createQuery("select f from Factura f where f.createAt ='"+hoy+"'").getResultList();
				if(listfactura==null || listfactura.size()==0) {
					pcv.setFecha(hoy);
					pcv.setValorAnterior(vad);
					pcv.setValorReciente(vrd);
					if(vad<=0) {
						pcv.setResultado(100.0);
					}else{
						pcv.setResultado(((vrd/vad)-1)*100);
					}
					pcvservice.save(pcv);
					Pageable pageRequest = PageRequest.of(page, 14,Sort.by(Sort.Direction.DESC,"id"));
					Page<PcvIndicador> topPage = pcvservice.findAll(pageRequest);
					List<PcvIndicador> topPcvList = topPage.getContent();
					return topPcvList;
				}else {
					for(Factura f : listfactura) {
						vrd+=f.getPreciototal();
					}
					List<PcvIndicador> listPcv = new ArrayList<PcvIndicador>();
					listPcv = em.createQuery("select i from PcvIndicador i where i.fecha ='"+hoy+"'").getResultList();
					Long idflat = null;
					for(PcvIndicador p : listPcv) {
						idflat = p.getId();
					}
					pcv.setId(idflat);
					pcv.setFecha(hoy);
					pcv.setValorAnterior(vad);
					pcv.setValorReciente(vrd);
					if(vad<=0) {
						pcv.setResultado(100.0);
					}else{
						pcv.setResultado(((vrd/vad)-1)*100);
					}
					pcvservice.save(pcv);
					
					Pageable pageRequest = PageRequest.of(page, 14,Sort.by(Sort.Direction.DESC,"id"));
					Page<PcvIndicador> topPage = pcvservice.findAll(pageRequest);
					List<PcvIndicador> topPcvList = topPage.getContent();
					return topPcvList;
				}
		
	}
	
}
