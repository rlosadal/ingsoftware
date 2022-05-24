package co.com.app.solicitudes.service;

import java.util.Date;
import java.util.List;

import co.com.app.solicitudes.entity.EstadoSolicitud;
import co.com.app.solicitudes.entity.Solicitud;
import co.com.app.solicitudes.entity.Usuario;
import co.com.app.solicitudes.view.View;

public interface SolicitudService {
	
	public List<Solicitud> findAll();
	
	public void save(Solicitud solicitud);
	
	public Solicitud findById(Long id);
	
	public List<Solicitud> findByUsuarioSolicitante(Usuario usuarioSolicitante);
	
	public void delete(Long id);
	
	public List<Solicitud> findByEstadoSolicitud(EstadoSolicitud estadoSolicitud);
	
	//Reportes/
	
	public Integer findByTimpoResolucion(Date fechaDesde, Date fechaHasta);
	
	public Integer findByTotalSolicitudesAtentendidas(Date fechaDesde, Date fechaHasta);
	
	public Integer findByTotalSolicitudesSatisfechas(Date fechaDesde, Date fechaHasta);
	
	public List<View> findBySolicitudesAtendidasPorPeriodo(Date fechaDesde, Date fechaHasta);
	
	public List<View> findBySolicitudesSinAtenderPorPeriodo(Date fechaDesde, Date fechaHasta);
	
	public List<View> findByConfiguracionesDeEquipo(Date fechaDesde, Date fechaHasta);
	
	public List<View> findByDepartamentoMasSolicitante(Date fechaDesde, Date fechaHasta);
	
	public List<View> findByUsuariosSolicitantes(Date fechaDesde, Date fechaHasta);

	
	
}
