package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: BincardAlmacenBean.java
 * @description: 
 * @date: 9 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class BincardAlmacenBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tipoOrigen;
	private String codigoAnio;
	private String codigoMes;
	private Integer idAlmacen;
	private Integer idProducto;	
	private String idRegistro;
	private String nombreMes;
	private String nroLote;
	private String nroBincard;
	private String nombreProducto;
	private String nombreUnidad;
	private String nombreAlmacen;
	private String fecha;
	private String concepto;
	private BigDecimal cantidadIngresos;
	private BigDecimal cantidadSalidas;
	private BigDecimal cantidadSaldo;
	private BigDecimal precioCompra;
	private BigDecimal precioPromedio;
	private BigDecimal importeValorizado;
	private String motivo;
	private String nroOrden;
	private String tipo;
	
	
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
	 * @return the idRegistro
	 */
	public String getIdRegistro() {
		return idRegistro;
	}
	/**
	 * @param idRegistro the idRegistro to set
	 */
	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}
	/**
	 * @return the nombreMes
	 */
	public String getNombreMes() {
		return nombreMes;
	}
	/**
	 * @param nombreMes the nombreMes to set
	 */
	public void setNombreMes(String nombreMes) {
		this.nombreMes = nombreMes;
	}
	/**
	 * @return the nroLote
	 */
	public String getNroLote() {
		return nroLote;
	}
	/**
	 * @param nroLote the nroLote to set
	 */
	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}
	/**
	 * @return the nroBincard
	 */
	public String getNroBincard() {
		return nroBincard;
	}
	/**
	 * @param nroBincard the nroBincard to set
	 */
	public void setNroBincard(String nroBincard) {
		this.nroBincard = nroBincard;
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
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}
	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * @return the cantidadIngresos
	 */
	public BigDecimal getCantidadIngresos() {
		return cantidadIngresos;
	}
	/**
	 * @param cantidadIngresos the cantidadIngresos to set
	 */
	public void setCantidadIngresos(BigDecimal cantidadIngresos) {
		this.cantidadIngresos = cantidadIngresos;
	}
	/**
	 * @return the cantidadSalidas
	 */
	public BigDecimal getCantidadSalidas() {
		return cantidadSalidas;
	}
	/**
	 * @param cantidadSalidas the cantidadSalidas to set
	 */
	public void setCantidadSalidas(BigDecimal cantidadSalidas) {
		this.cantidadSalidas = cantidadSalidas;
	}
	/**
	 * @return the cantidadSaldo
	 */
	public BigDecimal getCantidadSaldo() {
		return cantidadSaldo;
	}
	/**
	 * @param cantidadSaldo the cantidadSaldo to set
	 */
	public void setCantidadSaldo(BigDecimal cantidadSaldo) {
		this.cantidadSaldo = cantidadSaldo;
	}
	/**
	 * @return the precioCompra
	 */
	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}
	/**
	 * @param precioCompra the precioCompra to set
	 */
	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
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
	 * @return the importeValorizado
	 */
	public BigDecimal getImporteValorizado() {
		return importeValorizado;
	}
	/**
	 * @param importeValorizado the importeValorizado to set
	 */
	public void setImporteValorizado(BigDecimal importeValorizado) {
		this.importeValorizado = importeValorizado;
	}
	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return the nroOrden
	 */
	public String getNroOrden() {
		return nroOrden;
	}
	/**
	 * @param nroOrden the nroOrden to set
	 */
	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
