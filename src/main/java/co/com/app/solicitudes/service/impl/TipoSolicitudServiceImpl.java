package co.com.app.solicitudes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.app.solicitudes.entity.TipoSolicitud;
import co.com.app.solicitudes.repository.TipoSolicitudRepository;
import co.com.app.solicitudes.service.TipoSolicitudService;

@Service
public class TipoSolicitudServiceImpl implements TipoSolicitudService{

	@Autowired
	private TipoSolicitudRepository tipoSolicitudDAO;
	
	@Override
	public TipoSolicitud findById(Long id) {
		
		return this.tipoSolicitudDAO.findById(id).orElse(null);
	}

	@Override
	public List<TipoSolicitud> findAll() {
		
		return (List<TipoSolicitud>)this.tipoSolicitudDAO.findAll();
	}

}
