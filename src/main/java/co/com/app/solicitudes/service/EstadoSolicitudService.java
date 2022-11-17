package co.com.app.solicitudes.service;

import java.util.List;

import co.com.app.solicitudes.entity.EstadoSolicitud;

public interface EstadoSolicitudService {
	
	public EstadoSolicitud findById(Long id);
	
	public List<EstadoSolicitud> findAll();
	
	public EstadoSolicitud findByNombre(String nombre);


}
