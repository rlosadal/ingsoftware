package co.com.app.solicitudes.service;

import java.util.List;

import co.com.app.solicitudes.entity.Usuario;

public interface UsuariosService {
	
	public List<Usuario> findAll();
	
	public void save(Usuario usuario);
	
	public Usuario findById(Long id);
	
	public Usuario findByUsuario(String usuario);
	
	public void delete(Long id);

	
	
}
