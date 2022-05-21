package co.com.app.solicitudes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.app.solicitudes.entity.Role;
import co.com.app.solicitudes.repository.RoleRepository;
import co.com.app.solicitudes.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findById(Long id) {
		
		return this.roleRepository.findById(id).orElse(null);
	}

	@Override
	public List<Role> findAll() {
		
		return (List<Role>) this.roleRepository.findAll();
	}

	@Override
	public List<Role> findByRole(String role) {
		
		return (List<Role>) this.roleRepository.findByRole(role);
	}

}
