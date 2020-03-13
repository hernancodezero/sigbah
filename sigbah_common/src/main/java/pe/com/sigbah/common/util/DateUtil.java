package pe.com.sigbah.common.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @className: DateUtil.java
 * @description: Clase que guarda las utilidades para fecha.
 * @date: 01/02/2015 17:13:30
 * @author: Hernando Huaman.
 */
public class DateUtil implements Serializable {

	private static final long serialVersionUID = 6841760012474046341L;
	private static Log LOGGER = LogFactory.getLog(DateUtil.class.getName());

	/**
	 * Obtiene una fecha parseada con el formato que se requiere.
	 * @param formato - Formato requerido, tipo String.
	 * @param fecha - La fecha que se desea parsear, tipo String.
	 * @return Fecha con el formato requerido, tipo String.
	 */
	public static Date obtenerFechaParseada(String formato, String fecha) {
		try {
			if (fecha != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(formato);
				return sdf.parse(fecha);
			}
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
		return null;
	}
	
	/**
	 * Obtiene una fecha con el formato que se requiere.
	 * @param formato - Formato requerido, tipo String.
	 * @param fecha - La fecha que se desea formatear, tipo Date.
	 * @return Fecha con el formato requerido, tipo String.
	 */
	public static String obtenerFechaFormateada(String formato, Date fecha) {
		try {
			if (fecha != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(formato);
				return sdf.format(fecha);
			}
		} catch(Exception ex) {
			LOGGER.error(ex);
		}
		return null;
	}

	/**
	 * Obtiene la última fecha del mes a partir de otra.
	 * @param fecha - Fecha a buscar, tipo Date.
	 * @return Última fecha, tipo Date
	 */
	public static Date obtenerFechaFinalDia(Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.set(cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH),
				Constantes.DIA_ULTIMA_HORA,
				Constantes.DIA_ULTIMO_SEGUNDO);
		return cal.getTime();
	}

	/**
	 * Obtiene una fecha sin hora.
	 * @param date - Fecha a quitar la hora, minuto y segundo, tipo Date.
	 * @return Fecha sin hora, minuto y segundo, tipo Date.
	 */
	 public static Date obtenerFechaSinHora(Date date) {
		 if (null != date) {
	    		Date dateWithOutTime = null; 
	        	Calendar calendar = Calendar.getInstance();
	        	calendar.setTime(date);
	        	calendar.set(Calendar.HOUR_OF_DAY,0);
	        	calendar.set(Calendar.MINUTE,0);
	        	calendar.set(Calendar.SECOND,0);
	        	dateWithOutTime = new Date(calendar.getTimeInMillis());
	        	return dateWithOutTime;
		 } else {
	    		return null;
		 }
	 }

	 /**
	  * Obtiene la fecha final a partir de una fecha final y el número de días hábiles.
	  * @param fechaFin - Fecha final, tipo Date.
	  * @param diasHabiles - número de días hábiles, tipo int.
	  * @return Fecha de inicio, tipo Date.
	  */
	 @SuppressWarnings("deprecation")
	public static Date fechaInicioDiasHabiles(Date fechaFin, int diasHabiles){
		Date fechaInicio = fechaFin;
		int diasHab = diasHabiles;
		while (diasHab>0){
			if(fechaInicio.getDay() != 0 && fechaInicio.getDay() != 6){
				diasHab--;
			}
			fechaInicio = new Date(fechaInicio.getTime() - Constantes.L_23_HOURS_59);
		}
		return fechaInicio;
	}

	/**
	* Obtiene la fecha del día lunes de la semana actual.
	* @return Fecha, tipo Date.
	*/
	public static Date getPrimerDiaSemanaActual(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) -2)*-1 );
		cal.set(cal.get(Calendar.YEAR), 
				cal.get(Calendar.MONTH), 
				cal.get(Calendar.DAY_OF_MONTH),0,0,0);
		return cal.getTime();
	}
	 
	/**
	 * Obtiene la fecha del día Viernes de la semana actual.
	 * @return Fecha, tipo Date.
	 */
	public static Date getUltimoDiaSemanaActual(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, ( 6 - Calendar.getInstance().get(Calendar.DAY_OF_WEEK)  ) );
		cal.set(cal.get(Calendar.YEAR), 
				cal.get(Calendar.MONTH), 
				cal.get(Calendar.DAY_OF_MONTH),0,0,0);
		return cal.getTime();
	}	 
	
	/**
	 * Obtiene el año asociado a la fecha actual.
	 * @return Año asociado a la fecha actual, tipo int.
	 */
	public static int getAnioActual(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	/**
	 * Obtiene una fecha y hora parseada con el formato que se requiere.
	 * @param fecha - La fecha que se desea parsear, tipo String.
	 * @return Fecha con el formato requerido, tipo String.
	 */
	public static Date obtenerFechaHoraParseada(String fecha) {
		try {
			if (!Utils.isNullOrEmpty(fecha)) {			
				SimpleDateFormat formatDate = new SimpleDateFormat(Constantes.FORMATO_FECHA);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(formatDate.parse(fecha));
				Calendar cal = Calendar.getInstance();	
				calendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
	        	calendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
	        	calendar.set(Calendar.SECOND, cal.get(Calendar.SECOND));			
	        	return calendar.getTime();
			}			
		} catch(Exception ex) {
			LOGGER.error(ex);
		}
		return null;
	}
	
	/**
	 * Obtiene el mes asociado a la fecha actual.
	 * @return mes asociado a la fecha actual, tipo int.
	 */
	public static int getMesActual(){
		return Calendar.getInstance().get(Calendar.MONTH);
	}
	
}
