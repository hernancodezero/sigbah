package pe.com.sigbah.common.bean;

/**
 * @className: DocumentoDonacionSalidaBean.java
 * @description: 
 * @date: 31 de jul. de 2017
 * @author: ARCHY.
 */
public class DocumentoDonacionSalidaBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idDocumentoDonacion;
	private Integer idDonacion;
	private String nroDocumento;
	private String observacion;
	private Integer idTipoDocumento;
	private String codAlfresco;
	private String nombreArchivo;
	private String fecha;
	private String descripcion;
	private String nombreDocumento;

	
	/**
	 * 
	 */
	public DocumentoDonacionSalidaBean() {
		super();
	}

	public DocumentoDonacionSalidaBean(Integer idDocumentoDonacion) {
		super();
		this.idDocumentoDonacion = idDocumentoDonacion;
	}

	public Integer getIdDocumentoDonacion() {
		return idDocumentoDonacion;
	}

	public void setIdDocumentoDonacion(Integer idDocumentoDonacion) {
		this.idDocumentoDonacion = idDocumentoDonacion;
	}

	public Integer getIdDonacion() {
		return idDonacion;
	}

	public void setIdDonacion(Integer idDonacion) {
		this.idDonacion = idDonacion;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getCodAlfresco() {
		return codAlfresco;
	}

	public void setCodAlfresco(String codAlfresco) {
		this.codAlfresco = codAlfresco;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

}
