package com.bolsadeideas.springboot.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.app.models.dao.IIpvDao;
import com.bolsadeideas.springboot.app.models.dao.IPcvDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
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
	
	@SuppressWarnings({ "null", "unchecked" })
	@GetMapping(value = "/listaripv")
	public List<IpvIndicador> listarIpvRest() {
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
	
	@GetMapping(value= "/listar")
	public List<Factura> listarRest(){
		return clienteService.findAllFacturas();
	}
	
	@SuppressWarnings({ "null", "unchecked" })
	@GetMapping(value = "/listarpcv")
	public List<PcvIndicador> listarPcvRest() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String hoy= format.format(today);
		Date ayert = new Date(today.getTime() + TimeUnit.DAYS.toMillis(-1));
		String ayer= format.format(ayert);

		PcvIndicador pcv = new PcvIndicador();
		Double vrd = 0.0;
		Double vad = 0.0;
			List<IpvIndicador> listIpv = new ArrayList<IpvIndicador>();
			listIpv = em.createQuery("select i from PcvIndicador i where i.fecha ='"+ayer+"'").getResultList();
			for(IpvIndicador i : listIpv) {
				vad = i.getValorReciente();
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
	

	/*List<Factura> listfactayer = new ArrayList<Factura>();
	listfactayer = em.createQuery("select f from Factura f where f.createAt ='"+ayer+"'").getResultList();
	System.out.println("HOla1");
	if(listfactayer==null || listfactayer.size()==0) {
		pcv.setFecha(ayer);
		pcv.setValorReciente(vrd);
		pcv.setValorAnterior(vad);
		//pcv.setResultado(((vrd/vad)-1)*100);
		pcv.setResultado(vrd-vad);
		pcvservice.save(pcv);
		System.out.println("HOla2");
		return (List<PcvIndicador>) pcvservice.findAll();
	}else {*/
	
}