package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: StockAlmacenBean.java
 * @description: 
 * @date: 23 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class StockAlmacenBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private String tipoOrigen;
	private Integer idDdi;
	private Integer idAlmacen;
	private Integer idProducto;
	private String codigoProducto;
	private String nombreProducto;
	private Integer idCategoria;
	private String codigoCategoria;
	private String nombreCategoria;
	private String nombreUnidadMedida;
	private String nombreEnvase;
	private BigDecimal precioUnitarioPromedio;
	private BigDecimal cantidad;	
	private String nombreAlmacen;
	private String nroKardex;
	private String codigoSiga;
	private String nombreEnvasePrimario;
	private BigDecimal pesoUnitarioNeto;
	private BigDecimal pesoUnitarioBruto;
	private BigDecimal dimLargo;
	private BigDecimal dimAlto;
	private BigDecimal dimAncho;
	private BigDecimal volumenUnitario;
	private BigDecimal volumenTotal;
	private BigDecimal stockSeguridad;
	private BigDecimal cantidadStock;
	private BigDecimal precioPromedio;
	private Integer idTipoEnvaseSecundario;
	private String nombreEnvaseSecundario;
	private String descripcionEnvaseSecundario;
	private BigDecimal unidadesEnvaseSecundario;
	private BigDecimal cantidadEnvases;
	private BigDecimal unidadesSueltas;
	private String observacion;
	private String codigoAlmacen;
	
	private String nombreDdi;
	private BigDecimal importeTotal;
	private String tipoStock;
	private String nombreSistema;
	private String estado;
	
	
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
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
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
	 * @return the nombreCategoria
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	/**
	 * @param nombreCategoria the nombreCategoria to set
	 */
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	/**
	 * @return the nombreUnidadMedida
	 */
	public String getNombreUnidadMedida() {
		return nombreUnidadMedida;
	}
	/**
	 * @param nombreUnidadMedida the nombreUnidadMedida to set
	 */
	public void setNombreUnidadMedida(String nombreUnidadMedida) {
		this.nombreUnidadMedida = nombreUnidadMedida;
	}
	/**
	 * @return the nombreEnvase
	 */
	public String getNombreEnvase() {
		return nombreEnvase;
	}
	/**
	 * @param nombreEnvase the nombreEnvase to set
	 */
	public void setNombreEnvase(String nombreEnvase) {
		this.nombreEnvase = nombreEnvase;
	}
	/**
	 * @return the precioUnitarioPromedio
	 */
	public BigDecimal getPrecioUnitarioPromedio() {
		return precioUnitarioPromedio;
	}
	/**
	 * @param precioUnitarioPromedio the precioUnitarioPromedio to set
	 */
	public void setPrecioUnitarioPromedio(BigDecimal precioUnitarioPromedio) {
		this.precioUnitarioPromedio = precioUnitarioPromedio;
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
	 * @return the nroKardex
	 */
	public String getNroKardex() {
		return nroKardex;
	}
	/**
	 * @param nroKardex the nroKardex to set
	 */
	public void setNroKardex(String nroKardex) {
		this.nroKardex = nroKardex;
	}
	/**
	 * @return the codigoSiga
	 */
	public String getCodigoSiga() {
		return codigoSiga;
	}
	/**
	 * @param codigoSiga the codigoSiga to set
	 */
	public void setCodigoSiga(String codigoSiga) {
		this.codigoSiga = codigoSiga;
	}
	/**
	 * @return the nombreEnvasePrimario
	 */
	public String getNombreEnvasePrimario() {
		return nombreEnvasePrimario;
	}
	/**
	 * @param nombreEnvasePrimario the nombreEnvasePrimario to set
	 */
	public void setNombreEnvasePrimario(String nombreEnvasePrimario) {
		this.nombreEnvasePrimario = nombreEnvasePrimario;
	}
	/**
	 * @return the pesoUnitarioNeto
	 */
	public BigDecimal getPesoUnitarioNeto() {
		return pesoUnitarioNeto;
	}
	/**
	 * @param pesoUnitarioNeto the pesoUnitarioNeto to set
	 */
	public void setPesoUnitarioNeto(BigDecimal pesoUnitarioNeto) {
		this.pesoUnitarioNeto = pesoUnitarioNeto;
	}
	/**
	 * @return the pesoUnitarioBruto
	 */
	public BigDecimal getPesoUnitarioBruto() {
		return pesoUnitarioBruto;
	}
	/**
	 * @param pesoUnitarioBruto the pesoUnitarioBruto to set
	 */
	public void setPesoUnitarioBruto(BigDecimal pesoUnitarioBruto) {
		this.pesoUnitarioBruto = pesoUnitarioBruto;
	}
	/**
	 * @return the dimLargo
	 */
	public BigDecimal getDimLargo() {
		return dimLargo;
	}
	/**
	 * @param dimLargo the dimLargo to set
	 */
	public void setDimLargo(BigDecimal dimLargo) {
		this.dimLargo = dimLargo;
	}
	/**
	 * @return the dimAlto
	 */
	public BigDecimal getDimAlto() {
		return dimAlto;
	}
	/**
	 * @param dimAlto the dimAlto to set
	 */
	public void setDimAlto(BigDecimal dimAlto) {
		this.dimAlto = dimAlto;
	}
	/**
	 * @return the dimAncho
	 */
	public BigDecimal getDimAncho() {
		return dimAncho;
	}
	/**
	 * @param dimAncho the dimAncho to set
	 */
	public void setDimAncho(BigDecimal dimAncho) {
		this.dimAncho = dimAncho;
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
	 * @return the stockSeguridad
	 */
	public BigDecimal getStockSeguridad() {
		return stockSeguridad;
	}
	/**
	 * @param stockSeguridad the stockSeguridad to set
	 */
	public void setStockSeguridad(BigDecimal stockSeguridad) {
		this.stockSeguridad = stockSeguridad;
	}
	/**
	 * @return the cantidadStock
	 */
	public BigDecimal getCantidadStock() {
		return cantidadStock;
	}
	/**
	 * @param cantidadStock the cantidadStock to set
	 */
	public void setCantidadStock(BigDecimal cantidadStock) {
		this.cantidadStock = cantidadStock;
	}
	/**
	 * @return the precioPromedio
	 */
	public BigDecimal getPrecioPromedio() {
		return precioPromedio;
	}
	/**
	 * @param precioPromedio the precioPromedio to set
	 */
	public void setPrecioPromedio(BigDecimal precioPromedio) {
		this.precioPromedio = precioPromedio;
	}
	/**
	 * @return the idTipoEnvaseSecundario
	 */
	public Integer getIdTipoEnvaseSecundario() {
		return idTipoEnvaseSecundario;
	}
	/**
	 * @param idTipoEnvaseSecundario the idTipoEnvaseSecundario to set
	 */
	public void setIdTipoEnvaseSecundario(Integer idTipoEnvaseSecundario) {
		this.idTipoEnvaseSecundario = idTipoEnvaseSecundario;
	}
	/**
	 * @return the nombreEnvaseSecundario
	 */
	public String getNombreEnvaseSecundario() {
		return nombreEnvaseSecundario;
	}
	/**
	 * @param nombreEnvaseSecundario the nombreEnvaseSecundario to set
	 */
	public void setNombreEnvaseSecundario(String nombreEnvaseSecundario) {
		this.nombreEnvaseSecundario = nombreEnvaseSecundario;
	}
	/**
	 * @return the descripcionEnvaseSecundario
	 */
	public String getDescripcionEnvaseSecundario() {
		return descripcionEnvaseSecundario;
	}
	/**
	 * @param descripcionEnvaseSecundario the descripcionEnvaseSecundario to set
	 */
	public void setDescripcionEnvaseSecundario(String descripcionEnvaseSecundario) {
		this.descripcionEnvaseSecundario = descripcionEnvaseSecundario;
	}
	/**
	 * @return the unidadesEnvaseSecundario
	 */
	public BigDecimal getUnidadesEnvaseSecundario() {
		return unidadesEnvaseSecundario;
	}
	/**
	 * @param unidadesEnvaseSecundario the unidadesEnvaseSecundario to set
	 */
	public void setUnidadesEnvaseSecundario(BigDecimal unidadesEnvaseSecundario) {
		this.unidadesEnvaseSecundario = unidadesEnvaseSecundario;
	}
	/**
	 * @return the cantidadEnvases
	 */
	public BigDecimal getCantidadEnvases() {
		return cantidadEnvases;
	}
	/**
	 * @param cantidadEnvases the cantidadEnvases to set
	 */
	public void setCantidadEnvases(BigDecimal cantidadEnvases) {
		this.cantidadEnvases = cantidadEnvases;
	}
	/**
	 * @return the unidadesSueltas
	 */
	public BigDecimal getUnidadesSueltas() {
		return unidadesSueltas;
	}
	/**
	 * @param unidadesSueltas the unidadesSueltas to set
	 */
	public void setUnidadesSueltas(BigDecimal unidadesSueltas) {
		this.unidadesSueltas = unidadesSueltas;
	}
	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	/**
	 * @return the idCategoria
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}
	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	/**
	 * @return the codigoCategoria
	 */
	public String getCodigoCategoria() {
		return codigoCategoria;
	}
	/**
	 * @param codigoCategoria the codigoCategoria to set
	 */
	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
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
	 * @return the importeTotal
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}
	/**
	 * @param importeTotal the importeTotal to set
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}
	/**
	 * @return the tipoStock
	 */
	public String getTipoStock() {
		return tipoStock;
	}
	/**
	 * @param tipoStock the tipoStock to set
	 */
	public void setTipoStock(String tipoStock) {
		this.tipoStock = tipoStock;
	}
	/**
	 * @return the nombreSistema
	 */
	public String getNombreSistema() {
		return nombreSistema;
	}
	/**
	 * @param nombreSistema the nombreSistema to set
	 */
	public void setNombreSistema(String nombreSistema) {
		this.nombreSistema = nombreSistema;
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
