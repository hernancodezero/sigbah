package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: StockAlmacenProductoLoteBean.java
 * @description: 
 * @date: 23 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class StockAlmacenProductoLoteBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private String tipoOrigen;
	private Integer idDdi;
	private Integer idAlmacen;
	private Integer idProducto;
	private String nroLote;
	private String lote;
	private BigDecimal cantidadStock;
	private BigDecimal precioUnitario;
	private Integer idCartilla;
	private String control;
	
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
	 * @return the lote
	 */
	public String getLote() {
		return lote;
	}
	/**
	 * @param lote the lote to set
	 */
	public void setLote(String lote) {
		this.lote = lote;
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
	 * @return the precioUnitario
	 */
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}
	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public Integer getIdCartilla() {
		return idCartilla;
	}
	public void setIdCartilla(Integer idCartilla) {
		this.idCartilla = idCartilla;
	}
	public String getControl() {
		return control;
	}
	public void setControl(String control) {
		this.control = control;
	}

}
