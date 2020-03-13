package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: MaeDonanteBean.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeDonanteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idDonante;
	private String nombreDonante;
	private String tipo;
	private String representante;
	private String nroDocumento;
	private Integer idDdi;

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
	
	
	public Integer getIdDonante() {
		return idDonante;
	}
	public void setIdDonante(Integer idDonante) {
		this.idDonante = idDonante;
	}
	public String getNombreDonante() {
		return nombreDonante;
	}
	public void setNombreDonante(String nombreDonante) {
		this.nombreDonante = nombreDonante;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public Integer getIdDdi() {
		return idDdi;
	}
	public void setIdDdi(Integer idDdi) {
		this.idDdi = idDdi;
	}

	

}
