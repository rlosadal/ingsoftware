package co.com.app.solicitudes.filter;

import java.io.Serializable;

public class Filter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5572405234107046939L;
	private String fechaDesde;
	private String fechaHasta;

	public Filter() {
		
	}

		
	public Filter(String fechaDesde, String fechaHasta) {
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	

}
