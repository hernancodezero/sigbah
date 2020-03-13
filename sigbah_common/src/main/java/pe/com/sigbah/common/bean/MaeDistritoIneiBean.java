package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: MaeDistritoIneiBean.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeDistritoIneiBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codAnio;
	private String codDistrito;
	private String desDepartamento;
	private String desProvincia;
	private String desDistrito;
	private Integer poblacion;

	private String usuario;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private String fecha;
	private Integer vivienda;
	
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
	
	/**
	 * @return the codAnio
	 */
	public String getCodAnio() {
		return codAnio;
	}
	/**
	 * @param codAnio the codAnio to set
	 */
	public void setCodAnio(String codAnio) {
		this.codAnio = codAnio;
	}
	/**
	 * @return the codDistrito
	 */
	public String getCodDistrito() {
		return codDistrito;
	}
	/**
	 * @param codDistrito the codDistrito to set
	 */
	public void setCodDistrito(String codDistrito) {
		this.codDistrito = codDistrito;
	}
	/**
	 * @return the desDepartamento
	 */
	public String getDesDepartamento() {
		return desDepartamento;
	}
	/**
	 * @param desDepartamento the desDepartamento to set
	 */
	public void setDesDepartamento(String desDepartamento) {
		this.desDepartamento = desDepartamento;
	}
	
	public Integer getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(Integer poblacion) {
		this.poblacion = poblacion;
	}
	public String getDesProvincia() {
		return desProvincia;
	}
	public void setDesProvincia(String desProvincia) {
		this.desProvincia = desProvincia;
	}
	public String getDesDistrito() {
		return desDistrito;
	}
	public void setDesDistrito(String desDistrito) {
		this.desDistrito = desDistrito;
	}
	/**
	 * @return the vivienda
	 */
	public Integer getVivienda() {
		return vivienda;
	}
	/**
	 * @param vivienda the vivienda to set
	 */
	public void setVivienda(Integer vivienda) {
		this.vivienda = vivienda;
	}

	

}
