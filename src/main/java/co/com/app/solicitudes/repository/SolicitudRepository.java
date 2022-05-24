package co.com.app.solicitudes.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.app.solicitudes.entity.EstadoSolicitud;
import co.com.app.solicitudes.entity.Solicitud;
import co.com.app.solicitudes.entity.Usuario;

@Repository
public interface SolicitudRepository extends CrudRepository<Solicitud, Long>{
	
	public List<Solicitud> findByUsuarioSolicitante(Usuario usuarioSolicitante);
	
	public List<Solicitud> findByEstadoSolicitud(EstadoSolicitud estadoSolicitud);
	
	//reportes//
	@Query(value = "SELECT avg(tiempo_de_resolucion) FROM solicitudes soli "
			+ "WHERE soli.fecha_resuelta is not null AND soli.fecha_resuelta BETWEEN ?1 AND ?2", nativeQuery = true)
	public List<BigDecimal> findByTimpoResolucion(Date fechaDesde, Date fechaHasta);
		
	
	@Query(value = "SELECT count(*) as cantidadtotal " + 
			"FROM solicitudes as sol " + 
			"WHERE sol.fecha_resuelta is not null " + 
			"and sol.fecha_resuelta BETWEEN ?1 AND ?2 " , nativeQuery = true)
	public List<Object> findByTotalSolicitudesAtentendidas(Date fechaDesde, Date fechaHasta);
	
	@Query(value = "SELECT count(*) as cantidad " + 
			"FROM solicitudes as sol " + 
			"WHERE sol.solicitante_satisfecho = True " + 
			"AND sol.fecha_resuelta BETWEEN ?1 AND ?2 " , nativeQuery = true)
	public List<Object> findByTotalSolicitudesSatisfechas (Date fechaDesde, Date fechaHasta);
	
	@Query(value = "SELECT ts.nombre as nombre, count(*) as cantidad " + 
			"FROM solicitudes as sol " + 
			"INNER JOIN tipo_solicitud as ts " + 
			"ON(sol.id_tipo_solicitud = ts.id) " + 
			"WHERE sol.fecha_resuelta is not null " + 
			"AND sol.fecha_resuelta BETWEEN ?1 AND ?2 " + 
			"GROUP BY ts.nombre ", nativeQuery = true)
	public List<Object[]> findBySolicitudesAtendidasPorPeriodo(Date fechaDesde, Date fechaHasta);
	
	@Query(value = "SELECT ts.nombre as nombre, count(*) as cantidad " + 
			"FROM solicitudes as sol " + 
			"INNER JOIN tipo_solicitud as ts " + 
			"ON(sol.id_tipo_solicitud = ts.id) " + 
			"WHERE sol.fecha_en_proceso is null " + 
			"AND sol.fecha_creacion BETWEEN ?1 AND ?2 " + 
			"GROUP BY ts.nombre ", nativeQuery = true)
	public List<Object[]> findBySolicitudesSinAtenderPorPeriodo(Date fechaDesde, Date fechaHasta);
	
	@Query(value = "SELECT sol.codigo AS codigosolicitud, eq.observaciones AS observaciones " + 
			"FROM solicitudes AS sol " + 
			"INNER JOIN equipos AS eq ON(sol.id_equipo=eq.id) " + 
			"WHERE sol.fecha_resuelta is null AND sol.fecha_creacion BETWEEN ?1 AND ?2 ", nativeQuery = true)
	public List<Object[]> findByConfiguracionesDeEquipo(Date fechaDesde, Date fechaHasta);
	
	@Query(value = "SELECT ar.nombre, count(*) AS cantidad " + 
			"FROM   solicitudes AS sol " + 
			"INNER JOIN usuarios AS usu ON (sol.id_usuario_solicitante=usu.id) " + 
			"INNER JOIN areas AS ar ON (usu.area_id=ar.id) " + 
			"WHERE sol.fecha_creacion BETWEEN ?1 AND ?2 " + 
			"GROUP  BY ar.nombre " + 
			"ORDER  BY cantidad DESC ", nativeQuery = true)
	public List<Object[]> findByDepartamentoMasSolicitante(Date fechaDesde, Date fechaHasta);
	
	
	@Query(value = "SELECT usu.usuario AS usuario, usu.nombre_completo AS nombres, count(*) AS cantidad " + 
			"FROM   solicitudes AS sol " + 
			"INNER JOIN usuarios AS usu ON (sol.id_usuario_solicitante=usu.id) " + 
			"WHERE sol.fecha_creacion BETWEEN ?1 AND ?2 " + 
			"GROUP  BY usu.usuario, usu.nombre_completo " + 
			"ORDER  BY cantidad DESC ", nativeQuery = true)
	public List<Object[]> findByUsuariosSolicitantes(Date fechaDesde, Date fechaHasta);

}
