package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: AlmacenesWSBean.java
 * @description: Clase asociado a almacenes.
 * @date: 14/08/2018
 * @author: Alan Chumpitaz.
 */
public class StockXAlmacenWSBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idAlmacen;
	private String nombreDdiAlmacen;
	private String nombreCategoria;
	private String nombreProducto;
	private BigDecimal cantidad;
	private BigDecimal tonelada;
	
	/**
	 * 
	 */
	public StockXAlmacenWSBean() {
		super();
	}

	/**
	 * @return the idAlmacen
	 */
	public String getIdAlmacen() {
		return idAlmacen;
	}

	/**
	 * @param idAlmacen the idAlmacen to set
	 */
	public void setIdAlmacen(String idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	/**
	 * @return the nombreDdiAlmacen
	 */
	public String getNombreDdiAlmacen() {
		return nombreDdiAlmacen;
	}

	/**
	 * @param nombreDdiAlmacen the nombreDdiAlmacen to set
	 */
	public void setNombreDdiAlmacen(String nombreDdiAlmacen) {
		this.nombreDdiAlmacen = nombreDdiAlmacen;
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
	 * @return the tonelada
	 */
	public BigDecimal getTonelada() {
		return tonelada;
	}

	/**
	 * @param tonelada the tonelada to set
	 */
	public void setTonelada(BigDecimal tonelada) {
		this.tonelada = tonelada;
	}
	
	
}
