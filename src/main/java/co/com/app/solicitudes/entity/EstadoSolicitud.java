package co.com.app.solicitudes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "estado_solicitud")
public class EstadoSolicitud {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "estado_solicitud_seq")
	@SequenceGenerator(name = "estado_solicitud_seq",allocationSize = 1)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	
	public EstadoSolicitud() {
	
	}
		
	
	public EstadoSolicitud(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	@Override
	public String toString() {
		return "EstadoSolicitud [id=" + id + ", nombre=" + nombre + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoSolicitud other = (EstadoSolicitud) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
