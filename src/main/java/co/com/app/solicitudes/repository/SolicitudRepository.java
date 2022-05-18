package co.com.app.solicitudes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.app.solicitudes.entity.EstadoSolicitud;
import co.com.app.solicitudes.entity.Solicitud;
import co.com.app.solicitudes.entity.Usuario;

@Repository
public interface SolicitudRepository extends CrudRepository<Solicitud, Long>{
	
	public List<Solicitud> findByUsuarioSolicitante(Usuario usuarioSolicitante);
	
	public List<Solicitud> findByEstadoSolicitud(EstadoSolicitud estadoSolicitud);

}
