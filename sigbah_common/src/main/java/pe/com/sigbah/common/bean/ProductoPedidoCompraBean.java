package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ProductoPedidoCompraBean.java
 * @description: 
 * @date: 25 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoPedidoCompraBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idDetallePedidoCompra;
	private Integer idPedidoCompra;
	private Integer idProducto;
	private Integer idCategoria;
	private String nombreProducto;
	private BigDecimal cantidad;
	private BigDecimal precioUnitario;
	private String unidadMedida;
	private BigDecimal importeTotal;

    
  
	
	/**
	 * 
	 */
	public ProductoPedidoCompraBean() {
		super();
	}





	/**
	 * @param idDetallePedidoCompra
	 */
	public ProductoPedidoCompraBean(Integer idDetallePedidoCompra) {
		super();
		this.idDetallePedidoCompra = idDetallePedidoCompra;
	}





	/**
	 * @return the idDetallePedidoCompra
	 */
	public Integer getIdDetallePedidoCompra() {
		return idDetallePedidoCompra;
	}





	/**
	 * @param idDetallePedidoCompra the idDetallePedidoCompra to set
	 */
	public void setIdDetallePedidoCompra(Integer idDetallePedidoCompra) {
		this.idDetallePedidoCompra = idDetallePedidoCompra;
	}





	/**
	 * @return the idPedidoCompra
	 */
	public Integer getIdPedidoCompra() {
		return idPedidoCompra;
	}





	/**
	 * @param idPedidoCompra the idPedidoCompra to set
	 */
	public void setIdPedidoCompra(Integer idPedidoCompra) {
		this.idPedidoCompra = idPedidoCompra;
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

	

}
