package co.com.app.solicitudes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.app.solicitudes.entity.Area;
import co.com.app.solicitudes.repository.AreaRepository;
import co.com.app.solicitudes.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService{
	
	@Autowired
	private AreaRepository areaRepository;

	@Override
	public Area findById(Long id) {
		
		return this.areaRepository.findById(id).orElse(null);
	}

	@Override
	public List<Area> findAll() {
		
		return (List<Area>) this.areaRepository.findAll();
	}

	@Override
	public List<Area> findByNombre(String nombre) {
		return (List<Area>) this.areaRepository.findByNombre(nombre);
	}

}
