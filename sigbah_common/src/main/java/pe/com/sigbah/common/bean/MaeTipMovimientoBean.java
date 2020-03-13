package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: MaeTipMovimientoBean.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeTipMovimientoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idTipMovimiento;
	private String nombreMovimiento;
	private String flagIngreso;
	private String flagSalida;
	private String flagDonacionIngreso;
	private String flagDonacionSalida;
	private String flagActivo;
	
	private String usuario;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private String fecha;
	
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the codigoRespuesta
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	/**
	 * @param codigoRespuesta the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	/**
	 * @return the mensajeRespuesta
	 */
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}
	/**
	 * @param mensajeRespuesta the mensajeRespuesta to set
	 */
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
	public Integer getIdTipMovimiento() {
		return idTipMovimiento;
	}
	public void setIdTipMovimiento(Integer idTipMovimiento) {
		this.idTipMovimiento = idTipMovimiento;
	}
	public String getNombreMovimiento() {
		return nombreMovimiento;
	}
	public void setNombreMovimiento(String nombreMovimiento) {
		this.nombreMovimiento = nombreMovimiento;
	}
	public String getFlagIngreso() {
		return flagIngreso;
	}
	public void setFlagIngreso(String flagIngreso) {
		this.flagIngreso = flagIngreso;
	}
	public String getFlagSalida() {
		return flagSalida;
	}
	public void setFlagSalida(String flagSalida) {
		this.flagSalida = flagSalida;
	}
	public String getFlagDonacionIngreso() {
		return flagDonacionIngreso;
	}
	public void setFlagDonacionIngreso(String flagDonacionIngreso) {
		this.flagDonacionIngreso = flagDonacionIngreso;
	}
	public String getFlagDonacionSalida() {
		return flagDonacionSalida;
	}
	public void setFlagDonacionSalida(String flagDonacionSalida) {
		this.flagDonacionSalida = flagDonacionSalida;
	}
	public String getFlagActivo() {
		return flagActivo;
	}
	public void setFlagActivo(String flagActivo) {
		this.flagActivo = flagActivo;
	}

	

}
