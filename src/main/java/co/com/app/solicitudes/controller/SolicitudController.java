package co.com.app.solicitudes.controller;

import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import co.com.app.solicitudes.entity.Equipo;
import co.com.app.solicitudes.entity.EstadoSolicitud;
import co.com.app.solicitudes.entity.Solicitud;
import co.com.app.solicitudes.entity.TipoSolicitud;
import co.com.app.solicitudes.entity.Usuario;
import co.com.app.solicitudes.service.EstadoSolicitudService;
import co.com.app.solicitudes.service.SolicitudService;
import co.com.app.solicitudes.service.TipoSolicitudService;
import co.com.app.solicitudes.service.UsuariosService;
import co.com.app.solicitudes.util.CodigoGenerator;

@Controller
@SessionAttributes("solicitud")
public class SolicitudController {

	@Autowired
	private SolicitudService solicitudService;
	
	@Autowired
	private TipoSolicitudService tipoSolicitudService;
	
	@Autowired
	private EstadoSolicitudService estadoSolicitudService;
	
	@Autowired
	private UsuariosService usuarioService;
		
	@RequestMapping(value = "/solicitudes", method = RequestMethod.GET)
	public String solicitudes(Model model) {
		
		model.addAttribute("titulo", "SOLICITUDES");
		model.addAttribute("solicitudes", this.solicitudService.findAll());
		
		return "solicitudes";
	}

	@RequestMapping(value = "/solicitudespropias", method = RequestMethod.GET)
	public String solicitudespropias(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogueado = this.usuarioService.findByUsuario(authentication.getName());
		
		model.addAttribute("titulo", "MIS SOLICITUDES");
		model.addAttribute("solicitudes", this.solicitudService.findByUsuarioSolicitante(usuarioLogueado));
		
		return "solicitudespropias";
	}
	
	@RequestMapping(value = "/solicitudesmantenimiento", method = RequestMethod.GET)
	public String solicitudesmantenimiento(Model model) {
		
		EstadoSolicitud estadoSolicitud = this.estadoSolicitudService.findByNombre("EN_PROGRESO");
				
		model.addAttribute("titulo", "SOLICITUDES A RESOLVER");
		model.addAttribute("solicitudes", this.solicitudService.findByEstadoSolicitud(estadoSolicitud));
		
		return "solicitudesmantenimiento";
	}
	
	
	
	@RequestMapping(value = "/solicitudform")
	public String crear(Map<String, Object> model) {
		
		String action = "readonly";

		Solicitud solicitud = new Solicitud();
		solicitud.setCodigo(new CodigoGenerator().generateRandomString());
		
		EstadoSolicitud estadoSolicitud = this.estadoSolicitudService.findAll().get(0);
		solicitud.setEstadoSolicitud(estadoSolicitud);
		
		solicitud.setEquipo(new Equipo());
				
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioLogueado = this.usuarioService.findByUsuario(authentication.getName());
		solicitud.setUsuarioSolicitante(usuarioLogueado);
		
		model.put("titulo", "NUEVA SOLICITUD");
		model.put("solicitud", solicitud);
		model.put("tiposSolicitudes", this.tipoSolicitudService.findAll());
		model.put("action", action);
				
		return "solicitudform";

	}

	@RequestMapping(value = "/solicitudform", method = RequestMethod.POST)
	public String guardar(@Valid Solicitud solicitud, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		
		if (result.hasErrors()) {
			
			model.addAttribute("DATOS DE LA SOLICITUD");
			model.addAttribute("tiposSolicitudes", this.tipoSolicitudService.findAll());
			
			return "solicitudform";
		}
			
		
		String mensajeFlash = "Solicitud editada con éxito";
		
		if(solicitud.getId()==null) {
			
			EstadoSolicitud estadoSolicitud = this.estadoSolicitudService.findById(solicitud.getEstadoSolicitud().getId());
			solicitud.setEstadoSolicitud(estadoSolicitud);
			
			TipoSolicitud tipoSolicitud = this.tipoSolicitudService.findById(solicitud.getTipoSolicitud().getId());
			solicitud.setTipoSolicitud(tipoSolicitud);
			
			Usuario usuarioLogueado = this.usuarioService.findById(solicitud.getUsuarioSolicitante().getId());
			solicitud.setUsuarioSolicitante(usuarioLogueado);
			
			mensajeFlash = "Solicitud creada con éxito";
		}
				
		solicitudService.save(solicitud);
		
		status.setComplete();
		

		flash.addFlashAttribute("success", mensajeFlash);		
		
		return "redirect:solicitudespropias";
	}

	@RequestMapping(value = "/solicitudform/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		String action = "readonly";
		Solicitud solicitud = null;

		if (id > 0) {
			solicitud = this.solicitudService.findById(id);
			if(solicitud == null) {
				flash.addFlashAttribute("error", "La solicitud no existe en la BBDD!");
				return "redirect:/solicitudespropias";
			}			
		} else {
			flash.addFlashAttribute("error", "La solicitud creada no puede ser cero!");
			return "redirect:/solicitudespropias";
		}
		
		TipoSolicitud tipoSolicitud = this.tipoSolicitudService.findById(solicitud.getTipoSolicitud().getId());
		
		solicitud.setTipoSolicitud(tipoSolicitud);
								
		model.put("titulo", "EDITAR SOLICITUD");
		model.put("solicitud", solicitud);
		model.put("tiposSolicitudes", this.tipoSolicitudService.findAll());
		model.put("action", action);
	
		return "solicitudform";
	}

