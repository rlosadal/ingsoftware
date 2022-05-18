package co.com.app.solicitudes.service;

import java.util.List;

import co.com.app.solicitudes.entity.Role;

public interface RoleService {
	
	public Role findById(Long id);
	
	public List<Role> findAll();


}
