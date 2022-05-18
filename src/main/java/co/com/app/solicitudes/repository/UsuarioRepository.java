package co.com.app.solicitudes.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.app.solicitudes.entity.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	public Usuario findByUsuario(String usuario);
}
