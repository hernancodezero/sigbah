package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: DetalleGuiaRemisionBean.java
 * @description: 
 * @date: 18 de jul. de 2017
 * @author: SUMERIO.
 */
public class DetalleGuiaRemisionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal item;
	private Integer idSalida;
	private String nroGuiaEmision;
	private String sede;
	private String direccionPartida;
	private String puntoPartida;	
	private String fechaEmision;
	private String fechaInicioTraslado;
	private String empresaTransporte;
	private String rucEmpresaTransporte;
	private String nombreChofer;
	private String nroPlaca;
	private String puntoLlegada;
	private String razonSocialDestino;
	private String rucDestino;
	private String nombreProducto;	
	private BigDecimal cantidad;
	private String unidadMedida;
	private BigDecimal pesoTotal;
	private String observacionGuia;
	private String tipoMovimiento;	
	private Integer idMotivoTraslado;
	private String motivoTraslado;
	private String nroLicenciaConducir;
	private String codigoAnio;
	private String codigoMesInicio;
	private String codigoMesFin;
	private Integer idAlmacen;
	private String tipoOrigen;
	private Integer idMovimiento;
	private String nombreDdi;
	private String nombreAlmacen;
	private String nroOrdenSalida;
	private String nroGuiaRemision;
	private String nroManifiestoCarga;
	private String nroActa;
	private String nombreMovimiento;
	private String nombreEstado;
	private String direccion;
	private Integer idProducto;
	
	private Integer idEstado;
	
	private String direccionFiscal;
	private String nombreImprenta;
	private String rucImprenta;
	private String nroSerie;
	private String nroAutorizacion;
	private String fechaImpresion;
	
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
	 * @return the nroGuiaEmision
	 */
	public String getNroGuiaEmision() {
		return nroGuiaEmision;
	}
	/**
	 * @param nroGuiaEmision the nroGuiaEmision to set
	 */
	public void setNroGuiaEmision(String nroGuiaEmision) {
		this.nroGuiaEmision = nroGuiaEmision;
	}
	/**
	 * @return the sede
	 */
	public String getSede() {
		return sede;
	}
	/**
	 * @param sede the sede to set
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}
	/**
	 * @return the direccionPartida
	 */
	public String getDireccionPartida() {
		return direccionPartida;
	}
	/**
	 * @param direccionPartida the direccionPartida to set
	 */
	public void setDireccionPartida(String direccionPartida) {
		this.direccionPartida = direccionPartida;
	}
	/**
	 * @return the puntoPartida
	 */
	public String getPuntoPartida() {
		return puntoPartida;
	}
	/**
	 * @param puntoPartida the puntoPartida to set
	 */
	public void setPuntoPartida(String puntoPartida) {
		this.puntoPartida = puntoPartida;
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
	 * @return the fechaInicioTraslado
	 */
	public String getFechaInicioTraslado() {
		return fechaInicioTraslado;
	}
	/**
	 * @param fechaInicioTraslado the fechaInicioTraslado to set
	 */
	public void setFechaInicioTraslado(String fechaInicioTraslado) {
		this.fechaInicioTraslado = fechaInicioTraslado;
	}
	/**
	 * @return the empresaTransporte
	 */
	public String getEmpresaTransporte() {
		return empresaTransporte;
	}
	/**
	 * @param empresaTransporte the empresaTransporte to set
	 */
	public void setEmpresaTransporte(String empresaTransporte) {
		this.empresaTransporte = empresaTransporte;
	}
	/**
	 * @return the rucEmpresaTransporte
	 */
	public String getRucEmpresaTransporte() {
		return rucEmpresaTransporte;
	}
	/**
	 * @param rucEmpresaTransporte the rucEmpresaTransporte to set
	 */
	public void setRucEmpresaTransporte(String rucEmpresaTransporte) {
		this.rucEmpresaTransporte = rucEmpresaTransporte;
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
	 * @return the nroPlaca
	 */
	public String getNroPlaca() {
		return nroPlaca;
	}
	/**
	 * @param nroPlaca the nroPlaca to set
	 */
	public void setNroPlaca(String nroPlaca) {
		this.nroPlaca = nroPlaca;
	}
	/**
	 * @return the puntoLlegada
	 */
	public String getPuntoLlegada() {
		return puntoLlegada;
	}
	/**
	 * @param puntoLlegada the puntoLlegada to set
	 */
	public void setPuntoLlegada(String puntoLlegada) {
		this.puntoLlegada = puntoLlegada;
	}
	/**
	 * @return the razonSocialDestino
	 */
	public String getRazonSocialDestino() {
		return razonSocialDestino;
	}
	/**
	 * @param razonSocialDestino the razonSocialDestino to set
	 */
	public void setRazonSocialDestino(String razonSocialDestino) {
		this.razonSocialDestino = razonSocialDestino;
	}
	/**
	 * @return the rucDestino
	 */
	public String getRucDestino() {
		return rucDestino;
	}
	/**
	 * @param rucDestino the rucDestino to set
	 */
	public void setRucDestino(String rucDestino) {
		this.rucDestino = rucDestino;
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
	 * @return the observacionGuia
	 */
	public String getObservacionGuia() {
		return observacionGuia;
	}
	/**
	 * @param observacionGuia the observacionGuia to set
	 */
	public void setObservacionGuia(String observacionGuia) {
		this.observacionGuia = observacionGuia;
	}
	/**
	 * @return the tipoMovimiento
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	/**
	 * @param tipoMovimiento the tipoMovimiento to set
	 */
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	/**
	 * @return the idMotivoTraslado
	 */
	public Integer getIdMotivoTraslado() {
		return idMotivoTraslado;
	}
	/**
	 * @param idMotivoTraslado the idMotivoTraslado to set
	 */
	public void setIdMotivoTraslado(Integer idMotivoTraslado) {
		this.idMotivoTraslado = idMotivoTraslado;
	}
	/**
	 * @return the motivoTraslado
	 */
	public String getMotivoTraslado() {
		return motivoTraslado;
	}
	/**
	 * @param motivoTraslado the motivoTraslado to set
	 */
	public void setMotivoTraslado(String motivoTraslado) {
		this.motivoTraslado = motivoTraslado;
	}
	/**
	 * @return the nroLicenciaConducir
	 */
	public String getNroLicenciaConducir() {
		return nroLicenciaConducir;
	}
	/**
	 * @param nroLicenciaConducir the nroLicenciaConducir to set
	 */
	public void setNroLicenciaConducir(String nroLicenciaConducir) {
		this.nroLicenciaConducir = nroLicenciaConducir;
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
	 * @return the item
	 */
	public BigDecimal getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(BigDecimal item) {
		this.item = item;
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
	 * @return the codigoMesInicio
	 */
	public String getCodigoMesInicio() {
		return codigoMesInicio;
	}
	/**
	 * @param codigoMesInicio the codigoMesInicio to set
	 */
	public void setCodigoMesInicio(String codigoMesInicio) {
		this.codigoMesInicio = codigoMesInicio;
	}
	/**
	 * @return the codigoMesFin
	 */
	public String getCodigoMesFin() {
		return codigoMesFin;
	}
	/**
	 * @param codigoMesFin the codigoMesFin to set
	 */
	public void setCodigoMesFin(String codigoMesFin) {
		this.codigoMesFin = codigoMesFin;
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
	 * @return the tipoOrigen
	 */
	public String getTipoOrigen() {
		return tipoOrigen;
	}
	/**
	 * @param tipoOrigen the tipoOrigen to set
	 */
	public void setTipoOrigen(String tipoOrigen) {
		this.tipoOrigen = tipoOrigen;
	}
	/**
	 * @return the idMovimiento
	 */
	public Integer getIdMovimiento() {
		return idMovimiento;
	}
	/**
	 * @param idMovimiento the idMovimiento to set
	 */
	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
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
	 * @return the nroOrdenSalida
	 */
	public String getNroOrdenSalida() {
		return nroOrdenSalida;
	}
	/**
	 * @param nroOrdenSalida the nroOrdenSalida to set
	 */
	public void setNroOrdenSalida(String nroOrdenSalida) {
		this.nroOrdenSalida = nroOrdenSalida;
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
	 * @return the nombreMovimiento
	 */
	public String getNombreMovimiento() {
		return nombreMovimiento;
	}
	/**
	 * @param nombreMovimiento the nombreMovimiento to set
	 */
	public void setNombreMovimiento(String nombreMovimiento) {
		this.nombreMovimiento = nombreMovimiento;
	}
	/**
	 * @return the nombreEstado
	 */
	public String getNombreEstado() {
		return nombreEstado;
	}
	/**
	 * @param nombreEstado the nombreEstado to set
	 */
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	/**
	 * @return the idEstado
	 */
	public Integer getIdEstado() {
		return idEstado;
	}
	/**
	 * @param idEstado the idEstado to set
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	/**
	 * @return the direccionFiscal
	 */
	public String getDireccionFiscal() {
		return direccionFiscal;
	}
	/**
	 * @param direccionFiscal the direccionFiscal to set
	 */
	public void setDireccionFiscal(String direccionFiscal) {
		this.direccionFiscal = direccionFiscal;
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
	 * @return the fechaImpresion
	 */
	public String getFechaImpresion() {
		return fechaImpresion;
	}
	/**
	 * @param fechaImpresion the fechaImpresion to set
	 */
	public void setFechaImpresion(String fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

}
