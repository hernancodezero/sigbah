package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: MaeEmpresaTransporteBean.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeEmpresaTransporteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idEmpresaTransporte;
	private String nombreEmpresa;
	private String direccion;
	private String representante;
	private String ruc;
	private String tipoTransporte;
	private String telefono;
	private Integer idDdi;
	private Integer idMedioTransporte;

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
	
	
	public Integer getIdEmpresaTransporte() {
		return idEmpresaTransporte;
	}
	public void setIdEmpresaTransporte(Integer idEmpresaTransporte) {
		this.idEmpresaTransporte = idEmpresaTransporte;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getTipoTransporte() {
		return tipoTransporte;
	}
	public void setTipoTransporte(String tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Integer getIdDdi() {
		return idDdi;
	}
	public void setIdDdi(Integer idDdi) {
		this.idDdi = idDdi;
	}
	public Integer getIdMedioTransporte() {
		return idMedioTransporte;
	}
	public void setIdMedioTransporte(Integer idMedioTransporte) {
		this.idMedioTransporte = idMedioTransporte;
	}

	

}
