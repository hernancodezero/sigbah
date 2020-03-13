package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: ResumenStockAlimentoBean.java
 * @description: 
 * @date: 9 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class ResumenStockNoAlimentarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	private String nombreProducto;
	private BigDecimal totalStock;
	private BigDecimal totalConsumo;
	private BigDecimal totalSaldo;
	
	
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
	 * @return the totalStock
	 */
	public BigDecimal getTotalStock() {
		return totalStock;
	}
	/**
	 * @param totalStock the totalStock to set
	 */
	public void setTotalStock(BigDecimal totalStock) {
		this.totalStock = totalStock;
	}
	/**
	 * @return the totalConsumo
	 */
	public BigDecimal getTotalConsumo() {
		return totalConsumo;
	}
	/**
	 * @param totalConsumo the totalConsumo to set
	 */
	public void setTotalConsumo(BigDecimal totalConsumo) {
		this.totalConsumo = totalConsumo;
	}
	/**
	 * @return the totalSaldo
	 */
	public BigDecimal getTotalSaldo() {
		return totalSaldo;
	}
	/**
	 * @param totalSaldo the totalSaldo to set
	 */
	public void setTotalSaldo(BigDecimal totalSaldo) {
		this.totalSaldo = totalSaldo;
	}
	
}
