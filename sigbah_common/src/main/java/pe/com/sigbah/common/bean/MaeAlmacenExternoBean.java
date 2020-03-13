package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: MaeAlmacenBean.java
 * @description: 
 * @date: 11 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeAlmacenExternoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idAlmacenExterno;
	private Integer idRegion;
	private String NombreAlmacen;
	private String direccion;
	private String codAlmacen;
	private String codUbigeo;
	private String flgActivo;
	private String tipoAlmacen;
	private String nombreUbigeo;
	private String rucUbigeo;
	private String direccionUbigeo;
	private String fecha;
	private String codigoDepartamento;
	private String codigoProvincia;
	
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private String usuario;


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
	 * @return the idAlmacenExterno
	 */
	public Integer getIdAlmacenExterno() {
		return idAlmacenExterno;
	}
	/**
	 * @param idAlmacenExterno the idAlmacenExterno to set
	 */
	public void setIdAlmacenExterno(Integer idAlmacenExterno) {
		this.idAlmacenExterno = idAlmacenExterno;
	}
	/**
	 * @return the idRegion
	 */
	public Integer getIdRegion() {
		return idRegion;
	}
	/**
	 * @param idRegion the idRegion to set
	 */
	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}
	/**
	 * @return the tipoAlmacen
	 */
	public String getTipoAlmacen() {
		return tipoAlmacen;
	}
	/**
	 * @param tipoAlmacen the tipoAlmacen to set
	 */
	public void setTipoAlmacen(String tipoAlmacen) {
		this.tipoAlmacen = tipoAlmacen;
	}
	/**
	 * @return the nombreUbigeo
	 */
	public String getNombreUbigeo() {
		return nombreUbigeo;
	}
	/**
	 * @param nombreUbigeo the nombreUbigeo to set
	 */
	public void setNombreUbigeo(String nombreUbigeo) {
		this.nombreUbigeo = nombreUbigeo;
	}
	/**
	 * @return the rucUbigeo
	 */
	public String getRucUbigeo() {
		return rucUbigeo;
	}
	/**
	 * @param rucUbigeo the rucUbigeo to set
	 */
	public void setRucUbigeo(String rucUbigeo) {
		this.rucUbigeo = rucUbigeo;
	}
	/**
	 * @return the direccionUbigeo
	 */
	public String getDireccionUbigeo() {
		return direccionUbigeo;
	}
	/**
	 * @param direccionUbigeo the direccionUbigeo to set
	 */
	public void setDireccionUbigeo(String direccionUbigeo) {
		this.direccionUbigeo = direccionUbigeo;
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
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}
	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}
	public String getCodigoProvincia() {
		return codigoProvincia;
	}
	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	

}
