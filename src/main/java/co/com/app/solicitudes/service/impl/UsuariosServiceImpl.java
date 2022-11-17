package co.com.app.solicitudes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.app.solicitudes.entity.Usuario;
import co.com.app.solicitudes.repository.UsuarioRepository;
import co.com.app.solicitudes.service.UsuariosService;

@Service
public class UsuariosServiceImpl implements UsuariosService{
    
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuario.setPassword(usuario.getPassword());
		usuarioRepository.save(usuario);
		
	}

	@Override
	@Transactional(readOnly = true)	
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}
 
	@Override
	@Transactional	
	public void delete(Long id) {
		usuarioRepository.deleteById(id);
		
	}

	@Override
	public Usuario findByUsuario(String usuario) {
		
		return this.usuarioRepository.findByUsuario(usuario);
	}
	
	

}
