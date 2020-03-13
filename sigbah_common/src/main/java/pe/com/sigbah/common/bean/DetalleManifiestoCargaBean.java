package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @className: DetalleManifiestoCargaBean.java
 * @description:
 * @date: 18/02/2015 22:26:30
 * @author: Junior Huaman Flores.
 */
public class DetalleManifiestoCargaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idSalida;
	private Integer idGuiaRemision;
	private String nroManifiestoCarga;
	private String almacenOrigen;
	private String fechaEmision;
	private String ddiRegionDestino;
	private String almacenDestino;
	private String referencia;
	private String tipoTransporte;
	private String nombreChofer;
	private String nroPlacaVehiculo;
	private String nombreProducto;
	private String nombreUnidad;
	private BigDecimal cantidad;
	private BigDecimal pesoUnitario;
	private BigDecimal pesoTotal;
	private BigDecimal volumenUnitario;
	private BigDecimal volumenTotal;
	private String version;
	private String observacionManifiesto;
	
	private List<DetalleManifiestoCargaBean> lstCabecera;
	private List<DetalleManifiestoCargaBean> lstDetalle;
	
	
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
	 * @return the almacenOrigen
	 */
	public String getAlmacenOrigen() {
		return almacenOrigen;
	}
	/**
	 * @param almacenOrigen the almacenOrigen to set
	 */
	public void setAlmacenOrigen(String almacenOrigen) {
		this.almacenOrigen = almacenOrigen;
	}
	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return the ddiRegionDestino
	 */
	public String getDdiRegionDestino() {
		return ddiRegionDestino;
	}
	/**
	 * @param ddiRegionDestino the ddiRegionDestino to set
	 */
	public void setDdiRegionDestino(String ddiRegionDestino) {
		this.ddiRegionDestino = ddiRegionDestino;
	}
	/**
	 * @return the almacenDestino
	 */
	public String getAlmacenDestino() {
		return almacenDestino;
	}
	/**
	 * @param almacenDestino the almacenDestino to set
	 */
	public void setAlmacenDestino(String almacenDestino) {
		this.almacenDestino = almacenDestino;
	}
	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}
	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	/**
	 * @return the tipoTransporte
	 */
	public String getTipoTransporte() {
		return tipoTransporte;
	}
	/**
	 * @param tipoTransporte the tipoTransporte to set
	 */
	public void setTipoTransporte(String tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
	}
	/**
	 * @return the nombreChofer
	 */
	public String getNombreChofer() {
		return nombreChofer;
	}
	/**
	 * @param nombreChofer the nombreChofer to set
	 */
	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}
	/**
	 * @return the nroPlacaVehiculo
	 */
	public String getNroPlacaVehiculo() {
		return nroPlacaVehiculo;
	}
	/**
	 * @param nroPlacaVehiculo the nroPlacaVehiculo to set
	 */
	public void setNroPlacaVehiculo(String nroPlacaVehiculo) {
		this.nroPlacaVehiculo = nroPlacaVehiculo;
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
	 * @return the nombreUnidad
	 */
	public String getNombreUnidad() {
		return nombreUnidad;
	}
	/**
	 * @param nombreUnidad the nombreUnidad to set
	 */
	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
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
	 * @return the pesoUnitario
	 */
	public BigDecimal getPesoUnitario() {
		return pesoUnitario;
	}
	/**
	 * @param pesoUnitario the pesoUnitario to set
	 */
	public void setPesoUnitario(BigDecimal pesoUnitario) {
		this.pesoUnitario = pesoUnitario;
	}
	/**
	 * @return the pesoTotal
	 */
	public BigDecimal getPesoTotal() {
		return pesoTotal;
	}
	/**
	 * @param pesoTotal the pesoTotal to set
	 */
	public void setPesoTotal(BigDecimal pesoTotal) {
		this.pesoTotal = pesoTotal;
	}
	/**
	 * @return the volumenUnitario
	 */
	public BigDecimal getVolumenUnitario() {
		return volumenUnitario;
	}
	/**
	 * @param volumenUnitario the volumenUnitario to set
	 */
	public void setVolumenUnitario(BigDecimal volumenUnitario) {
		this.volumenUnitario = volumenUnitario;
	}
	/**
	 * @return the volumenTotal
	 */
	public BigDecimal getVolumenTotal() {
		return volumenTotal;
	}
	/**
	 * @param volumenTotal the volumenTotal to set
	 */
	public void setVolumenTotal(BigDecimal volumenTotal) {
		this.volumenTotal = volumenTotal;
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
	 * @return the observacionManifiesto
	 */
	public String getObservacionManifiesto() {
		return observacionManifiesto;
	}
	/**
	 * @param observacionManifiesto the observacionManifiesto to set
	 */
	public void setObservacionManifiesto(String observacionManifiesto) {
		this.observacionManifiesto = observacionManifiesto;
	}
	/**
	 * @return the nroManifiestoCarga
	 */
	public String getNroManifiestoCarga() {
		return nroManifiestoCarga;
	}
	/**
	 * @param nroManifiestoCarga the nroManifiestoCarga to set
	 */
	public void setNroManifiestoCarga(String nroManifiestoCarga) {
		this.nroManifiestoCarga = nroManifiestoCarga;
	}
	/**
	 * @return the lstCabecera
	 */
	public List<DetalleManifiestoCargaBean> getLstCabecera() {
		return lstCabecera;
	}
	/**
	 * @param lstCabecera the lstCabecera to set
	 */
	public void setLstCabecera(List<DetalleManifiestoCargaBean> lstCabecera) {
		this.lstCabecera = lstCabecera;
	}
	/**
	 * @return the lstDetalle
	 */
	public List<DetalleManifiestoCargaBean> getLstDetalle() {
		return lstDetalle;
	}
	/**
	 * @param lstDetalle the lstDetalle to set
	 */
	public void setLstDetalle(List<DetalleManifiestoCargaBean> lstDetalle) {
		this.lstDetalle = lstDetalle;
	}

}