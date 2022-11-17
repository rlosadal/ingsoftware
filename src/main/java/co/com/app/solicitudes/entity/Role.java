package co.com.app.solicitudes.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5519079577266975914L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "role_seq")
	@SequenceGenerator(name = "role_seq",allocationSize = 1)
	private Long id;

	@Column(name = "role")
	private String role;
	
	@Column(name = "nombre")
	private String nombre;

	public Role() {
		
	}

	public Role(String rol, String nombre) {
		this.role = rol;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
