package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: MaeCorrelativoBean.java
 * @description: 
 * @date: 31 de jul. de 2018
 * @author: ARCHY.
 */
public class MaeCorrelativoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idCorrelativo;
	private Integer idAlmacen;
	private String nroSerie;
	private String nroInicio;
	private String nroFin;
	private String nroActual;
	private String flagActivo;
	private String codTipo;
	private String activarFormato;
	private String nombreImprenta;
	private String rucImprenta;
	private String nroAutorizacion;
	private String fechaInicio;
	private String usuario;
	private String control;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	
	private String nombreAlmacen;
	private String vigencia;
	/**
	 * @return the idCorrelativo
	 */
	public Integer getIdCorrelativo() {
		return idCorrelativo;
	}
	/**
	 * @param idCorrelativo the idCorrelativo to set
	 */
	public void setIdCorrelativo(Integer idCorrelativo) {
		this.idCorrelativo = idCorrelativo;
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
	 * @return the nroSerie
	 */
	public String getNroSerie() {
		return nroSerie;
	}
	/**
	 * @param nroSerie the nroSerie to set
	 */
	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}
	/**
	 * @return the nroInicio
	 */
	public String getNroInicio() {
		return nroInicio;
	}
	/**
	 * @param nroInicio the nroInicio to set
	 */
	public void setNroInicio(String nroInicio) {
		this.nroInicio = nroInicio;
	}
	/**
	 * @return the nroFin
	 */
	public String getNroFin() {
		return nroFin;
	}
	/**
	 * @param nroFin the nroFin to set
	 */
	public void setNroFin(String nroFin) {
		this.nroFin = nroFin;
	}
	/**
	 * @return the nroActual
	 */
	public String getNroActual() {
		return nroActual;
	}
	/**
	 * @param nroActual the nroActual to set
	 */
	public void setNroActual(String nroActual) {
		this.nroActual = nroActual;
	}
	/**
	 * @return the flagActivo
	 */
	public String getFlagActivo() {
		return flagActivo;
	}
	/**
	 * @param flagActivo the flagActivo to set
	 */
	public void setFlagActivo(String flagActivo) {
		this.flagActivo = flagActivo;
	}
	/**
	 * @return the codTipo
	 */
	public String getCodTipo() {
		return codTipo;
	}
	/**
	 * @param codTipo the codTipo to set
	 */
	public void setCodTipo(String codTipo) {
		this.codTipo = codTipo;
	}
	/**
	 * @return the activarFormato
	 */
	public String getActivarFormato() {
		return activarFormato;
	}
	/**
	 * @param activarFormato the activarFormato to set
	 */
	public void setActivarFormato(String activarFormato) {
		this.activarFormato = activarFormato;
	}
	/**
	 * @return the nombreImprenta
	 */
	public String getNombreImprenta() {
		return nombreImprenta;
	}
	/**
	 * @param nombreImprenta the nombreImprenta to set
	 */
	public void setNombreImprenta(String nombreImprenta) {
		this.nombreImprenta = nombreImprenta;
	}
	/**
	 * @return the nroAutorizacion
	 */
	public String getNroAutorizacion() {
		return nroAutorizacion;
	}
	/**
	 * @param nroAutorizacion the nroAutorizacion to set
	 */
	public void setNroAutorizacion(String nroAutorizacion) {
		this.nroAutorizacion = nroAutorizacion;
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
	 * @return the control
	 */
	public String getControl() {
		return control;
	}
	/**
	 * @param control the control to set
	 */
	public void setControl(String control) {
		this.control = control;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the rucImprenta
	 */
	public String getRucImprenta() {
		return rucImprenta;
	}
	/**
	 * @param rucImprenta the rucImprenta to set
	 */
	public void setRucImprenta(String rucImprenta) {
		this.rucImprenta = rucImprenta;
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
	 * @return the vigencia
	 */
	public String getVigencia() {
		return vigencia;
	}
	/**
	 * @param vigencia the vigencia to set
	 */
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	

	

}
