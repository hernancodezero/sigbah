package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: PedidoCompraBean.java
 * @description: 
 * @date: 30 jul. 2017
 * @author: whr.
 */
public class PedidoCompraReporteBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private String nombreSistema;
	private String numPedidoCompra;
	private String fecPedido;
	private String descripcion;
	private String dee;
	private Integer ideDetPedido;
	private String nomProducto;
	private String nombreUniMed;
	private BigDecimal cantidad;
	private BigDecimal precioUni;
	private BigDecimal importeTotal;
	private Integer fkIdCategoria;
	private Integer fkIdProducto;
	private Integer tipoPedido;
	private String motivoCompra;
	
	/**
	 * 
	 */
	public PedidoCompraReporteBean() {
		super();
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
	 * @return the numPedidoCompra
	 */
	public String getNumPedidoCompra() {
		return numPedidoCompra;
	}


	/**
	 * @param numPedidoCompra the numPedidoCompra to set
	 */
	public void setNumPedidoCompra(String numPedidoCompra) {
		this.numPedidoCompra = numPedidoCompra;
	}


	/**
	 * @return the fecPedido
	 */
	public String getFecPedido() {
		return fecPedido;
	}


	/**
	 * @param fecPedido the fecPedido to set
	 */
	public void setFecPedido(String fecPedido) {
		this.fecPedido = fecPedido;
	}


	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * @return the dee
	 */
	public String getDee() {
		return dee;
	}


	/**
	 * @param dee the dee to set
	 */
	public void setDee(String dee) {
		this.dee = dee;
	}


	/**
	 * @return the ideDetPedido
	 */
	public Integer getIdeDetPedido() {
		return ideDetPedido;
	}


	/**
	 * @param ideDetPedido the ideDetPedido to set
	 */
	public void setIdeDetPedido(Integer ideDetPedido) {
		this.ideDetPedido = ideDetPedido;
	}


	/**
	 * @return the nomProducto
	 */
	public String getNomProducto() {
		return nomProducto;
	}


	/**
	 * @param nomProducto the nomProducto to set
	 */
	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}


	/**
	 * @return the nombreUniMed
	 */
	public String getNombreUniMed() {
		return nombreUniMed;
	}


	/**
	 * @param nombreUniMed the nombreUniMed to set
	 */
	public void setNombreUniMed(String nombreUniMed) {
		this.nombreUniMed = nombreUniMed;
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
	 * @return the precioUni
	 */
	public BigDecimal getPrecioUni() {
		return precioUni;
	}


	/**
	 * @param precioUni the precioUni to set
	 */
	public void setPrecioUni(BigDecimal precioUni) {
		this.precioUni = precioUni;
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
	 * @return the fkIdCategoria
	 */
	public Integer getFkIdCategoria() {
		return fkIdCategoria;
	}


	/**
	 * @param fkIdCategoria the fkIdCategoria to set
	 */
	public void setFkIdCategoria(Integer fkIdCategoria) {
		this.fkIdCategoria = fkIdCategoria;
	}


	/**
	 * @return the fkIdProducto
	 */
	public Integer getFkIdProducto() {
		return fkIdProducto;
	}


	/**
	 * @param fkIdProducto the fkIdProducto to set
	 */
	public void setFkIdProducto(Integer fkIdProducto) {
		this.fkIdProducto = fkIdProducto;
	}


	

	/**
	 * @return the tipoPedido
	 */
	public Integer getTipoPedido() {
		return tipoPedido;
	}


	/**
	 * @param tipoPedido the tipoPedido to set
	 */
	public void setTipoPedido(Integer tipoPedido) {
		this.tipoPedido = tipoPedido;
	}


	/**
	 * @return the motivoCompra
	 */
	public String getMotivoCompra() {
		return motivoCompra;
	}


	/**
	 * @param motivoCompra the motivoCompra to set
	 */
	public void setMotivoCompra(String motivoCompra) {
		this.motivoCompra = motivoCompra;
	}

	
	
}
