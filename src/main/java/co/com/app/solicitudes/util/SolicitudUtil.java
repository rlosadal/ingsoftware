package co.com.app.solicitudes.util;

import java.util.Calendar;
import java.util.Date;

public class SolicitudUtil {

	public SolicitudUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static Date getFechaDesde() {
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0); 
       	
       	return cal.getTime();
	}
	public static Date getFechaHasta() {
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 7); 
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0); 
       	        		
		return cal.getTime();
	}

}
