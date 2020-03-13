package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: MaeTipCamionBean.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeTipCamionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idTipCamion;
	private BigDecimal tonelaje;
	private BigDecimal volumen;
	private String descripcion;
	
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
	
	public Integer getIdTipCamion() {
		return idTipCamion;
	}
	public void setIdTipCamion(Integer idTipCamion) {
		this.idTipCamion = idTipCamion;
	}
	public BigDecimal getTonelaje() {
		return tonelaje;
	}
	public void setTonelaje(BigDecimal tonelaje) {
		this.tonelaje = tonelaje;
	}
	public BigDecimal getVolumen() {
		return volumen;
	}
	public void setVolumen(BigDecimal volumen) {
		this.volumen = volumen;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

}
