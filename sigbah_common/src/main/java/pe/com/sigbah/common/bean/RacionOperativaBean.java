package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: RacionOperativaBean.java
 * @description: 
 * @date: 29/07/2017
 * @author: Junior Huaman Flores.
 */
public class RacionOperativaBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idRacionOperativa;
	private String codigoRacion;
	private String nombreRacion;
	private String codigoRacionOperativa;
	private Integer idDetalleRacionOperativa;
	private Integer diasAtencion;
	private Integer idProducto;
	private String nombreProducto;
	private BigDecimal cantidadRacionKg;
	private BigDecimal pesoUnidadPres;
	private Integer idProgramacion;
	private Integer idRacion;
	private Integer idDdi;
	
	
	/**
	 * @return the idRacionOperativa
	 */
	public Integer getIdRacionOperativa() {
		return idRacionOperativa;
	}
	/**
	 * @param idRacionOperativa the idRacionOperativa to set
	 */
	public void setIdRacionOperativa(Integer idRacionOperativa) {
		this.idRacionOperativa = idRacionOperativa;
	}
	/**
	 * @return the codigoRacion
	 */
	public String getCodigoRacion() {
		return codigoRacion;
	}
	/**
	 * @param codigoRacion the codigoRacion to set
	 */
	public void setCodigoRacion(String codigoRacion) {
		this.codigoRacion = codigoRacion;
	}
	/**
	 * @return the nombreRacion
	 */
	public String getNombreRacion() {
		return nombreRacion;
	}
	/**
	 * @param nombreRacion the nombreRacion to set
	 */
	public void setNombreRacion(String nombreRacion) {
		this.nombreRacion = nombreRacion;
	}
	/**
	 * @return the codigoRacionOperativa
	 */
	public String getCodigoRacionOperativa() {
		return codigoRacionOperativa;
	}
	/**
	 * @param codigoRacionOperativa the codigoRacionOperativa to set
	 */
	public void setCodigoRacionOperativa(String codigoRacionOperativa) {
		this.codigoRacionOperativa = codigoRacionOperativa;
	}
	/**
	 * @return the idDetalleRacionOperativa
	 */
	public Integer getIdDetalleRacionOperativa() {
		return idDetalleRacionOperativa;
	}
	/**
	 * @param idDetalleRacionOperativa the idDetalleRacionOperativa to set
	 */
	public void setIdDetalleRacionOperativa(Integer idDetalleRacionOperativa) {
		this.idDetalleRacionOperativa = idDetalleRacionOperativa;
	}
	/**
	 * @return the diasAtencion
	 */
	public Integer getDiasAtencion() {
		return diasAtencion;
	}
	/**
	 * @param diasAtencion the diasAtencion to set
	 */
	public void setDiasAtencion(Integer diasAtencion) {
		this.diasAtencion = diasAtencion;
	}
	/**
	 * @return the idProducto
	 */
	public Integer getIdProducto() {
		return idProducto;
	}
	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	/**
	 * @return the nombreProducto
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}
	/**
	 * @param nombreProducto the nombreProducto to set
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	/**
	 * @return the cantidadRacionKg
	 */
	public BigDecimal getCantidadRacionKg() {
		return cantidadRacionKg;
	}
	/**
	 * @param cantidadRacionKg the cantidadRacionKg to set
	 */
	public void setCantidadRacionKg(BigDecimal cantidadRacionKg) {
		this.cantidadRacionKg = cantidadRacionKg;
	}
	/**
	 * @return the pesoUnidadPres
	 */
	public BigDecimal getPesoUnidadPres() {
		return pesoUnidadPres;
	}
	/**
	 * @param pesoUnidadPres the pesoUnidadPres to set
	 */
	public void setPesoUnidadPres(BigDecimal pesoUnidadPres) {
		this.pesoUnidadPres = pesoUnidadPres;
	}
	/**
	 * @return the idProgramacion
	 */
	public Integer getIdProgramacion() {
		return idProgramacion;
	}
	/**
	 * @param idProgramacion the idProgramacion to set
	 */
	public void setIdProgramacion(Integer idProgramacion) {
		this.idProgramacion = idProgramacion;
	}
	/**
	 * @return the idRacion
	 */
	public Integer getIdRacion() {
		return idRacion;
	}
	/**
	 * @param idRacion the idRacion to set
	 */
	public void setIdRacion(Integer idRacion) {
		this.idRacion = idRacion;
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
	
}
