package co.com.app.solicitudes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.app.solicitudes.entity.Equipo;

@Repository
public interface EquipoRepository extends CrudRepository<Equipo, Long>{

}
