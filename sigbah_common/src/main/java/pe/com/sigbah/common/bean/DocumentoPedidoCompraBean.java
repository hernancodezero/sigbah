package pe.com.sigbah.common.bean;

/**
 * @className: DocumentoPedidoCompraBean.java
 * @description: 
 * @date: 25 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DocumentoPedidoCompraBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idDocumentoPedidoCompra;//
	private Integer idPedidoCompra;// FK_IDE_PEDIDO_COMPRA
	private Integer idTipoDocumento;
	private String nombreDocumento;
	private String nroDocumento;
	private String fechaDocumento;
	private String observacion;
	private String codigoArchivoAlfresco;
	private String nombreArchivo;
	private Integer flgActivo;
	/**
	 * 
	 */
	public DocumentoPedidoCompraBean() {
		super();
	}

	

	/**
	 * @param idDocumentoPedidoCompra
	 */
	public DocumentoPedidoCompraBean(Integer idDocumentoPedidoCompra) {
		super();
		this.idDocumentoPedidoCompra = idDocumentoPedidoCompra;
	}



	/**
	 * @return the idDocumentoPedidoCompra
	 */
	public Integer getIdDocumentoPedidoCompra() {
		return idDocumentoPedidoCompra;
	}



	/**
	 * @param idDocumentoPedidoCompra the idDocumentoPedidoCompra to set
	 */
	public void setIdDocumentoPedidoCompra(Integer idDocumentoPedidoCompra) {
		this.idDocumentoPedidoCompra = idDocumentoPedidoCompra;
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
	 * @return the idTipoDocumento
	 */
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}



	/**
	 * @param idTipoDocumento the idTipoDocumento to set
	 */
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}



	/**
	 * @return the nombreDocumento
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}



	/**
	 * @param nombreDocumento the nombreDocumento to set
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}



	/**
	 * @return the nroDocumento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}



	/**
	 * @param nroDocumento the nroDocumento to set
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}



	/**
	 * @return the fechaDocumento
	 */
	public String getFechaDocumento() {
		return fechaDocumento;
	}



	/**
	 * @param fechaDocumento the fechaDocumento to set
	 */
	public void setFechaDocumento(String fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
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
	 * @return the codigoArchivoAlfresco
	 */
	public String getCodigoArchivoAlfresco() {
		return codigoArchivoAlfresco;
	}



	/**
	 * @param codigoArchivoAlfresco the codigoArchivoAlfresco to set
	 */
	public void setCodigoArchivoAlfresco(String codigoArchivoAlfresco) {
		this.codigoArchivoAlfresco = codigoArchivoAlfresco;
	}



	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}



	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}



	/**
	 * @return the flgActivo
	 */
	public Integer getFlgActivo() {
		return flgActivo;
	}



	/**
	 * @param flgActivo the flgActivo to set
	 */
	public void setFlgActivo(Integer flgActivo) {
		this.flgActivo = flgActivo;
	}



}
