package co.com.app.solicitudes.service;

import java.util.List;

import co.com.app.solicitudes.entity.Area;

public interface AreaService {
	
	public Area findById(Long id);
	
	public List<Area> findAll();
	
	public List<Area> findByNombre(String nombre);


}
