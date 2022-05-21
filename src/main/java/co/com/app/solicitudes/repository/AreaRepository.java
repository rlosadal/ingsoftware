package co.com.app.solicitudes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.app.solicitudes.entity.Area;

@Repository
public interface AreaRepository extends CrudRepository<Area, Long>{
	
	List<Area> findByNombre(String nombre);

}
