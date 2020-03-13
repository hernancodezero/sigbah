package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: DetalleProductoControlCalidadBean.java
 * @description: 
 * @date: 25 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DetalleProductoControlCalidadBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal item;
	private Integer idControlCalidad;
	private String nroControlCalidad;
	private String nombreDdi;
	private String nombreAlmacen;
	private String proveedorDestino;
	private String fechaEmision;
	private String nroOrdenCompra;
	private String conclusiones;
	private String recomendaciones;
	private String nombreProducto;
	private BigDecimal cantidadLote;
	private BigDecimal cantidadMuestra;
	private String primario;
	private String secundario;
	private String parOlor;
	private String parColor;
	private String parTextura;
	private String parSabor;
	private String flagConforProducto;
	private String flagEspecTecnicas;
	private String flagTipoProducto;
	private String tipoControlCalidad;
	
	
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
	 * @return the idControlCalidad
	 */
	public Integer getIdControlCalidad() {
		return idControlCalidad;
	}
	/**
	 * @param idControlCalidad the idControlCalidad to set
	 */
	public void setIdControlCalidad(Integer idControlCalidad) {
		this.idControlCalidad = idControlCalidad;
	}
	/**
	 * @return the nroControlCalidad
	 */
	public String getNroControlCalidad() {
		return nroControlCalidad;
	}
	/**
	 * @param nroControlCalidad the nroControlCalidad to set
	 */
	public void setNroControlCalidad(String nroControlCalidad) {
		this.nroControlCalidad = nroControlCalidad;
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
	 * @return the proveedorDestino
	 */
	public String getProveedorDestino() {
		return proveedorDestino;
	}
	/**
	 * @param proveedorDestino the proveedorDestino to set
	 */
	public void setProveedorDestino(String proveedorDestino) {
		this.proveedorDestino = proveedorDestino;
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
	 * @return the nroOrdenCompra
	 */
	public String getNroOrdenCompra() {
		return nroOrdenCompra;
	}
	/**
	 * @param nroOrdenCompra the nroOrdenCompra to set
	 */
	public void setNroOrdenCompra(String nroOrdenCompra) {
		this.nroOrdenCompra = nroOrdenCompra;
	}
	/**
	 * @return the conclusiones
	 */
	public String getConclusiones() {
		return conclusiones;
	}
	/**
	 * @param conclusiones the conclusiones to set
	 */
	public void setConclusiones(String conclusiones) {
		this.conclusiones = conclusiones;
	}
	/**
	 * @return the recomendaciones
	 */
	public String getRecomendaciones() {
		return recomendaciones;
	}
	/**
	 * @param recomendaciones the recomendaciones to set
	 */
	public void setRecomendaciones(String recomendaciones) {
		this.recomendaciones = recomendaciones;
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
	 * @return the cantidadLote
	 */
	public BigDecimal getCantidadLote() {
		return cantidadLote;
	}
	/**
	 * @param cantidadLote the cantidadLote to set
	 */
	public void setCantidadLote(BigDecimal cantidadLote) {
		this.cantidadLote = cantidadLote;
	}
	/**
	 * @return the cantidadMuestra
	 */
	public BigDecimal getCantidadMuestra() {
		return cantidadMuestra;
	}
	/**
	 * @param cantidadMuestra the cantidadMuestra to set
	 */
	public void setCantidadMuestra(BigDecimal cantidadMuestra) {
		this.cantidadMuestra = cantidadMuestra;
	}
	/**
	 * @return the primario
	 */
	public String getPrimario() {
		return primario;
	}
	/**
	 * @param primario the primario to set
	 */
	public void setPrimario(String primario) {
		this.primario = primario;
	}
	/**
	 * @return the secundario
	 */
	public String getSecundario() {
		return secundario;
	}
	/**
	 * @param secundario the secundario to set
	 */
	public void setSecundario(String secundario) {
		this.secundario = secundario;
	}
	/**
	 * @return the parOlor
	 */
	public String getParOlor() {
		return parOlor;
	}
	/**
	 * @param parOlor the parOlor to set
	 */
	public void setParOlor(String parOlor) {
		this.parOlor = parOlor;
	}
	/**
	 * @return the parColor
	 */
	public String getParColor() {
		return parColor;
	}
	/**
	 * @param parColor the parColor to set
	 */
	public void setParColor(String parColor) {
		this.parColor = parColor;
	}
	/**
	 * @return the parTextura
	 */
	public String getParTextura() {
		return parTextura;
	}
	/**
	 * @param parTextura the parTextura to set
	 */
	public void setParTextura(String parTextura) {
		this.parTextura = parTextura;
	}
	/**
	 * @return the parSabor
	 */
	public String getParSabor() {
		return parSabor;
	}
	/**
	 * @param parSabor the parSabor to set
	 */
	public void setParSabor(String parSabor) {
		this.parSabor = parSabor;
	}
	/**
	 * @return the flagConforProducto
	 */
	public String getFlagConforProducto() {
		return flagConforProducto;
	}
	/**
	 * @param flagConforProducto the flagConforProducto to set
	 */
	public void setFlagConforProducto(String flagConforProducto) {
		this.flagConforProducto = flagConforProducto;
	}
	/**
	 * @return the flagEspecTecnicas
	 */
	public String getFlagEspecTecnicas() {
		return flagEspecTecnicas;
	}
	/**
	 * @param flagEspecTecnicas the flagEspecTecnicas to set
	 */
	public void setFlagEspecTecnicas(String flagEspecTecnicas) {
		this.flagEspecTecnicas = flagEspecTecnicas;
	}
	/**
	 * @return the flagTipoProducto
	 */
	public String getFlagTipoProducto() {
		return flagTipoProducto;
	}
	/**
	 * @param flagTipoProducto the flagTipoProducto to set
	 */
	public void setFlagTipoProducto(String flagTipoProducto) {
		this.flagTipoProducto = flagTipoProducto;
	}
	/**
	 * @return the tipoControlCalidad
	 */
	public String getTipoControlCalidad() {
		return tipoControlCalidad;
	}
	/**
	 * @param tipoControlCalidad the tipoControlCalidad to set
	 */
	public void setTipoControlCalidad(String tipoControlCalidad) {
		this.tipoControlCalidad = tipoControlCalidad;
	}	
	
}