	@RequestMapping(value = "/eliminarsolicitud/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			this.solicitudService.delete(id);
			flash.addFlashAttribute("success", "La solucitud fue eliminada con éxito!");			
		}
		return "redirect:/solicitudespropias";

	}
	
	@RequestMapping(value = "/calificarsolicitud/{id}")
	public String calificarsolicitud(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Solicitud solicitud = this.solicitudService.findById(id);
			solicitud.setSolicitanteSatisfecho(true);
			this.solicitudService.save(solicitud);
			flash.addFlashAttribute("success", "Gracias por calificarnos!");			
		}
		return "redirect:/solicitudespropias";

	}
	
	@GetMapping(value="/versolicitud/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Solicitud solicitud = this.solicitudService.findById(id);
		if(solicitud==null) {
			flash.addFlashAttribute("error","La solicitud no existe en la BBDD");
			return "redirect:/solicitudespropias";
		}
		
		model.put("solicitud", solicitud);
		model.put("titulo", "DETALLE DE LA SOLICITUD");
		
		return "versolicitud";
	}	
	
	@RequestMapping(value = "/solicitudenprogreso/{id}")
	public String solicitudenprogreso(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			this.cambiarEstadoSolicitud(id, "EN_PROGRESO");
			flash.addFlashAttribute("success", "La solucitud fue INICIADA con éxito!");			
		}
		return "redirect:/solicitudes";

	}
	
	@RequestMapping(value = "/solicitudcerrada/{id}")
	public String solicitudcerrada(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			this.cambiarEstadoSolicitud(id, "CERRADA");
			flash.addFlashAttribute("success", "La solucitud fue CERRADA con éxito!");			
		}
		return "redirect:/solicitudes";

	}
	
	@RequestMapping(value = "/solicitudresuelta/{id}")
	public String solicitudresuelta(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			this.cambiarEstadoSolicitud(id, "RESUELTA");
			flash.addFlashAttribute("success", "La solucitud fue RESUELTA con éxito!");			
		}
		return "redirect:/solicitudesmantenimiento";

	}
	
	@RequestMapping(value = "/solicitudcancelada/{id}")
	public String solicitudcancelada(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			this.cambiarEstadoSolicitud(id, "CANCELADA");
			flash.addFlashAttribute("success", "La solucitud fue CANCELADA con éxito!");			
		}
		return "redirect:/solicitudesmantenimiento";

	}
	
	private void cambiarEstadoSolicitud(Long id, String siguienteEstado) {

		EstadoSolicitud estadoSolicitud = this.estadoSolicitudService.findByNombre(siguienteEstado);
		Solicitud solicitud = this.solicitudService.findById(id);
		solicitud.setEstadoSolicitud(estadoSolicitud);
		
		if("EN_PROGRESO".equals(siguienteEstado)) {
			solicitud.setFechaEnProceso(new Date());
			solicitud.setFechaHoraEnProceso(new Date());
		} else if("RESUELTA".equals(siguienteEstado)) {
			
			//para test lo se resuelve la solicitud a los tres dias
			//Calendar cal = Calendar.getInstance();
	        //cal.setTime(new Date());
	        //cal.add(Calendar.DATE, 3); 
	        //solicitud.setFechaResuelta(cal.getTime());
	        //solicitud.setFechaHoraResuelta(cal.getTime());
	        
	        //orginal
	        solicitud.setFechaResuelta(new Date());
	        solicitud.setFechaHoraResuelta(new Date());
			//AQUI SE CALCULA EL TIEMPO QUE SE TARDO EN RESOLVER LA SOLICITUD
			if(solicitud.getFechaEnProceso()!=null) {
				
				long diferenciaEnTime = solicitud.getFechaHoraResuelta().getTime() - solicitud.getFechaHoraEnProceso().getTime();
				long diferenciaEnSegundos = diferenciaEnTime / 1000 % 60;
				long diferenciaEnMinutos = diferenciaEnTime / (60 * 1000) % 60;
				long diferenciaEnHoras = diferenciaEnTime / (60 * 60 * 1000) ;
				long diferenciaEnDias = diferenciaEnTime / (24 * 60 * 60 * 1000);
				
                Integer tiempoDeResolucion = Long.valueOf(diferenciaEnHoras).intValue();

                solicitud.setTiempoDeResolucion(tiempoDeResolucion);
				
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				Usuario usuarioLogueado = this.usuarioService.findByUsuario(authentication.getName());
				
				solicitud.setUsuarioResolutivo(usuarioLogueado);
			}
			
		} else if("CANCELADA".equals(siguienteEstado)) {
			solicitud.setFechaCancelada(new Date());		
		} else if("CERRADA".equals(siguienteEstado)) {
			solicitud.setFechaCerrada(new Date());
		}
		
		this.solicitudService.save(solicitud);
	}

}
