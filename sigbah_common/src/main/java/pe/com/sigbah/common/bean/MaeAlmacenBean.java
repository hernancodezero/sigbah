package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: MaeAlmacenBean.java
 * @description: 
 * @date: 11 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeAlmacenBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idAlmacen;
	private Integer idDdi;
	private String NombreAlmacen;
	private String codAlmacen;
	private String codDdi;
	private String direccion;
	private String codUbigeo;
	private Integer idModAlmacen;
	private String nombrePropietario;
	private BigDecimal capacidadTotal;
	private BigDecimal capacidadNeta;
	private Integer nroModulos;
	private Integer nroNaves;
	private Integer areaTotal;
	private String caracteristicas;
	private String plazoPosecion;
	private BigDecimal montoAlquiler;
	private BigDecimal adelantoGarantia;
	private String flgActivo;
	private String nombreSecundario;
	private String fecha;
	private String usuario;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private String codAlmacenConcatenado;
	private String nombreDdi;
	private String estado;
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
	 * @return the nombreAlmacen
	 */
	public String getNombreAlmacen() {
		return NombreAlmacen;
	}
	/**
	 * @param nombreAlmacen the nombreAlmacen to set
	 */
	public void setNombreAlmacen(String nombreAlmacen) {
		NombreAlmacen = nombreAlmacen;
	}
	/**
	 * @return the codAlmacen
	 */
	public String getCodAlmacen() {
		return codAlmacen;
	}
	/**
	 * @param codAlmacen the codAlmacen to set
	 */
	public void setCodAlmacen(String codAlmacen) {
		this.codAlmacen = codAlmacen;
	}
	/**
	 * @return the codDdi
	 */
	public String getCodDdi() {
		return codDdi;
	}
	/**
	 * @param codDdi the codDdi to set
	 */
	public void setCodDdi(String codDdi) {
		this.codDdi = codDdi;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the codUbigeo
	 */
	public String getCodUbigeo() {
		return codUbigeo;
	}
	/**
	 * @param codUbigeo the codUbigeo to set
	 */
	public void setCodUbigeo(String codUbigeo) {
		this.codUbigeo = codUbigeo;
	}
	/**
	 * @return the idModAlmacen
	 */
	public Integer getIdModAlmacen() {
		return idModAlmacen;
	}
	/**
	 * @param idModAlmacen the idModAlmacen to set
	 */
	public void setIdModAlmacen(Integer idModAlmacen) {
		this.idModAlmacen = idModAlmacen;
	}
	/**
	 * @return the nombrePropietario
	 */
	public String getNombrePropietario() {
		return nombrePropietario;
	}
	/**
	 * @param nombrePropietario the nombrePropietario to set
	 */
	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}
	/**
	 * @return the capacidadTotal
	 */
	public BigDecimal getCapacidadTotal() {
		return capacidadTotal;
	}
	/**
	 * @param capacidadTotal the capacidadTotal to set
	 */
	public void setCapacidadTotal(BigDecimal capacidadTotal) {
		this.capacidadTotal = capacidadTotal;
	}
	/**
	 * @return the capacidadNeta
	 */
	public BigDecimal getCapacidadNeta() {
		return capacidadNeta;
	}
	/**
	 * @param capacidadNeta the capacidadNeta to set
	 */
	public void setCapacidadNeta(BigDecimal capacidadNeta) {
		this.capacidadNeta = capacidadNeta;
	}
	/**
	 * @return the nroModulos
	 */
	public Integer getNroModulos() {
		return nroModulos;
	}
	/**
	 * @param nroModulos the nroModulos to set
	 */
	public void setNroModulos(Integer nroModulos) {
		this.nroModulos = nroModulos;
	}
	/**
	 * @return the nroNaves
	 */
	public Integer getNroNaves() {
		return nroNaves;
	}
	/**
	 * @param nroNaves the nroNaves to set
	 */
	public void setNroNaves(Integer nroNaves) {
		this.nroNaves = nroNaves;
	}
	/**
	 * @return the areaTotal
	 */
	public Integer getAreaTotal() {
		return areaTotal;
	}
	/**
	 * @param areaTotal the areaTotal to set
	 */
	public void setAreaTotal(Integer areaTotal) {
		this.areaTotal = areaTotal;
	}
	/**
	 * @return the caracteristicas
	 */
	public String getCaracteristicas() {
		return caracteristicas;
	}
	/**
	 * @param caracteristicas the caracteristicas to set
	 */
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	/**
	 * @return the plazoPosecion
	 */
	public String getPlazoPosecion() {
		return plazoPosecion;
	}
	/**
	 * @param plazoPosecion the plazoPosecion to set
	 */
	public void setPlazoPosecion(String plazoPosecion) {
		this.plazoPosecion = plazoPosecion;
	}
	/**
	 * @return the montoAlquiler
	 */
	public BigDecimal getMontoAlquiler() {
		return montoAlquiler;
	}
	/**
	 * @param montoAlquiler the montoAlquiler to set
	 */
	public void setMontoAlquiler(BigDecimal montoAlquiler) {
		this.montoAlquiler = montoAlquiler;
	}
	/**
	 * @return the adelantoGarantia
	 */
	public BigDecimal getAdelantoGarantia() {
		return adelantoGarantia;
	}
	/**
	 * @param adelantoGarantia the adelantoGarantia to set
	 */
	public void setAdelantoGarantia(BigDecimal adelantoGarantia) {
		this.adelantoGarantia = adelantoGarantia;
	}
	/**
	 * @return the flgActivo
	 */
	public String getFlgActivo() {
		return flgActivo;
	}
	/**
	 * @param flgActivo the flgActivo to set
	 */
	public void setFlgActivo(String flgActivo) {
		this.flgActivo = flgActivo;
	}
	/**
	 * @return the nombreSecundario
	 */
	public String getNombreSecundario() {
		return nombreSecundario;
	}
	/**
	 * @param nombreSecundario the nombreSecundario to set
	 */
	public void setNombreSecundario(String nombreSecundario) {
		this.nombreSecundario = nombreSecundario;
	}
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
	 * @return the codAlmacenConcatenado
	 */
	public String getCodAlmacenConcatenado() {
		return codAlmacenConcatenado;
	}
	/**
	 * @param codAlmacenConcatenado the codAlmacenConcatenado to set
	 */
	public void setCodAlmacenConcatenado(String codAlmacenConcatenado) {
		this.codAlmacenConcatenado = codAlmacenConcatenado;
	}
	public String getNombreDdi() {
		return nombreDdi;
	}
	public void setNombreDdi(String nombreDdi) {
		this.nombreDdi = nombreDdi;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	

}
