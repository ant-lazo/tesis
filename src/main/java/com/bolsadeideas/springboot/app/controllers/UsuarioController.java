package com.bolsadeideas.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.binary.StringUtils;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.bolsadeideas.springboot.app.models.entity.Producto;
import com.bolsadeideas.springboot.app.models.entity.Role;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.models.service.IRoleService;
import com.bolsadeideas.springboot.app.models.service.IUploadFileService;
import com.bolsadeideas.springboot.app.models.service.JpaUserDetailsService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/usuario")
@SessionAttributes("usuario")
public class UsuarioController {
	
	@Autowired
	private JpaUserDetailsService usuarioService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IRoleService roleService;
	
	private final Logger log= LoggerFactory.getLogger(getClass());
	
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
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value ="/listarusuarios", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication,
			HttpServletRequest request,
			Locale locale) {
		
		if(authentication!=null) {
			log.info("Hola usuario authenticado, tu username es: ".concat(authentication.getName()));
		}
		
		Authentication auth =SecurityContextHolder.getContext().getAuthentication();

		if(auth!=null) {
			log.info("'Utilizando forma est??tica' Hola usuario authenticado, tu username es: ".concat(auth.getName()));
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
		
		Pageable pageRequest = PageRequest.of(page, 3,Sort.by(Sort.Direction.DESC,"id"));

		Page<Usuario> usuarios = usuarioService.findAllUsuarios(pageRequest);

		PageRender<Usuario> pageRender = new PageRender<>("/usuario/listarusuarios", usuarios);
		model.addAttribute("titulo", messageSource.getMessage("text.usuario.listar.titulo", null, locale));
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("page", pageRender);

		return "usuario/listarusuarios";
	}
	
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Formulario Usuario");
		return "usuario/form";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = null;
		if (id > 0) {
			usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "El id del usuario no ??xite en la base de datos");
				return "redirect:/listarusuarios";
			}
		} else {
			flash.addFlashAttribute("error", "El id del usuario no puede ser cero");
			return "redirect:/listarusuarios";
		}
		model.put("titulo", "Editar Usuario");
		model.put("usuario", usuario);
		return "usuario/formedt";
	}
	
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario Usuario");
			return "usuario/form";
		}

		if (!foto.isEmpty()) {

			if (usuario.getId() != null && usuario.getId() > 0 && usuario.getFoto() != null
					&& usuario.getFoto().length() > 0) {

				uploadFileService.delete(usuario.getFoto());

			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + foto.getOriginalFilename() + "'");
			usuario.setFoto(uniqueFilename);
		}

		String mensajeflash = (usuario.getId() != null) ? "Usuario editado con ??xito" : "Usuario creado con ??xito";
		
		if(usuarioService.existsByUsername(usuario.getUsername())) {
			model.addAttribute("titulo", "Formulario Usuario");
			model.addAttribute("error", "El username ingresado ya existe en nuestros registros");
			return "usuario/form";
		}
		
		//usuario.setEnabled(true);
		System.out.println(usuario.getEnabled());
		String encryppass =passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(encryppass);
		
		/*foto nulos a vacios*/
		
		if(usuario.getFoto()==null) {
			usuario.setFoto("");
		}
		
		Role rol = new Role();
		rol.setAuthority("ROLE_USER");
		List<Role> roles = new ArrayList<>();
		roles.add(rol);
		usuario.setRoles(roles);
		usuarioService.saveUsuario(usuario);
		
		//Role rol = new Role();
		/*rol.setId(usuario.getId()+1);
		rol.setAuthority("ROLE_USER");
		System.out.println("el roleid: "+rol.getId());
		System.out.println("el authority: "+rol.getAuthority());*/
		
		
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeflash);
		return "redirect:listarusuarios";
	}
	
	@RequestMapping(value = "/formedt", method = RequestMethod.POST)
	public String guardaredt(@Valid Usuario usuario, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario Usuario");
			return "usuario/form";
		}

		if (!foto.isEmpty()) {

			if (usuario.getId() != null && usuario.getId() > 0 && usuario.getFoto() != null
					&& usuario.getFoto().length() > 0) {

				uploadFileService.delete(usuario.getFoto());

			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + foto.getOriginalFilename() + "'");
			usuario.setFoto(uniqueFilename);
		}

		String mensajeflash = (usuario.getId() != null) ? "Usuario editado con ??xito" : "Usuario creado con ??xito";
		
		/*if(usuarioService.existsByUsername(usuario.getUsername())) {
			model.addAttribute("titulo", "Formulario Usuario");
			model.addAttribute("error", "El username ingresado ya existe en nuestros registros");
			return "usuario/form";
		}*/
		
		//usuario.setEnabled(true);
		System.out.println(usuario.getEnabled());
		String encryppass =passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(encryppass);
		
		/*foto nulos a vacios*/
		
		if(usuario.getFoto()==null) {
			usuario.setFoto("");
		}
		
		Role rol = new Role();
		rol.setAuthority("ROLE_USER");
		List<Role> roles = new ArrayList<>();
		roles.add(rol);
		usuario.setRoles(roles);
		usuarioService.saveUsuario(usuario);
		
		//Role rol = new Role();
		/*rol.setId(usuario.getId()+1);
		rol.setAuthority("ROLE_USER");
		System.out.println("el roleid: "+rol.getId());
		System.out.println("el authority: "+rol.getAuthority());*/
		
		
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeflash);
		return "redirect:listarusuarios";
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
	
	/*@InitBinder
	public void vaciosComoNulos(WebDataBinder binder) {
		StringTrimmerEditor recortarEspacios= new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, recortarEspacios);
	}*/
	
}
