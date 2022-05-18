package co.com.app.solicitudes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.app.solicitudes.entity.EstadoSolicitud;
import co.com.app.solicitudes.repository.EstadoSolicitudRepository;
import co.com.app.solicitudes.service.EstadoSolicitudService;

@Service
public class EstadoSolicitudServiceImpl implements EstadoSolicitudService{
	
	@Autowired
	private EstadoSolicitudRepository estadoSolicitudRepository;

	@Override
	public EstadoSolicitud findById(Long id) {
		
		return this.estadoSolicitudRepository.findById(id).orElse(null);
	}

	@Override
	public List<EstadoSolicitud> findAll() {
		
		return (List<EstadoSolicitud>)this.estadoSolicitudRepository.findAll();
	}

	@Override
	public EstadoSolicitud findByNombre(String nombre) {
		
		return this.estadoSolicitudRepository.findByNombre(nombre);
	}

}
