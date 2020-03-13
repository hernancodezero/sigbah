package pe.com.sigbah.common.bean;

import java.io.Serializable;

/**
 * @className: BaseException1.java
 * @description: 
 * @date: 2 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class BaseOutputBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private String indicador;
	private String url;
	private String usuarioRegistro;
	private Integer idUsuario;

	/**
	 * 
	 */
	public BaseOutputBean() {
		super();
	}
	
	/**
	 * @param codigoRespuesta
	 */
	public BaseOutputBean(String codigoRespuesta) {
		super();
		this.codigoRespuesta = codigoRespuesta;
	}

	/**
	 * @param codigoRespuesta
	 * @param mensajeRespuesta
	 */
	public BaseOutputBean(String codigoRespuesta, String mensajeRespuesta) {
		super();
		this.codigoRespuesta = codigoRespuesta;
		this.mensajeRespuesta = mensajeRespuesta;
	}

	/**
	 * @param codigoRespuesta
	 * @param mensajeRespuesta
	 * @param indicador
	 */
	public BaseOutputBean(String codigoRespuesta, String mensajeRespuesta, String indicador) {
		super();
		this.codigoRespuesta = codigoRespuesta;
		this.mensajeRespuesta = mensajeRespuesta;
		this.indicador = indicador;
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
	 * @return the indicador
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador the indicador to set
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the usuarioRegistro
	 */
	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	/**
	 * @param usuarioRegistro the usuarioRegistro to set
	 */
	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	/**
	 * @return the idUsuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}