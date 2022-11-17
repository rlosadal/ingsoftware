package co.com.app.solicitudes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.app.solicitudes.entity.TipoSolicitud;

@Repository
public interface TipoSolicitudRepository extends CrudRepository<TipoSolicitud, Long>{

}
