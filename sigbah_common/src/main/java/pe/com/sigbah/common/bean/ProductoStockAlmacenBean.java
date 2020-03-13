package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ProductoStockAlmacenBean.java
 * @description: 
 * @date: 22/07/2017
 * @author: Junior Huaman Flores.
 */
public class ProductoStockAlmacenBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private String nombreProducto;
	private String codigoProducto;
	private String codigoSiga;
	private BigDecimal cantidadStock;
	private String unidadMedida;
	private String nombreEnvase;
	private BigDecimal precioPromedio;
	
	
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

}
