package com.bolsadeideas.springboot.app.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.ItemFactura;
import com.bolsadeideas.springboot.app.models.entity.Producto;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;


@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {
	
	@Autowired
	private IClienteService clienteService;
	
	
	private final Logger log= LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		
		Factura factura= clienteService.fetchFacturaByIdWithClienteWithItemFacturaWithProducto(id);//clienteService.findFactruaById(id);
		
		if(factura==null) {
			
			Factura fac = clienteService.fetchFacturaByIdWithItemFacturaWithProducto(id);
			
			if(fac!=null) {
				
				model.addAttribute("factura", fac);
				model.addAttribute("titulo", "Factura: ".concat(fac.getDescripcion()));
				return "factura/versc";
			}
			
			flash.addFlashAttribute("error", "La factura no existe en la base de datos!");
			return "redirect:/listar";
		}
		
		model.addAttribute("factura", factura);
		model.addAttribute("titulo", "Factura: ".concat(factura.getDescripcion()));
		
		return "factura/ver";
	}
	
	
	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable(value="clienteId") Long clienteId,
			Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente=clienteService.findOne(clienteId);
		
		if(cliente==null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}
		
		Factura factura= new Factura();
		factura.setCliente(cliente);
		
		model.put("factura", factura);
		model.put("titulo","Crear Comprobante");
		
		return "factura/form";
	}
	
	@GetMapping(value="/cargar-productos/{term}",produces= {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term){
		return clienteService.finByNombre(term);
	}
	
	@PostMapping("/form")
	public String guardar(@Valid Factura factura,
			BindingResult result,
			Model model,
			@RequestParam(name="item_id[]",required = false)Long[] itemId,
			@RequestParam(name="cantidad[]",required = false)Integer[] cantidad,
			RedirectAttributes flash,
			SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear Comprobante");
			return "factura/form";
			
		}
		
		if(itemId==null||itemId.length==0) {
			model.addAttribute("titulo", "Crear Comprobante");
			model.addAttribute("error", "Error: El comprobante tiene que estar lleno!");
			return "factura/form";
		}
		
		for(int i=0;i<itemId.length;i++) {
			Producto producto= clienteService.findProductoById(itemId[i]);
			
			ItemFactura linea= new ItemFactura();
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);
			factura.addItemFactura(linea);
			
			log.info("ID: "+ itemId[i].toString() +", CANTIDAD: "+ cantidad[i].toString());
		}
		
		clienteService.saveFactura(factura);
		status.setComplete();
		flash.addFlashAttribute("success", "Comprobante creada con éxito");
		
		return "redirect:/ver/"+ factura.getCliente().getId();
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		Factura factura= clienteService.findFactruaById(id);
		if(factura!=null) {
			clienteService.deleteFactura(id);
			flash.addFlashAttribute("success", "Comprobante eliminado con éxito!");
			return "redirect:/ver/" + factura.getCliente().getId();
		}
		
		flash.addFlashAttribute("error", "El comprobante no exite en la base de datos, no se pudo eliminar!");
		return "redirect:/listar";
		
	}
	
	@RequestMapping(value ="/listarfacturas", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication,
			HttpServletRequest request,
			Locale locale) {
		
		if(authentication!=null) {
			log.info("Hola usuario authenticado, tu username es: ".concat(authentication.getName()));
		}
		
		Authentication auth =SecurityContextHolder.getContext().getAuthentication();

		if(auth!=null) {
			log.info("'Utilizando forma estática' Hola usuario authenticado, tu username es: ".concat(auth.getName()));
		}
		
		if(hasRole("ROLE_ADMIN")) {
			log.info("Hola usuario"+auth.getName().concat(" tienes acceso."));
		}else {
			log.info("Hola usuario"+auth.getName().concat(" No tienes acceso."));
		}
		
		SecurityContextHolderAwareRequestWrapper securityContext= new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
		
		if(securityContext.isUserInRole("ADMIN")) {
			log.info("Hola usuario SecurityContextHolderAwareRequestWrapper: "+auth.getName().concat(" tienes acceso."));
			
		}else {
			log.info("Hola usuario SecurityContextHolderAwareRequestWrapper: "+auth.getName().concat(" No tienes acceso."));

		}
		
		if(request.isUserInRole("ROLE_ADMIN")) {
			log.info("Hola usuario HttpServletRequest: "+auth.getName().concat(" tienes acceso."));
			
		}else {
			log.info("Hola usuario HttpServletRequest: "+auth.getName().concat(" No tienes acceso."));

		}
		
		Pageable pageRequest = PageRequest.of(page, 7,Sort.by(Sort.Direction.DESC,"id"));

		Page<Factura> facturas = clienteService.findAllFacturas(pageRequest);

		PageRender<Factura> pageRender = new PageRender<>("/factura/listarfacturas", facturas);
		model.addAttribute("titulo", messageSource.getMessage("text.factura.listar.titulo", null, locale));
		model.addAttribute("facturas", facturas);
		model.addAttribute("page", pageRender);

		return "factura/listarfacturas";
	}
	
	private boolean hasRole(String role) {
		
		SecurityContext context= SecurityContextHolder.getContext();
		
		if(context == null) {
			return false;
		}
		
		Authentication auth =context.getAuthentication();
		
		if(auth == null) {
			return false;
		}
	
		Collection<? extends GrantedAuthority> authorities= auth.getAuthorities();
	
		return authorities.contains(new SimpleGrantedAuthority(role));
		
		/*for(GrantedAuthority authority: authorities) {
			if(role.equals(authority.getAuthority())) {
				logger.info("Hola usuario: ".concat(auth.getName()).concat(" con el rol: ".concat(authority.getAuthority())));
				return true;
			}
		}
		return false;*/
	}
	
	@GetMapping("/eliminarsc/{id}")
	public String eliminarsc(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		Factura factura= clienteService.findFactruaById(id);
		if(factura!=null) {
			clienteService.deleteFactura(id);
			flash.addFlashAttribute("success", "Comprobante eliminado con éxito!");
			/*return "redirect:/ver/" + factura.getCliente().getId();*/
			return "redirect:/factura/listarfacturas";
		}
		
		flash.addFlashAttribute("error", "El comprobante no exite en la base de datos, no se pudo eliminar!");
		return "redirect:/listar";
		
	}
	
	@GetMapping("/form")
	public String crearsc(Map<String, Object> model) {
		
		Factura factura= new Factura();
		factura.setCliente(null);
		
		model.put("factura", factura);
		model.put("titulo","Crear Comprobante");
		
		return "factura/formsc";
	}
	
	@PostMapping("/formsc")
	public String guardarsc(@Valid Factura factura,
			BindingResult result,
			Model model,
			@RequestParam(name="item_id[]",required = false)Long[] itemId,
			@RequestParam(name="cantidad[]",required = false)Integer[] cantidad,
			RedirectAttributes flash,
			SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear Comprobante");
			return "factura/formsc";
			
		}
		
		if(itemId==null||itemId.length==0) {
			model.addAttribute("titulo", "Crear Comprobante");
			model.addAttribute("error", "Error: El comprobante tiene que estar lleno!");
			return "factura/form";
		}
		
		for(int i=0;i<itemId.length;i++) {
			Producto producto= clienteService.findProductoById(itemId[i]);
			
			ItemFactura linea= new ItemFactura();
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);
			factura.addItemFactura(linea);
			
			log.info("ID: "+ itemId[i].toString() +", CANTIDAD: "+ cantidad[i].toString());
		}
		
		clienteService.saveFactura(factura);
		status.setComplete();
		flash.addFlashAttribute("success", "Comprobante creada con éxito");
		
		return "redirect:/factura/listarfacturas";
	}
	
	
}
