package co.com.app.solicitudes.service;

import java.util.List;

import co.com.app.solicitudes.entity.Role;
import co.com.app.solicitudes.entity.TipoSolicitud;

public interface TipoSolicitudService {
	
	public TipoSolicitud findById(Long id);
	
	public List<TipoSolicitud> findAll();


}
