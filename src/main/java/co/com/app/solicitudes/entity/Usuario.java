package co.com.app.solicitudes.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2112119666019776186L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "usuario_seq")
	@SequenceGenerator(name = "usuario_seq",allocationSize = 1)
	private Long id;

	@Column(name = "usuario")
	private String usuario;
	
	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "nombres")
	private String nombres;
	
	@Column(name = "nombre_completo")
	private String nombreCompleto;
		
	@OneToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	
	@OneToOne
	@JoinColumn(name="area_id")
	private Area area;	
	
	
	public Usuario() {
		
	}
	
		

	public Usuario(String usuario, String password, String email, String apellido, String nombres,
			String nombreCompleto, Role role, Area area) {

		this.usuario = usuario;
		this.password = password;
		this.email = email;
		this.apellido = apellido;
		this.nombres = nombres;
		this.nombreCompleto = nombreCompleto;
		this.role = role;
		this.area = area;
	}




	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getNombreCompleto() {
		return nombreCompleto;
	}


	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public Area getArea() {
		return area;
	}


	public void setArea(Area area) {
		this.area = area;
	}
	
	

	

}
