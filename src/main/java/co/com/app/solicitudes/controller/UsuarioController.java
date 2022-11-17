package co.com.app.solicitudes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.com.app.solicitudes.entity.Area;
import co.com.app.solicitudes.entity.Role;
import co.com.app.solicitudes.entity.Usuario;
import co.com.app.solicitudes.service.AreaService;
import co.com.app.solicitudes.service.RoleService;
import co.com.app.solicitudes.service.UsuariosService;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {

	@Autowired
	private UsuariosService usuarioService;
	
	@Autowired
	private RoleService rolService;
	
	@Autowired
	private AreaService areaService;
	
	
		
	@RequestMapping(value = "/listarusuarios", method = RequestMethod.GET)
	public String listar(Model model) {
		
		List<Usuario> usuarios = new ArrayList<>();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogueado = this.usuarioService.findByUsuario(authentication.getName());
		
		if("ADMIN".equals(usuarioLogueado.getRole().getRole())) {
			usuarios = this.usuarioService.findAll();
		} else {
			usuarios.add(usuarioLogueado);
		}
				
		model.addAttribute("titulo", "USUARIOS DEL SISTEMA");
		model.addAttribute("usuarios", usuarios);
		
		return "listarusuarios";
	}

	@RequestMapping(value = "/usuarioform")
	public String crear(Map<String, Object> model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogueado = this.usuarioService.findByUsuario(authentication.getName());
		Usuario usuario = new Usuario();
		List<Area> areas = new ArrayList<>();
	   	    		
		if("ADMIN".equals(usuarioLogueado.getRole().getRole())) {
	    	areas = this.areaService.findByNombre("SISTEMAS");
	    } 
	    	                
	    model.put("usuario", usuario);
		model.put("titulo", "FORMULARIO DE USUARIO");
		model.put("roles", this.rolService.findAll());
		model.put("areas", areas);
		model.put("action", "no_readonly");
		
		return "usuarioform";

	}

	@RequestMapping(value = "/usuarioform", method = RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		
		if (result.hasErrors()) {
			
			model.addAttribute("titulo", "FORMULARIO DE USUARIO");
			
			return "usuarioform";
		}
		
		Usuario usuarioBD = this.usuarioService.findByUsuario(usuario.getUsuario());
		if (usuarioBD != null) {
			model.addAttribute("error", "El Usuario ya existe, elija otro nombre de usuario!!");
			model.addAttribute("usuario", usuario);
			model.addAttribute("titulo", "FORMULARIO DE USUARIO");
			model.addAttribute("roles", this.rolService.findAll());
			model.addAttribute("areas", this.areaService.findAll());
			//si es alta de usuario
			if (usuario.getId() == null) {
					return "usuarioform";

			} else {
				//el editar el nombre de un usuario
				if (usuario.getId().longValue() != usuarioBD.getId().longValue()) {
					return "usuarioform";
				}
			}
		}
								
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(usuario.getPassword());
		
		usuario.setPassword(encodedPassword);
		usuario.setNombreCompleto(usuario.getApellido()+", "+usuario.getNombres());
		
		usuarioService.save(usuario);
		
		status.setComplete();
		
		String mensajeFlash = (usuario.getId() != null)? "Usuario guardado con éxito" : "Usuario guardado con éxito";

		flash.addFlashAttribute("success", mensajeFlash);		
		
		return "redirect:listarusuarios";
	}

	@RequestMapping(value = "/usuarioform/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = null;
		List<Area> areas = new ArrayList<>();

		if (id > 0) {
			usuario = usuarioService.findById(id);
			if(usuario == null) {
				flash.addFlashAttribute("error", "El Usuario no existe en la BBDD!");
				return "redirect:/listarusuarios";
			}			
		} else {
			flash.addFlashAttribute("error", "El Usuario creado no puede ser cero!");
			return "redirect:/listarusuarios";
		}
		
		//blanqueo el password
		usuario.setPassword("");
				
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<SimpleGrantedAuthority> lista = (List<SimpleGrantedAuthority>)authentication.getAuthorities();
		String role = lista.get(0).getAuthority();
						
		String action = "readonly";
		
		if(role.equals("ADMIN")) {
			action = "no_readonly";
			areas = this.getFilterAreas(usuario.getRole().getRole());
		} else {
			areas = this.areaService.findAll();
		}
								
		model.put("usuario", usuario);
		model.put("titulo", "EDITAR USUARIO");
		model.put("roles", this.rolService.findAll());
		model.put("areas", areas);
		model.put("action", action);
		
		return "usuarioform";
	}

	@RequestMapping(value = "/eliminarusuario/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			this.usuarioService.delete(id);
			flash.addFlashAttribute("success", "Usuario eliminado con éxito!");			
		}
		return "redirect:/listarusuarios";

	}
	
	@GetMapping(value="/verusuario/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Usuario usuario = usuarioService.findById(id);
		if(usuario==null) {
			flash.addFlashAttribute("error","El Usuario no existe en la BBDD");
			return "redirect:/listarusuarios";
		}
		
		model.put("usuario", usuario);
		model.put("titulo", "DETALLE DE USUARIO: ");
		
		return "verusuario";
	}
	
	@GetMapping(value = "/areas")
	public @ResponseBody List<Area> findAreas(@RequestParam(value = "roleId", required = true) Long roleId) {
		
		Role role = this.rolService.findById(roleId);
		List<Area> areas = this.getFilterAreas(role.getRole());
	        
	    return areas;
	}
	
	private List<Area> getFilterAreas(String role){
		
		List<Area> areas = new ArrayList<>();
	    	    
	    if("ADMIN".equals(role)) {
	    	areas = this.areaService.findByNombre("SISTEMAS");
	    } else if("HELP_DESK".equals(role)){
	    	areas = this.areaService.findByNombre("CALL_CENTER");
	    } else if("SUPPORT".equals(role)) {
	    	areas = this.areaService.findByNombre("TECNOLOGIA");
	    } else {
	    	List<Area> tmp = this.areaService.findAll();
	    	// filter 1
	        Predicate<Area> noSistemas = area -> !area.getNombre().contains("SISTEMAS");
	        // filter 2
	        Predicate<Area> noCallCenter = area -> !area.getNombre().contains("CALL_CENTER");
	        // filter 3
	        Predicate<Area> noTecnologia = area -> !area.getNombre().contains("TECNOLOGIA");

	        areas = tmp.stream().filter(noSistemas)
	                .filter(noCallCenter).filter(noTecnologia).collect(Collectors.toList());
	    }
	    
	    return areas;
		
	}
	
}
