package co.com.app.solicitudes.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.com.app.solicitudes.filter.Filter;
import co.com.app.solicitudes.service.SolicitudService;
import co.com.app.solicitudes.view.View;

@Controller
@SessionAttributes("reporte")
public class ReporteController {

	@Autowired
	private SolicitudService solicitudService;
	
			
	@RequestMapping(value = "/loadtiemporesolucion", method = RequestMethod.GET)
	public String loadTiempoResolucion(Model model) {
		
		//Date fechaDesde = SolicitudUtil.getFechaDesde();
		//Date fechaHasta = SolicitudUtil.getFechaHasta();
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
		Integer promedio = 0 ;
        
		try {
			promedio = this.solicitudService.findByTimpoResolucion(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		        
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String fechaDesdeFormateada= DateFor.format(fechaDesde);
        String fechaHastaFormateada= DateFor.format(fechaHasta);
        
        Filter filter = new Filter(fechaDesdeFormateada, fechaHastaFormateada);
        		
        model.addAttribute("titulo", "REPORTE PROMEDIO TIEMPO DE RESOLUCION");
        model.addAttribute("filter", filter);
		model.addAttribute("promedio", promedio!=null? promedio.toString() : 0);
		
		return "reportes/tiemporesolucion";
	}
	
	@RequestMapping(value = "/searchtiemporesolucion", method = RequestMethod.GET)
	public String searchTiempoResolucion(Filter filter, Model model) {
		
		Integer promedio = 0;
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			fechaDesde = sdf.parse(filter.getFechaDesde());
			fechaHasta = sdf.parse(filter.getFechaHasta());
			promedio = this.solicitudService.findByTimpoResolucion(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("titulo", "REPORTE PROMEDIO TIEMPO DE RESOLUCION");	
		model.addAttribute("filter", filter);
		model.addAttribute("promedio", promedio!=null? promedio.toString() : 0);
		
		return "reportes/tiemporesolucion";
	}
	
	@RequestMapping(value = "/loadclientessatisfechos", method = RequestMethod.GET)
	public String loadClientesSatisfechos(Model model) {
		
		//Date fechaDesde = SolicitudUtil.getFechaDesde();
		//Date fechaHasta = SolicitudUtil.getFechaHasta();
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
		Integer totalAtendidos = 0 ;
		Integer totalSatisfechos = 0;
		
		try {
			totalAtendidos = this.solicitudService.findByTotalSolicitudesAtentendidas(fechaDesde, fechaHasta);
	        totalSatisfechos = this.solicitudService.findByTotalSolicitudesSatisfechas(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
                
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String fechaDesdeFormateada= DateFor.format(fechaDesde);
        String fechaHastaFormateada= DateFor.format(fechaHasta);
        
        Filter filter = new Filter(fechaDesdeFormateada, fechaHastaFormateada);
        		
        model.addAttribute("titulo", "REPORTE CLIENTES SATISFECHOS");
        model.addAttribute("filter", filter);
		model.addAttribute("totalAtendidos", totalAtendidos!=null? totalAtendidos.toString() : 0);
		model.addAttribute("totalSatisfechos", totalSatisfechos!=null? totalSatisfechos.toString() : 0);
		
		return "reportes/clientessatisfechos";
	}
	
	@RequestMapping(value = "/searchclientessatisfechos", method = RequestMethod.GET)
	public String searchClientesSatisfechos(Filter filter, Model model) {
		
		Integer totalAtendidos = 0;
		Integer totalSatisfechos = 0;
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			fechaDesde = sdf.parse(filter.getFechaDesde());
			fechaHasta = sdf.parse(filter.getFechaHasta());
			totalAtendidos = this.solicitudService.findByTotalSolicitudesAtentendidas(fechaDesde, fechaHasta);
	        totalSatisfechos = this.solicitudService.findByTotalSolicitudesSatisfechas(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("titulo", "REPORTE CLIENTES SATISFECHOS");
        model.addAttribute("filter", filter);
		model.addAttribute("totalAtendidos", totalAtendidos!=null? totalAtendidos.toString() : 0);
		model.addAttribute("totalSatisfechos", totalSatisfechos!=null? totalSatisfechos.toString() : 0);
		
		return "reportes/clientessatisfechos";
	}
	
	@RequestMapping(value = "/loadsolicitudesatendidasporperiodo", method = RequestMethod.GET)
	public String loadSolicitudesAtendidasPorPeriodo(Model model) {
		
		List<View> resultado = new ArrayList<View>();
		
		//Date fechaDesde = SolicitudUtil.getFechaDesde();
		//Date fechaHasta = SolicitudUtil.getFechaHasta();
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
        
		try {
			resultado = this.solicitudService.findBySolicitudesAtendidasPorPeriodo(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		        
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String fechaDesdeFormateada= DateFor.format(fechaDesde);
        String fechaHastaFormateada= DateFor.format(fechaHasta);
        
        Filter filter = new Filter(fechaDesdeFormateada, fechaHastaFormateada);
        		
        model.addAttribute("titulo", "REPORTE SOLICITUDES ATENDIDAS POR PERIODO");
        model.addAttribute("filter", filter);
		model.addAttribute("resultado", resultado);
		
		return "reportes/solicitudesatendidasporperiodo";
	}
	
	@RequestMapping(value = "/searchsolicitudesatendidasporperiodo", method = RequestMethod.GET)
	public String searchSolicitudesAtendidasPorPeriodo(Filter filter, Model model) {
		
		List<View> resultado = new ArrayList<View>();
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			fechaDesde = sdf.parse(filter.getFechaDesde());
			fechaHasta = sdf.parse(filter.getFechaHasta());
			resultado = this.solicitudService.findBySolicitudesAtendidasPorPeriodo(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("titulo", "REPORTE SOLICITUDES ATENDIDAS POR PERIODO");
		model.addAttribute("filter", filter);
		model.addAttribute("resultado", resultado);
		
		return "reportes/solicitudesatendidasporperiodo";
	}
	
	@RequestMapping(value = "/loadsolicitudessinatenderporperiodo", method = RequestMethod.GET)
	public String loadSolicitudesSinAtenderPorPeriodo(Model model) {
		
		List<View> resultado = new ArrayList<View>();
		
		//Date fechaDesde = SolicitudUtil.getFechaDesde();
		//Date fechaHasta = SolicitudUtil.getFechaHasta();
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
		
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String fechaDesdeFormateada= DateFor.format(fechaDesde);
        String fechaHastaFormateada= DateFor.format(fechaHasta);
        
        try {
        	resultado = this.solicitudService.findBySolicitudesSinAtenderPorPeriodo(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		              
        
        Filter filter = new Filter(fechaDesdeFormateada, fechaHastaFormateada);
        		
        model.addAttribute("titulo", "REPORTE SOLICITUDES SIN ATENDER POR PERIODO");
        model.addAttribute("filter", filter);
		model.addAttribute("resultado", resultado);
		
		return "reportes/solicitudessinatenderporperiodo";
	}
	
	@RequestMapping(value = "/searchsolicitudessinatenderporperiodo", method = RequestMethod.GET)
	public String searchSolicitudesSinAtenderPorPeriodo(Filter filter, Model model) {
		
		List<View> resultado = new ArrayList<View>();
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			fechaDesde = sdf.parse(filter.getFechaDesde());
			fechaHasta = sdf.parse(filter.getFechaHasta());
			resultado = this.solicitudService.findBySolicitudesSinAtenderPorPeriodo(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("titulo", "REPORTE SOLICITUDES SIN ATENDER POR PERIODO");
		model.addAttribute("filter", filter);
		model.addAttribute("resultado", resultado);
		
		return "reportes/solicitudessinatenderporperiodo";
	}
	
	@RequestMapping(value = "/loadconfiguracionesdeequipo", method = RequestMethod.GET)
	public String loadConfiguracionesDeEquipo(Model model) {
		
		List<View> resultado = new ArrayList<View>();
		
		//Date fechaDesde = SolicitudUtil.getFechaDesde();
		//Date fechaHasta = SolicitudUtil.getFechaHasta();
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
        
		try {
			resultado = this.solicitudService.findByConfiguracionesDeEquipo(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		        
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String fechaDesdeFormateada= DateFor.format(fechaDesde);
        String fechaHastaFormateada= DateFor.format(fechaHasta);
        
        Filter filter = new Filter(fechaDesdeFormateada, fechaHastaFormateada);
        		
        model.addAttribute("titulo", "REPORTE CONFIGURACIONES DE EQUIPOS");
        model.addAttribute("filter", filter);
		model.addAttribute("resultado", resultado);
		
		return "reportes/configuracionesdeequipo";
	}
	
	@RequestMapping(value = "/searchconfiguracionesdeequipo", method = RequestMethod.GET)
	public String searchConfiguracionesDeEquipo(Filter filter, Model model) {
		
		List<View> resultado = new ArrayList<View>();
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			fechaDesde = sdf.parse(filter.getFechaDesde());
			fechaHasta = sdf.parse(filter.getFechaHasta());
			resultado = this.solicitudService.findByConfiguracionesDeEquipo(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("titulo", "REPORTE CONFIGURACIONES DE EQUIPOS");
		model.addAttribute("filter", filter);
		model.addAttribute("resultado", resultado);
		
		return "reportes/configuracionesdeequipo";
	}
	
	@RequestMapping(value = "/loaddepartamentomassolicitante", method = RequestMethod.GET)
	public String loadDepartamentoMasSolicitante(Model model) {
		
		List<View> resultado = new ArrayList<View>();
		
		//Date fechaDesde = SolicitudUtil.getFechaDesde();
		//Date fechaHasta = SolicitudUtil.getFechaHasta();
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
        
		try {
			resultado = this.solicitudService.findByDepartamentoMasSolicitante(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		        
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String fechaDesdeFormateada= DateFor.format(fechaDesde);
        String fechaHastaFormateada= DateFor.format(fechaHasta);
        
        Filter filter = new Filter(fechaDesdeFormateada, fechaHastaFormateada);
        		
        model.addAttribute("titulo", "REPORTE DEPARTAMENTO QUE MAS SOLICITUDES REALIZARON");
        model.addAttribute("filter", filter);
		model.addAttribute("resultado", resultado);
		
		return "reportes/departamentomassolicitante";
	}
	
	@RequestMapping(value = "/searchdepartamentomassolicitante", method = RequestMethod.GET)
	public String searchdepartamentomassolicitante(Filter filter, Model model) {
		
		List<View> resultado = new ArrayList<View>();
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			fechaDesde = sdf.parse(filter.getFechaDesde());
			fechaHasta = sdf.parse(filter.getFechaHasta());
			resultado = this.solicitudService.findByDepartamentoMasSolicitante(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("titulo", "REPORTE DEPARTAMENTO QUE MAS SOLICITUDES REALIZARON");
		model.addAttribute("filter", filter);
		model.addAttribute("resultado", resultado);
		
		return "reportes/departamentomassolicitante";
	}
	
	@RequestMapping(value = "/loadusuariossolicitantes", method = RequestMethod.GET)
	public String loadUsuariosSolicitantes(Model model) {
		
		List<View> resultado = new ArrayList<View>();
		
		//Date fechaDesde = SolicitudUtil.getFechaDesde();
		//Date fechaHasta = SolicitudUtil.getFechaHasta();
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
        
		try {
			resultado = this.solicitudService.findByUsuariosSolicitantes(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		        
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String fechaDesdeFormateada= DateFor.format(fechaDesde);
        String fechaHastaFormateada= DateFor.format(fechaHasta);
        
        Filter filter = new Filter(fechaDesdeFormateada, fechaHastaFormateada);
        		
        model.addAttribute("titulo", "REPORTE USUARIOS QUE MAS SOLICITAN ARREGLOS");
        model.addAttribute("filter", filter);
		model.addAttribute("resultado", resultado);
		
		return "reportes/usuariossolicitantes";
	}
	
	@RequestMapping(value = "/searchusuariossolicitantes", method = RequestMethod.GET)
	public String searchUsuariosSolicitantes(Filter filter, Model model) {
		
		List<View> resultado = new ArrayList<View>();
		Date fechaDesde = new Date();
		Date fechaHasta = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			fechaDesde = sdf.parse(filter.getFechaDesde());
			fechaHasta = sdf.parse(filter.getFechaHasta());
			resultado = this.solicitudService.findByUsuariosSolicitantes(fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("titulo", "REPORTE USUARIOS QUE MAS SOLICITAN ARREGLOS");
		model.addAttribute("filter", filter);
		model.addAttribute("resultado", resultado);
		
		return "reportes/usuariossolicitantes";
	}
		

}
