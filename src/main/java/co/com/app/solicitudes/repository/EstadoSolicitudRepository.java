package co.com.app.solicitudes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.app.solicitudes.entity.EstadoSolicitud;

@Repository
public interface EstadoSolicitudRepository extends CrudRepository<EstadoSolicitud, Long>{
	
	EstadoSolicitud findByNombre(String nombre);

}
