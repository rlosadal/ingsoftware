package co.com.app.solicitudes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "equipos")
public class Equipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "equipo_seq")
	@SequenceGenerator(name = "equipo_seq",allocationSize = 1)
	private Long id;
	
	@Column(name = "observaciones")
	private String observaciones;

	public Equipo() {
		
	}
	
	
	public Equipo(String observaciones) {
		this.observaciones = observaciones;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	
}
