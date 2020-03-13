package pe.com.sigbah.common.bean;

/**
 * @className: DocumentoSalidaBean.java
 * @description: 
 * @date: 25 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DocumentoProyectoManifiestoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idDocumentoProyecto;
	private Integer idProyectoManifiesto;
	private Integer idTipoDocumento;
	private String nombreDocumento;
	private String nroDocumento;
	private String fechaDocumento;
	private String observacion;
	private String codigoArchivoAlfresco;
	private String nombreArchivo;
	private String tipoOrigen;
	private String indControl;

	
	/**
	 * 
	 */
	public DocumentoProyectoManifiestoBean() {
		super();
	}

	/**
	 * @param idDocumentoProyecto
	 */
	public DocumentoProyectoManifiestoBean(Integer idDocumentoProyecto) {
		super();
		this.idDocumentoProyecto = idDocumentoProyecto;
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
	 * @return the idDocumentoProyecto
	 */
	public Integer getIdDocumentoProyecto() {
		return idDocumentoProyecto;
	}

	/**
	 * @param idDocumentoProyecto the idDocumentoProyecto to set
	 */
	public void setIdDocumentoProyecto(Integer idDocumentoProyecto) {
		this.idDocumentoProyecto = idDocumentoProyecto;
	}

	/**
	 * @return the idProyectoManifiesto
	 */
	public Integer getIdProyectoManifiesto() {
		return idProyectoManifiesto;
	}

	/**
	 * @param idProyectoManifiesto the idProyectoManifiesto to set
	 */
	public void setIdProyectoManifiesto(Integer idProyectoManifiesto) {
		this.idProyectoManifiesto = idProyectoManifiesto;
	}
	
	/**
	 * @return the indControl
	 */
	public String getIndControl() {
		return indControl;
	}
	
	/**
	 * @param indControl the indControl to set
	 */
	public void setIndControl(String indControl) {
		this.indControl = indControl;
	}

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
}
