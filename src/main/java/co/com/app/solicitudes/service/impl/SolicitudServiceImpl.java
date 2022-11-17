package co.com.app.solicitudes.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.app.solicitudes.entity.EstadoSolicitud;
import co.com.app.solicitudes.entity.Solicitud;
import co.com.app.solicitudes.entity.Usuario;
import co.com.app.solicitudes.repository.SolicitudRepository;
import co.com.app.solicitudes.service.SolicitudService;
import co.com.app.solicitudes.view.View;

@Service
public class SolicitudServiceImpl implements SolicitudService{
	
	@Autowired
	private SolicitudRepository solicitudRepository;

	@Override
	public List<Solicitud> findAll() {
		
		return (List<Solicitud>)this.solicitudRepository.findAll();
	}

	@Override
	public void save(Solicitud solicitud) {
		this.solicitudRepository.save(solicitud);
		
	}

	@Override
	public Solicitud findById(Long id) {
		
		return this.solicitudRepository.findById(id).orElse(null);
	}

	

	@Override
	public void delete(Long id) {
		this.solicitudRepository.deleteById(id);
		
	}

	@Override
	public List<Solicitud> findByUsuarioSolicitante(Usuario usuarioSolicitante) {
		
		return (List<Solicitud>) this.solicitudRepository.findByUsuarioSolicitante(usuarioSolicitante);
	}

	@Override
	public List<Solicitud> findByEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
		
		return (List<Solicitud>) this.solicitudRepository.findByEstadoSolicitud(estadoSolicitud);
	}

	@Override
	public Integer findByTimpoResolucion(Date fechaDesde, Date fechaHasta) {
		Integer promedio = 0;
		List<BigDecimal> result = this.solicitudRepository.findByTimpoResolucion(fechaDesde, fechaHasta);
		if(!result.isEmpty()) {
			 List<Integer> resultList = new ArrayList();
		        for(BigDecimal obj:result){
		        	if(obj!=null) {
		        		resultList.add(Integer.valueOf(obj.intValue()));
		        	}
		        }
		        if(!resultList.isEmpty()) {
		        	promedio = resultList.get(0);
		        }
		}
		return promedio;
	}
	
	@Override
	public Integer findByTotalSolicitudesAtentendidas(Date fechaDesde, Date fechaHasta) {
		Integer totalAtendidos = null;
		List<Object> result = this.solicitudRepository.findByTotalSolicitudesAtentendidas(fechaDesde, fechaHasta);
		for (Object object : result) {
			totalAtendidos = Integer.valueOf(String.valueOf(object));
		}
		return totalAtendidos!=null?totalAtendidos:0;
	}

	@Override
	public Integer findByTotalSolicitudesSatisfechas(Date fechaDesde, Date fechaHasta) {
		Integer totalSatisfechos = null;
		List<Object> result = this.solicitudRepository.findByTotalSolicitudesSatisfechas(fechaDesde, fechaHasta);
		for (Object object : result) {
			totalSatisfechos = Integer.valueOf(String.valueOf(object));
		}
		return totalSatisfechos!=null?totalSatisfechos:0;
	}
	
	@Override
	public List<View> findBySolicitudesAtendidasPorPeriodo(Date fechaDesde, Date fechaHasta) {
		View view=null;
		List<View> resultadoFinal = new ArrayList<>();
		List<Object[]> result = this.solicitudRepository.findBySolicitudesAtendidasPorPeriodo(fechaDesde, fechaHasta);
		for (Object[] object : result) {
			view = new View();
			view.setData1((String)object[0]);
			view.setData2(Integer.valueOf(String.valueOf(object[1])).toString());
			resultadoFinal.add(view);
		}
		return resultadoFinal;
	}
	
	@Override
	public List<View> findBySolicitudesSinAtenderPorPeriodo(Date fechaDesde, Date fechaHasta) {
		View view=null;
		List<View> resultadoFinal = new ArrayList<>();
		List<Object[]> result = this.solicitudRepository.findBySolicitudesSinAtenderPorPeriodo(fechaDesde, fechaHasta);
		for (Object[] object : result) {
			view = new View();
			view.setData1((String)object[0]);
			view.setData2(Integer.valueOf(String.valueOf(object[1])).toString());
			resultadoFinal.add(view);
		}
		return resultadoFinal;
	}
	
	@Override
	public List<View> findByConfiguracionesDeEquipo(Date fechaDesde, Date fechaHasta) {
		View view=null;
		List<View> resultadoFinal = new ArrayList<>();
		List<Object[]> result = this.solicitudRepository.findByConfiguracionesDeEquipo(fechaDesde, fechaHasta);
		for (Object[] object : result) {
			view = new View();
			view.setData1((String)object[0]);
			view.setData2((String)object[1]);
			resultadoFinal.add(view);
		}
		return resultadoFinal;
	}
	
	@Override
	public List<View> findByDepartamentoMasSolicitante(Date fechaDesde, Date fechaHasta) {
		View view=null;
		List<View> resultadoFinal = new ArrayList<>();
		List<Object[]> result = this.solicitudRepository.findByDepartamentoMasSolicitante(fechaDesde, fechaHasta);
		for (Object[] object : result) {
			view = new View();
			view.setData1((String)object[0]);
			view.setData2(Integer.valueOf(String.valueOf(object[1])).toString());
			resultadoFinal.add(view);
		}
		return resultadoFinal;
	}

	@Override
	public List<View> findByUsuariosSolicitantes(Date fechaDesde, Date fechaHasta) {
		View view=null;
		List<View> resultadoFinal = new ArrayList<>();
		List<Object[]> result = this.solicitudRepository.findByUsuariosSolicitantes(fechaDesde, fechaHasta);
		for (Object[] object : result) {
			view = new View();
			view.setData1((String)object[0]);
			view.setData2((String)object[1]);
			view.setData3(Integer.valueOf(String.valueOf(object[2])).toString());
			resultadoFinal.add(view);
		}
		return resultadoFinal;
	}

	
}
