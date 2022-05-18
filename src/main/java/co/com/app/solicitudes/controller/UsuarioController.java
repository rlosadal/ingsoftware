package co.com.app.solicitudes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
				
		model.addAttribute("titulo", "Listado de Usuarios");
		model.addAttribute("usuarios", usuarios);
		
		return "listarusuarios";
	}

	@RequestMapping(value = "/usuarioform")
	public String crear(Map<String, Object> model) {

		Usuario usuario = new Usuario();
		
		model.put("usuario", usuario);
		model.put("titulo", "Formulario de Usuario");
		model.put("roles", this.rolService.findAll());
		model.put("areas", this.areaService.findAll());
		model.put("action", "no_readonly");
		
		return "usuarioform";

	}

	@RequestMapping(value = "/usuarioform", method = RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		
		if (result.hasErrors()) {
			
			model.addAttribute("titulo", "Formulario de Usuarios");
			
			return "usuarioform";
		}
		
		Usuario usuarioBD = this.usuarioService.findByUsuario(usuario.getUsuario());
		if (usuarioBD != null) {
			model.addAttribute("error", "El Usuario ya existe, elija otro nombre de usuario!!");
			model.addAttribute("usuario", usuario);
			model.addAttribute("titulo", "Formulario de Usuario");
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
		
		String mensajeFlash = (usuario.getId() != null)? "Usuario editado con éxito" : "Usuario creado con éxito";

		flash.addFlashAttribute("success", mensajeFlash);		
		
		return "redirect:listarusuarios";
	}

	@RequestMapping(value = "/usuarioform/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Usuario usuario = null;

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
		String rol = lista.get(0).getAuthority();
		
		String action = "readonly";
		if(rol.equals("ADMIN")) {
			action = "no_readonly";
		}
								
		model.put("usuario", usuario);
		model.put("titulo", "Editar Usuario");
		model.put("roles", this.rolService.findAll());
		model.put("areas", this.areaService.findAll());
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
		model.put("titulo", "Detalle Usuario: ");
		
		return "verusuario";
	}	

}
