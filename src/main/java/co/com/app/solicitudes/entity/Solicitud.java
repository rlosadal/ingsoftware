package co.com.app.solicitudes.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "solicitudes")
public class Solicitud {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "codigo")
	private String codigo;
	
	@Temporal(TemporalType.DATE)	
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.DATE)	
	@Column(name = "fecha_en_proceso")
	private Date fechaEnProceso;
	
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name = "fecha_hora_en_proceso")
	private Date fechaHoraEnProceso;
	
	@Temporal(TemporalType.DATE)	
	@Column(name = "fecha_resuelta")
	private Date fechaResuelta;
	
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name = "fecha_hora_resuelta")
	private Date fechaHoraResuelta;
	
	@Temporal(TemporalType.DATE)	
	@Column(name = "fecha_cancelada")
	private Date fechaCancelada;
	
	@Temporal(TemporalType.DATE)	
	@Column(name = "fecha_cerrada")
	private Date fechaCerrada;
		
	@OneToOne
	@JoinColumn(name="id_tipo_solicitud")
	private TipoSolicitud tipoSolicitud;
	
	@OneToOne
	@JoinColumn(name="id_estado_solicitud")
	private EstadoSolicitud estadoSolicitud;
		
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_equipo")
	private Equipo equipo;
		
	@OneToOne
	@JoinColumn(name="id_usuario_solicitante")
	private Usuario usuarioSolicitante;
	
	@OneToOne
	@JoinColumn(name="id_usuario_resolutivo")
	private Usuario usuarioResolutivo;
	
	@Column(name = "tiempo_de_resolucion")
	private Integer tiempoDeResolucion = 0;
	
	@Column(name = "solicitante_satisfecho")
	private boolean solicitanteSatisfecho = false;
	
	public Solicitud() {
		
	}
	
	public Solicitud(String codigo, Date fechaCreacion, Date fechaEnProceso, Date fechaHoraEnProceso,
			Date fechaResuelta, Date fechaHoraResuelta, Date fechaCancelada, Date fechaCerrada,
			TipoSolicitud tipoSolicitud, EstadoSolicitud estadoSolicitud, Equipo equipo, Usuario usuarioSolicitante,
			Usuario usuarioResolutivo, Integer tiempoDeResolucion, boolean solicitanteSatisfecho) {

		this.codigo = codigo;
		this.fechaCreacion = fechaCreacion;
		this.fechaEnProceso = fechaEnProceso;
		this.fechaHoraEnProceso = fechaHoraEnProceso;
		this.fechaResuelta = fechaResuelta;
		this.fechaHoraResuelta = fechaHoraResuelta;
		this.fechaCancelada = fechaCancelada;
		this.fechaCerrada = fechaCerrada;
		this.tipoSolicitud = tipoSolicitud;
		this.estadoSolicitud = estadoSolicitud;
		this.equipo = equipo;
		this.usuarioSolicitante = usuarioSolicitante;
		this.usuarioResolutivo = usuarioResolutivo;
		this.tiempoDeResolucion = tiempoDeResolucion;
		this.solicitanteSatisfecho = solicitanteSatisfecho;
	}






	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
	}
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public Date getFechaEnProceso() {
		return fechaEnProceso;
	}



	public void setFechaEnProceso(Date fechaEnProceso) {
		this.fechaEnProceso = fechaEnProceso;
	}



	public Date getFechaResuelta() {
		return fechaResuelta;
	}


	public void setFechaResuelta(Date fechaResuelta) {
		this.fechaResuelta = fechaResuelta;
	}



	public Date getFechaCerrada() {
		return fechaCerrada;
	}



	public void setFechaCerrada(Date fechaCerrada) {
		this.fechaCerrada = fechaCerrada;
	}







	public Date getFechaCancelada() {
		return fechaCancelada;
	}



	public void setFechaCancelada(Date fechaCancelada) {
		this.fechaCancelada = fechaCancelada;
	}


	
	public TipoSolicitud getTipoSolicitud() {
		return tipoSolicitud;
	}



	public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}



	public EstadoSolicitud getEstadoSolicitud() {
		return estadoSolicitud;
	}



	public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}



	public Equipo getEquipo() {
		return equipo;
	}



	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	

	public Usuario getUsuarioSolicitante() {
		return usuarioSolicitante;
	}



	public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}



	public Usuario getUsuarioResolutivo() {
		return usuarioResolutivo;
	}



	public void setUsuarioResolutivo(Usuario usuarioResolutivo) {
		this.usuarioResolutivo = usuarioResolutivo;
	}
	
	
//	public String getTiempoDeResolucion() {
//		
//		if(this.getFechaSolucionada()!=null && this.getFechaEnProceso()!=null) {
//			
//			long tmp = this.getFechaSolucionada().getTime() - this.getFechaCreacion().getTime();
//			long dias = tmp/(24*60*60*1000);
//			long horas = (tmp/(60*60*1000) - dias*24);
//			tiempoDeResolucion = Long.toString(horas).toString();
//			
//		}
//		return tiempoDeResolucion;
//	}
//
//
//	public void setTiempoDeResolucion(String tiempoDeResolucion) {
//		this.tiempoDeResolucion = tiempoDeResolucion;
//	}


	public Integer getTiempoDeResolucion() {
		return tiempoDeResolucion;
	}




	public void setTiempoDeResolucion(Integer tiempoDeResolucion) {
		this.tiempoDeResolucion = tiempoDeResolucion;
	}

	

	public boolean isSolicitanteSatisfecho() {
		return solicitanteSatisfecho;
	}


	public void setSolicitanteSatisfecho(boolean solicitanteSatisfecho) {
		this.solicitanteSatisfecho = solicitanteSatisfecho;
	}
	
	
	public Date getFechaHoraEnProceso() {
		return fechaHoraEnProceso;
	}


	public void setFechaHoraEnProceso(Date fechaHoraEnProceso) {
		this.fechaHoraEnProceso = fechaHoraEnProceso;
	}



	public Date getFechaHoraResuelta() {
		return fechaHoraResuelta;
	}



	public void setFechaHoraResuelta(Date fechaHoraResuelta) {
		this.fechaHoraResuelta = fechaHoraResuelta;
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
		Solicitud other = (Solicitud) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", codigo=" + codigo + ", fechaCreacion=" + fechaCreacion + ", usuarioSolicitante="
				+ usuarioSolicitante.getUsuario() + "]";
	}
		
	
}
