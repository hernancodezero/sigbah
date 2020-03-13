package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: MaeUnidadMedidaBean.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeUnidadMedidaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idUnidad;
	private String nombreUnidad;
	private String nombreCorto;
	private String flagActivo;
	private Integer idUnidadSiga;
	
	private String usuario;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private String fecha;
	private String idFlagActivo;
	
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
	
	public Integer getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Integer idUnidad) {
		this.idUnidad = idUnidad;
	}
	public String getNombreUnidad() {
		return nombreUnidad;
	}
	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public String getFlagActivo() {
		return flagActivo;
	}
	public void setFlagActivo(String flagActivo) {
		this.flagActivo = flagActivo;
	}
	public Integer getIdUnidadSiga() {
		return idUnidadSiga;
	}
	public void setIdUnidadSiga(Integer idUnidadSiga) {
		this.idUnidadSiga = idUnidadSiga;
	}
	/**
	 * @return the idFlagActivo
	 */
	public String getIdFlagActivo() {
		return idFlagActivo;
	}
	/**
	 * @param idFlagActivo the idFlagActivo to set
	 */
	public void setIdFlagActivo(String idFlagActivo) {
		this.idFlagActivo = idFlagActivo;
	}

	

}
