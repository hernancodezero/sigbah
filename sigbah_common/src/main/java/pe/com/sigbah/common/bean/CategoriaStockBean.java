package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: CategoriaStockBean.java
 * @description: Clase asociado al categoria stock.
 * @date: 28/07/2018
 * @author: Sumerio.
 */
public class CategoriaStockBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer nroFila;
	private Integer idDdi;
	private Integer idAlmacen;
	private Integer idProducto;
	private Integer idCategoria;
	private String nombreCategoria;
	private String nombreProducto;
	private BigDecimal cantidadProducto;
	private BigDecimal cantidadToneladas;
	
	
	/**
	 * 
	 */
	public CategoriaStockBean() {
		super();
	}
	/**
	 * @param idCategoria
	 */
	public CategoriaStockBean(Integer idCategoria) {
		super();
		this.idCategoria = idCategoria;
	}
	/**
	 * @return the nroFila
	 */
	public Integer getNroFila() {
		return nroFila;
	}
	/**
	 * @param nroFila the nroFila to set
	 */
	public void setNroFila(Integer nroFila) {
		this.nroFila = nroFila;
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
	 * @return the cantidadProducto
	 */
	public BigDecimal getCantidadProducto() {
		return cantidadProducto;
	}
	/**
	 * @param cantidadProducto the cantidadProducto to set
	 */
	public void setCantidadProducto(BigDecimal cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	/**
	 * @return the cantidadToneladas
	 */
	public BigDecimal getCantidadToneladas() {
		return cantidadToneladas;
	}
	/**
	 * @param cantidadToneladas the cantidadToneladas to set
	 */
	public void setCantidadToneladas(BigDecimal cantidadToneladas) {
		this.cantidadToneladas = cantidadToneladas;
	}

}
