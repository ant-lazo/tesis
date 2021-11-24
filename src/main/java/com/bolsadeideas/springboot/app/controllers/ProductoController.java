package com.bolsadeideas.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.Producto;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.models.service.IUploadFileService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

//@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/producto")
@SessionAttributes("producto")
public class ProductoController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	private final Logger log= LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MessageSource messageSource;
	
	@Secured("ROLE_USER")
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);

	}
	
	@Secured("ROLE_USER")
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Producto producto = clienteService.findOneProducto(id);//clienteService.findOne(id);
		if (producto == null) {
			flash.addFlashAttribute("error", "El producto no existe en la base de datos");
			return "redirect:/listarproductos";
		}

		model.put("producto", producto);
		model.put("titulo", "Detalle producto: " + producto.getNombre());
		
		return "producto/ver";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value ="/listarproductos", method = RequestMethod.GET)
	public String listar( Model model,
			@RequestParam(name = "page", defaultValue = "0", required=true) int page,
			@Param("nombre") Optional<String> nombre,
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

		//Page<Producto> productos = clienteService.findAllProductos(pageRequest);
		Page<Producto> productos = clienteService.findByNombreP(nombre.orElse("_"),pageRequest);
		List<Producto> lproductos = clienteService.findAllProductosl();
		
		
		String nombres = nombre.stream()
        .filter(x -> x.length() == 1)
        .findFirst()
        .map(Object::toString)
        .orElse("");

		PageRender<Producto> pageRender = new PageRender<>("/producto/listarproductos", productos);
		model.addAttribute("titulo", messageSource.getMessage("text.producto.listar.titulo", null, locale));
		model.addAttribute("productos", productos);
		model.addAttribute("page", pageRender);
		model.addAttribute("pagep", page);
		model.addAttribute("nombre", nombres);
		model.addAttribute("lproductos", lproductos);

		return "producto/listarproductos";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Producto producto = new Producto();
		model.put("producto", producto);
		model.put("titulo", "Formulario Producto");
		return "producto/form";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Producto producto = null;
		if (id > 0) {
			producto = clienteService.findOneProducto(id);
			if (producto == null) {
				flash.addFlashAttribute("error", "El id del producto no éxite en la base de datos");
				return "redirect:/listarproductos";
			}
		} else {
			flash.addFlashAttribute("error", "El id del producto no puede ser cero");
			return "redirect:/listarproductos";
		}
		model.put("titulo", "Editar Producto");
		model.put("producto", producto);
		return "producto/form";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Producto producto, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		System.out.println("holaaaa");
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario Producto");
			return "producto/form";
		}

		if (!foto.isEmpty()) {

			if (producto.getId() != null && producto.getId() > 0 && producto.getFoto() != null
					&& producto.getFoto().length() > 0) {

				uploadFileService.delete(producto.getFoto());

			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + foto.getOriginalFilename() + "'");
			producto.setFoto(uniqueFilename);
		}

		String mensajeflash = (producto.getId() != null) ? "Producto editado con éxito" : "Producto creado con éxito";

		clienteService.saveProducto(producto);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeflash);
		return "redirect:listarproductos";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Producto producto = clienteService.findOneProducto(id);
			clienteService.deleteProducto(id);
			flash.addFlashAttribute("success", "Producto eliminado con éxito");

			if (uploadFileService.delete(producto.getFoto())) {
				flash.addFlashAttribute("info", "Foto" + producto.getFoto() + " eliminada con exito");
			}
		}

		return "redirect:/producto/listarproductos";
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
	
	@InitBinder
	public void vaciosComoNulos(WebDataBinder binder) {
		StringTrimmerEditor recortarEspacios= new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, recortarEspacios);
	}
	
}
