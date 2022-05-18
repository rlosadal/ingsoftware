package co.com.app.solicitudes.service;

import java.util.List;

import co.com.app.solicitudes.entity.EstadoSolicitud;
import co.com.app.solicitudes.entity.Solicitud;
import co.com.app.solicitudes.entity.Usuario;

public interface SolicitudService {
	
	public List<Solicitud> findAll();
	
	public void save(Solicitud solicitud);
	
	public Solicitud findById(Long id);
	
	public List<Solicitud> findByUsuarioSolicitante(Usuario usuarioSolicitante);
	
	public void delete(Long id);
	
	public List<Solicitud> findByEstadoSolicitud(EstadoSolicitud estadoSolicitud);

	
	
}
