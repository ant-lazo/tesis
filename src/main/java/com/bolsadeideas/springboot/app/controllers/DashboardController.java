package com.bolsadeideas.springboot.app.controllers;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bolsadeideas.springboot.app.models.dao.IIpvDao;
import com.bolsadeideas.springboot.app.models.dao.IPcvDao;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.IpvIndicador;
import com.bolsadeideas.springboot.app.models.entity.PcvIndicador;

import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/dashboard")
@SessionAttributes("dashboard")
public class DashboardController {
	
	@Autowired
	private IIpvDao ipvservice;
	
	@Autowired
	private IPcvDao pcvservice;
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private MessageSource messageSource;
	
	Double resultIpv;
	Double resultPcv;
	
	@GetMapping("/panel")
	public String mostrarDash(Model model, Locale locale) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Date today = new Date();
		String hoy= format.format(today);
		Date ayer1t = new Date(today.getTime() + TimeUnit.DAYS.toMillis(-1));
		String ayer1= format.format(ayer1t);
		
		ipv(hoy);
		pcv(hoy,ayer1);
		
		model.addAttribute("titulo", messageSource.getMessage("text.dashboard.titulo", null, locale));
		model.addAttribute("ipv",transformar(this.resultIpv));
		model.addAttribute("pcv",transformar(this.resultPcv));
		return "dashboard/panel";
	}
	
	@SuppressWarnings("unchecked")
	public void ipv(String fecha) {
		List<Factura> listfactura= new ArrayList<Factura>();
		listfactura = em.createQuery("select f from Factura f where f.createAt ='"+fecha+"'").getResultList();
		Double vrd = 0.0;
		for (Factura f : listfactura) {
			if(f.getPreciototal()!=null) {
				vrd += f.getPreciototal();
			}
		}
		Double ipv =0.0;
		ipv = vrd/12;
		this.resultIpv = ipv;
	}
	
	@SuppressWarnings("unchecked")
	public void pcv(String fecha, String fechaanterior) {
		List<Factura> listfactura= new ArrayList<Factura>();
		listfactura = em.createQuery("select f from Factura f where f.createAt ='"+fecha+"'").getResultList();
		Double vrd = 0.0;
		for (Factura f : listfactura) {
			if(f.getPreciototal()!=null) {
				vrd += f.getPreciototal();
			}
		}
		List<Factura> listfactayer= new ArrayList<Factura>();
		listfactayer = em.createQuery("select f from Factura f where f.createAt ='"+fechaanterior+"'").getResultList();
		Double vad = 0.0;
		for (Factura f : listfactayer) {
			if(f.getPreciototal()!=null) {
				vad += f.getPreciototal();
			}
		}
		Double pcv=0.0;
		if(vad==0) {
			pcv=100.0;
			this.resultPcv=pcv;
		}else {
			pcv=((vrd/vad)-1)*100;
			this.resultPcv=pcv;
		}
	}
	
	public String transformar(double valor){
		DecimalFormat f = new DecimalFormat("#.00");
		String salida=f.format(valor);
		return salida;
	}
	
	@GetMapping("/ipv")
	public String mostrarIpv(Model model,@RequestParam(name = "page", defaultValue = "0") int page) {
		Pageable pageRequest = PageRequest.of(page, 14,Sort.by(Sort.Direction.DESC,"id"));
		Page<IpvIndicador> topPage = ipvservice.findAll(pageRequest);
		List<IpvIndicador> topIpvList = topPage.getContent();
		model.addAttribute("ipv",topIpvList);
		return "dashboard/ipv";
	}
	
	@GetMapping("/pcv")
	public String mostrarPcv(Model model,@RequestParam(name = "page", defaultValue = "0") int page) {
		Pageable pageRequest = PageRequest.of(page, 14,Sort.by(Sort.Direction.DESC,"id"));
		Page<PcvIndicador> topPage = pcvservice.findAll(pageRequest);
		List<PcvIndicador> topPcvList = topPage.getContent();
		model.addAttribute("pcv",topPcvList);
		return "dashboard/pcv";
	}
	
	@GetMapping("/ipvpre")
	public String mostrarIpvpre() {
		return "dashboard/ipvpre";
	}
	
	@GetMapping("/pcvpre")
	public String mostrarPcvpre() {
		return "dashboard/pcvpre";
	}

}
