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
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bolsadeideas.springboot.app.models.entity.Factura;

import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/dashboard")
@SessionAttributes("dashboard")
public class DashboardController {
	
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
		System.out.println("hoy :"+hoy);
		Date ayer1t = new Date(today.getTime() + TimeUnit.DAYS.toMillis(-1));
		String ayer1= format.format(ayer1t);
		System.out.println("ayer1 :"+ayer1);
		Date ayer2t = new Date(today.getTime() + TimeUnit.DAYS.toMillis(-2));
		String ayer2= format.format(ayer2t);
		System.out.println("ayer2 :"+ayer2);
		Date ayer3t = new Date(today.getTime() + TimeUnit.DAYS.toMillis(-3));
		String ayer3= format.format(ayer3t);
		System.out.println("ayer3 :"+ayer3);
		Date ayer4t = new Date(today.getTime() + TimeUnit.DAYS.toMillis(-4));
		String ayer4= format.format(ayer4t);
		System.out.println("ayer4 :"+ayer4);
		Date ayer5t = new Date(today.getTime() + TimeUnit.DAYS.toMillis(-5));
		String ayer5= format.format(ayer5t);
		System.out.println("ayer5 :"+ayer5);
		Date ayer6t = new Date(today.getTime() + TimeUnit.DAYS.toMillis(-6));
		String ayer6= format.format(ayer6t);
		System.out.println("ayer6 :"+ayer6);
		Date ayer7t = new Date(today.getTime() + TimeUnit.DAYS.toMillis(-7));
		String ayer7= format.format(ayer7t);
		System.out.println("ayer7 :"+ayer7);
		
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

}
