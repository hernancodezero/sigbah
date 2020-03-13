package pe.com.sigbah.common.bean;

import java.io.Serializable;

/**
 * @className: UsuarioBean.java
 * @description: 
 * @date: 17 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idUsuario;
	private String usuario;
	private String cargo;
	private String nombreUsuario;
	private Integer idDdi;
	private String codigoDdi;
	private String nombreDdi;
	private Integer idAlmacen;
	private String codigoAlmacen;
	private String nombreAlmacen;
	private String password;
	private String sessionId;
	private String codigoAnio;
	private String email;
	private String codigoMes;
	private String flagActivo;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private String menuXUsuario;
	private String nomEstado;
	
	/**
	 * 
	 */
	public UsuarioBean() {
		super();
	}

	/**
	 * @param usuario
	 * @param password
	 */
	public UsuarioBean(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;
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
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}


	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}


	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	/**
	 * @return the idDdi
	 */
	public Integer getIdDdi() {
		return idDdi;
	}


	/**
	 * @param idDdi the idDdi to set
	 */
	public void setIdDdi(Integer idDdi) {
		this.idDdi = idDdi;
	}


	/**
	 * @return the codigoDdi
	 */
	public String getCodigoDdi() {
		return codigoDdi;
	}


	/**
	 * @param codigoDdi the codigoDdi to set
	 */
	public void setCodigoDdi(String codigoDdi) {
		this.codigoDdi = codigoDdi;
	}


	/**
	 * @return the nombreDdi
	 */
	public String getNombreDdi() {
		return nombreDdi;
	}


	/**
	 * @param nombreDdi the nombreDdi to set
	 */
	public void setNombreDdi(String nombreDdi) {
		this.nombreDdi = nombreDdi;
	}


	/**
	 * @return the idAlmacen
	 */
	public Integer getIdAlmacen() {
		return idAlmacen;
	}


	/**
	 * @param idAlmacen the idAlmacen to set
	 */
	public void setIdAlmacen(Integer idAlmacen) {
		this.idAlmacen = idAlmacen;
	}


	/**
	 * @return the codigoAlmacen
	 */
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}


	/**
	 * @param codigoAlmacen the codigoAlmacen to set
	 */
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}


	/**
	 * @return the nombreAlmacen
	 */
	public String getNombreAlmacen() {
		return nombreAlmacen;
	}


	/**
	 * @param nombreAlmacen the nombreAlmacen to set
	 */
	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the codigoAnio
	 */
	public String getCodigoAnio() {
		return codigoAnio;
	}

	/**
	 * @param codigoAnio the codigoAnio to set
	 */
	public void setCodigoAnio(String codigoAnio) {
		this.codigoAnio = codigoAnio;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the codigoMes
	 */
	public String getCodigoMes() {
		return codigoMes;
	}

	/**
	 * @param codigoMes the codigoMes to set
	 */
	public void setCodigoMes(String codigoMes) {
		this.codigoMes = codigoMes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UsuarioBean [idUsuario=" + idUsuario + ", usuario=" + usuario + ", cargo=" + cargo + ", nombreUsuario="
				+ nombreUsuario + ", idDdi=" + idDdi + ", codigoDdi=" + codigoDdi + ", nombreDdi=" + nombreDdi
				+ ", idAlmacen=" + idAlmacen + ", codigoAlmacen=" + codigoAlmacen + ", nombreAlmacen=" + nombreAlmacen
				+ ", password=" + password + ", sessionId=" + sessionId + ", codigoAnio=" + codigoAnio + ", email="
				+ email + ", codigoMes=" + codigoMes + "]";
	}

	public String getFlagActivo() {
		return flagActivo;
	}

	public void setFlagActivo(String flagActivo) {
		this.flagActivo = flagActivo;
	}

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	/**
	 * @return the menuXUsuario
	 */
	public String getMenuXUsuario() {
		return menuXUsuario;
	}

	/**
	 * @param menuXUsuario the menuXUsuario to set
	 */
	public void setMenuXUsuario(String menuXUsuario) {
		this.menuXUsuario = menuXUsuario;
	}

	/**
	 * @return the nomEstado
	 */
	public String getNomEstado() {
		return nomEstado;
	}

	/**
	 * @param nomEstado the nomEstado to set
	 */
	public void setNomEstado(String nomEstado) {
		this.nomEstado = nomEstado;
	}
	
}
