package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @className: DetalleActaEntregaBean.java
 * @description: 
 * @date: 18 de jul. de 2017
 * @author: SUMERIO.
 */
public class DetalleActaEntregaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idSalida;
	private Integer idGuiaRemision;	
	private String nroActa;
	private String almacenDestino1;
	private String fechaEmisionActa;
	private String nombreProducto;
	private String unidadMedida;
	private BigDecimal cantidad;
	private String nroGuiaRemision;
	private String fechaEmisionGuia;
	private String almacenDestino2;
	private String observacionActa;
	private String version;
	
	private List<DetalleActaEntregaBean> lstCabecera;
	private List<DetalleActaEntregaBean> lstDetalle;
	
	
	/**
	 * @return the idSalida
	 */
	public Integer getIdSalida() {
		return idSalida;
	}
	/**
	 * @param idSalida the idSalida to set
	 */
	public void setIdSalida(Integer idSalida) {
		this.idSalida = idSalida;
	}
	/**
	 * @return the idGuiaRemision
	 */
	public Integer getIdGuiaRemision() {
		return idGuiaRemision;
	}
	/**
	 * @param idGuiaRemision the idGuiaRemision to set
	 */
	public void setIdGuiaRemision(Integer idGuiaRemision) {
		this.idGuiaRemision = idGuiaRemision;
	}
	/**
	 * @return the nroActa
	 */
	public String getNroActa() {
		return nroActa;
	}
	/**
	 * @param nroActa the nroActa to set
	 */
	public void setNroActa(String nroActa) {
		this.nroActa = nroActa;
	}
	/**
	 * @return the almacenDestino1
	 */
	public String getAlmacenDestino1() {
		return almacenDestino1;
	}
	/**
	 * @param almacenDestino1 the almacenDestino1 to set
	 */
	public void setAlmacenDestino1(String almacenDestino1) {
		this.almacenDestino1 = almacenDestino1;
	}
	/**
	 * @return the fechaEmisionActa
	 */
	public String getFechaEmisionActa() {
		return fechaEmisionActa;
	}
	/**
	 * @param fechaEmisionActa the fechaEmisionActa to set
	 */
	public void setFechaEmisionActa(String fechaEmisionActa) {
		this.fechaEmisionActa = fechaEmisionActa;
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
	 * @return the unidadMedida
	 */
	public String getUnidadMedida() {
		return unidadMedida;
	}
	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	/**
	 * @return the cantidad
	 */
	public BigDecimal getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the nroGuiaRemision
	 */
	public String getNroGuiaRemision() {
		return nroGuiaRemision;
	}
	/**
	 * @param nroGuiaRemision the nroGuiaRemision to set
	 */
	public void setNroGuiaRemision(String nroGuiaRemision) {
		this.nroGuiaRemision = nroGuiaRemision;
	}
	/**
	 * @return the fechaEmisionGuia
	 */
	public String getFechaEmisionGuia() {
		return fechaEmisionGuia;
	}
	/**
	 * @param fechaEmisionGuia the fechaEmisionGuia to set
	 */
	public void setFechaEmisionGuia(String fechaEmisionGuia) {
		this.fechaEmisionGuia = fechaEmisionGuia;
	}
	/**
	 * @return the almacenDestino2
	 */
	public String getAlmacenDestino2() {
		return almacenDestino2;
	}
	/**
	 * @param almacenDestino2 the almacenDestino2 to set
	 */
	public void setAlmacenDestino2(String almacenDestino2) {
		this.almacenDestino2 = almacenDestino2;
	}
	/**
	 * @return the observacionActa
	 */
	public String getObservacionActa() {
		return observacionActa;
	}
	/**
	 * @param observacionActa the observacionActa to set
	 */
	public void setObservacionActa(String observacionActa) {
		this.observacionActa = observacionActa;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the lstCabecera
	 */
	public List<DetalleActaEntregaBean> getLstCabecera() {
		return lstCabecera;
	}
	/**
	 * @param lstCabecera the lstCabecera to set
	 */
	public void setLstCabecera(List<DetalleActaEntregaBean> lstCabecera) {
		this.lstCabecera = lstCabecera;
	}
	/**
	 * @return the lstDetalle
	 */
	public List<DetalleActaEntregaBean> getLstDetalle() {
		return lstDetalle;
	}
	/**
	 * @param lstDetalle the lstDetalle to set
	 */
	public void setLstDetalle(List<DetalleActaEntregaBean> lstDetalle) {
		this.lstDetalle = lstDetalle;
	}
	
	

}
