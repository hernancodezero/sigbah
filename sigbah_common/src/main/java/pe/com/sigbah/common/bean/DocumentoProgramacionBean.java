package pe.com.sigbah.common.bean;

/**
 * @className: DocumentoProgramacionBean.java
 * @description: 
 * @date: 25 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DocumentoProgramacionBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idDocumentoProgramacion;
	private Integer idProgramacion;
	private Integer idTipoDocumento;
	private String nombreDocumento;
	private String nroDocumento;
	private String fechaDocumento;
	private String observacion;
	private String codigoArchivoAlfresco;
	private String nombreArchivo;
	private String indControl;

	
	/**
	 * 
	 */
	public DocumentoProgramacionBean() {
		super();
	}

	/**
	 * @param idDocumentoProgramacion
	 */
	public DocumentoProgramacionBean(Integer idDocumentoProgramacion) {
		super();
		this.idDocumentoProgramacion = idDocumentoProgramacion;
	}
	
	/**
	 * @return the idDocumentoProgramacion
	 */
	public Integer getIdDocumentoProgramacion() {
		return idDocumentoProgramacion;
	}

	/**
	 * @param idDocumentoProgramacion the idDocumentoProgramacion to set
	 */
	public void setIdDocumentoProgramacion(Integer idDocumentoProgramacion) {
		this.idDocumentoProgramacion = idDocumentoProgramacion;
	}

	/**
	 * @return the idProgramacion
	 */
	public Integer getIdProgramacion() {
		return idProgramacion;
	}

	/**
	 * @param idProgramacion the idProgramacion to set
	 */
	public void setIdProgramacion(Integer idProgramacion) {
		this.idProgramacion = idProgramacion;
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

}
