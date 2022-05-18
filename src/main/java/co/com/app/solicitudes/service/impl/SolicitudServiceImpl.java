package co.com.app.solicitudes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.app.solicitudes.entity.EstadoSolicitud;
import co.com.app.solicitudes.entity.Solicitud;
import co.com.app.solicitudes.entity.Usuario;
import co.com.app.solicitudes.repository.SolicitudRepository;
import co.com.app.solicitudes.service.SolicitudService;

@Service
public class SolicitudServiceImpl implements SolicitudService{
	
	@Autowired
	private SolicitudRepository solicitudRepository;

	@Override
	public List<Solicitud> findAll() {
		
		return (List<Solicitud>)this.solicitudRepository.findAll();
	}

	@Override
	public void save(Solicitud solicitud) {
		this.solicitudRepository.save(solicitud);
		
	}

	@Override
	public Solicitud findById(Long id) {
		
		return this.solicitudRepository.findById(id).orElse(null);
	}

	

	@Override
	public void delete(Long id) {
		this.solicitudRepository.deleteById(id);
		
	}

	@Override
	public List<Solicitud> findByUsuarioSolicitante(Usuario usuarioSolicitante) {
		
		return (List<Solicitud>) this.solicitudRepository.findByUsuarioSolicitante(usuarioSolicitante);
	}

	@Override
	public List<Solicitud> findByEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
		
		return (List<Solicitud>) this.solicitudRepository.findByEstadoSolicitud(estadoSolicitud);
	}

}
