package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: DonacionesBean.java
 * @description: 
 * @date: 23 de jun. de 2017
 * @author: SUMERIO.
 */
public class DonacionesBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private String codigoAnio;
	private String codigoMes;
	private String nombreDdi;
	private String codigoDonacion;
	private String fechaEmision;
	private String nombrePais;
	private String nombreDonante;
	private String nombreEstado;
	private String codigoDdi;
	private String codigoEstado;
	private Integer idEstado;
	private Integer idDonacion;
	private Integer idDdi;
	private Integer idPaisDonante;
	private String tipoDonante;
	private String tipoOrigenDonante;
	private Integer idOficina;
	private Integer idPersonal;
	private Integer idDonante;
	private String finalidad;
	private String observacion;
	private String textoa;
	private String textob;
	private String tipoDonacion;
	private Integer idDee;
	private String nombreSistema;
	private String versionSistema;
	
	private String textoCodigo;  
	private String nombreDeclaratoria;
	private String nroDee;
	private String representante;
	private String numDocumento;
	private String nombrePersonal;
	private String nombreOficina;
	private String nombreOrigen;
	private String oficinaResponsable;
	private String fechaEvaluacion;
	
	private String fechaRj;
	private String nroRj;
	private String codigoAlfrescoRj;
	private BigDecimal importeMonedaOrigen;
	private String nroDocInafectacion;
	private String codigoAlfrescoInafectacion;
	private String nombreArchivo;
	 
	/**
	 * @return the codigoAnio
	 */
	public String getCodigoAnio() {
		return codigoAnio;
	}
	/**
	 * @param codigoAnio the codigoAnio to set
	 */
	public void setCodigoAnio(String codigoAnio) {
		this.codigoAnio = codigoAnio;
	}
	 
	/**
	 * @return the codigoMes
	 */
	public String getCodigoMes() {
		return codigoMes;
	}
	/**
	 * @param codigoMes the codigoMes to set
	 */
	public void setCodigoMes(String codigoMes) {
		this.codigoMes = codigoMes;
	}
	
	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	  
	/**
	 * @return the nombreEstado
	 */
	public String getNombreEstado() {
		return nombreEstado;
	}
	/**
	 * @param nombreEstado the nombreEstado to set
	 */
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
 
	/**
	 * @return the nombreDdi
	 */
	public String getNombreDdi() {
		return nombreDdi;
	}
	/**
	 * @param nombreDdi the nombreDdi to set
	 */
	public void setNombreDdi(String nombreDdi) {
		this.nombreDdi = nombreDdi;
	}
	/**
	 * @return the codigoDonacion
	 */
	public String getCodigoDonacion() {
		return codigoDonacion;
	}
	/**
	 * @param codigoDonacion the codigoDonacion to set
	 */
	public void setCodigoDonacion(String codigoDonacion) {
		this.codigoDonacion = codigoDonacion;
	}
	/**
	 * @return the nombrePais
	 */
	public String getNombrePais() {
		return nombrePais;
	}
	/**
	 * @param nombrePais the nombrePais to set
	 */
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	/**
	 * @return the nombreDonante
	 */
	public String getNombreDonante() {
		return nombreDonante;
	}
	/**
	 * @param nombreDonante the nombreDonante to set
	 */
	public void setNombreDonante(String nombreDonante) {
		this.nombreDonante = nombreDonante;
	}
	/**
	 * @return the idDonacion
	 */
	public Integer getIdDonacion() {
		return idDonacion;
	}
	/**
	 * @param idDonacion the idDonacion to set
	 */
	public void setIdDonacion(Integer idDonacion) {
		this.idDonacion = idDonacion;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the idEstado
	 */
	public Integer getIdEstado() {
		return idEstado;
	}
	/**
	 * @param idEstado the idEstado to set
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	/**
	 * @return the codigoDdi
	 */
	public String getCodigoDdi() {
		return codigoDdi;
	}
	/**
	 * @param codigoDdi the codigoDdi to set
	 */
	public void setCodigoDdi(String codigoDdi) {
		this.codigoDdi = codigoDdi;
	}
	/**
	 * @return the codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}
	/**
	 * @param codigoEstado the codigoAlmacen to set
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	/**
	 * @param idPaisDonante the idPaisDonante to set
	 */
	public void setIdPaisDonante(Integer idPaisDonante) {
		this.idPaisDonante = idPaisDonante;
	}
	/**
	 * @return the idPaisDonante
	 */
	public Integer getIdPaisDonante() {
		return idPaisDonante;
	}
	/**
	 * @param tipoDonante the tipoDonante to set
	 */
	public void setTipoDonante(String tipoDonante) {
		this.tipoDonante = tipoDonante;
	}
	/**
	 * @return the tipoDonante
	 */
	public String getTipoDonante() {
		return tipoDonante;
	}
	/**
	 * @param tipoOrigenDonante the tipoOrigenDonante to set
	 */
	public void setTipoOrigenDonante(String tipoOrigenDonante) {
		this.tipoOrigenDonante = tipoOrigenDonante;
	}
	/**
	 * @return the tipoOrigenDonante
	 */
	public String getTipoOrigenDonante() {
		return tipoOrigenDonante;
	}
	/**
	 * @param idOficina the idOficina to set
	 */
	public void setIdOficina(Integer idOficina) {
		this.idOficina = idOficina;
	}
	/**
	 * @return the idOficina
	 */
	public Integer getIdOficina() {
		return idOficina;
	}
	/**
	 * @param idPersonal the idPersonal to set
	 */
	public void setIdPersonal(Integer idPersonal) {
		this.idPersonal = idPersonal;
	}
	/**
	 * @return the idPersonal
	 */
	public Integer getIdPersonal() {
		return idPersonal;
	}
	/**
	 * @param idDonante the idDonante to set
	 */
	public void setIdDonante(Integer idDonante) {
		this.idDonante = idDonante;
	}
	/**
	 * @return the idDonante
	 */
	public Integer getIdDonante() {
		return idDonante;
	}
	/**
	 * @return the finalidad
	 */
	public String getFinalidad() {
		return finalidad;
	}
	/**
	 * @param finalidad the finalidad to set
	 */
	public void setFinalidad(String finalidad) {
		this.finalidad = finalidad;
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
	 * @return the textoa
	 */
	public String getTextoa() {
		return textoa;
	}
	/**
	 * @param textoa the textoa to set
	 */
	public void setTextoa(String textoa) {
		this.textoa = textoa;
	}
	/**
	 * @return the textob
	 */
	public String getTextob() {
		return textob;
	}
	/**
	 * @param textob the textob to set
	 */
	public void setTextob(String textob) {
		this.textob = textob;
	}
	/**
	 * @param tipoDonacion the tipoDonacion to set
	 */
	public void setTipoDonacion(String tipoDonacion) {
		this.tipoDonacion = tipoDonacion;
	}
	/**
	 * @return the tipoDonacion
	 */
	public String getTipoDonacion() {
		return tipoDonacion;
	}
	/**
	 * @param idDee the idDee to set
	 */
	public void setIdDee(Integer idDee) {
		this.idDee = idDee;
	}
	/**
	 * @return the idDee
	 */
	public Integer getIdDee() {
		return idDee;
	}
	/**
	 * @return the textoCodigo
	 */
	public String getTextoCodigo() {
		return textoCodigo;
	}
	/**
	 * @param textoCodigo the textoCodigo to set
	 */
	public void setTextoCodigo(String textoCodigo) {
		this.textoCodigo = textoCodigo;
	}
	/**
	 * @return the nombreDeclaratoria
	 */
	public String getNombreDeclaratoria() {
		return nombreDeclaratoria;
	}
	/**
	 * @param nombreDeclaratoria the nombreDeclaratoria to set
	 */
	public void setNombreDeclaratoria(String nombreDeclaratoria) {
		this.nombreDeclaratoria = nombreDeclaratoria;
	}
	/**
	 * @return the nombreDeclaratoria
	 */
	public String getRepresentante() {
		return representante;
	}
	/**
	 * @param representante the representante to set
	 */
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	/**
	 * @return the numDocumento
	 */
	public String getNumDocumento() {
		return numDocumento;
	}
	/**
	 * @param numDocumento the numDocumento to set
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	/**
	 * @return the nombrePersonal
	 */
	public String getNombrePersonal() {
		return nombrePersonal;
	}
	/**
	 * @param nombrePersonal the nombrePersonal to set
	 */
	public void setNombrePersonal(String nombrePersonal) {
		this.nombrePersonal = nombrePersonal;
	}
	/**
	 * @return the nombreOficina
	 */
	public String getNombreOficina() {
		return nombreOficina;
	}
	/**
	 * @param nombreOficina the nombreOficina to set
	 */
	public void setNombreOficina(String nombreOficina) {
		this.nombreOficina = nombreOficina;
	}
	/**
	 * @return the nombreOrigen
	 */
	public String getNombreOrigen() {
		return nombreOrigen;
	}
	/**
	 * @param nombreOrigen the nombreOrigen to set
	 */
	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}
	/**
	 * @return the oficinaResponsable
	 */
	public String getOficinaResponsable() {
		return oficinaResponsable;
	}
	/**
	 * @param oficinaResponsable the oficinaResponsable to set
	 */
	public void setOficinaResponsable(String oficinaResponsable) {
		this.oficinaResponsable = oficinaResponsable;
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
	 * @return the versionSistema
	 */
	public String getVersionSistema() {
		return versionSistema;
	}
	/**
	 * @param versionSistema the versionSistema to set
	 */
	public void setVersionSistema(String versionSistema) {
		this.versionSistema = versionSistema;
	}
	public String getNroDee() {
		return nroDee;
	}
	public void setNroDee(String nroDee) {
		this.nroDee = nroDee;
	}
	/**
	 * @return the fechaEvaluacion
	 */
	public String getFechaEvaluacion() {
		return fechaEvaluacion;
	}
	/**
	 * @param fechaEvaluacion the fechaEvaluacion to set
	 */
	public void setFechaEvaluacion(String fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}
	/**
	 * @return the fechaRj
	 */
	public String getFechaRj() {
		return fechaRj;
	}
	/**
	 * @param fechaRj the fechaRj to set
	 */
	public void setFechaRj(String fechaRj) {
		this.fechaRj = fechaRj;
	}
	/**
	 * @return the nroRj
	 */
	public String getNroRj() {
		return nroRj;
	}
	/**
	 * @param nroRj the nroRj to set
	 */
	public void setNroRj(String nroRj) {
		this.nroRj = nroRj;
	}
	/**
	 * @return the codigoAlfrescoRj
	 */
	public String getCodigoAlfrescoRj() {
		return codigoAlfrescoRj;
	}
	/**
	 * @param codigoAlfrescoRj the codigoAlfrescoRj to set
	 */
	public void setCodigoAlfrescoRj(String codigoAlfrescoRj) {
		this.codigoAlfrescoRj = codigoAlfrescoRj;
	}
	/**
	 * @return the importeMonedaOrigen
	 */
	public BigDecimal getImporteMonedaOrigen() {
		return importeMonedaOrigen;
	}
	/**
	 * @param importeMonedaOrigen the importeMonedaOrigen to set
	 */
	public void setImporteMonedaOrigen(BigDecimal importeMonedaOrigen) {
		this.importeMonedaOrigen = importeMonedaOrigen;
	}
	/**
	 * @return the nroDocInafectacion
	 */
	public String getNroDocInafectacion() {
		return nroDocInafectacion;
	}
	/**
	 * @param nroDocInafectacion the nroDocInafectacion to set
	 */
	public void setNroDocInafectacion(String nroDocInafectacion) {
		this.nroDocInafectacion = nroDocInafectacion;
	}
	/**
	 * @return the codigoAlfrescoInafectacion
	 */
	public String getCodigoAlfrescoInafectacion() {
		return codigoAlfrescoInafectacion;
	}
	/**
	 * @param codigoAlfrescoInafectacion the codigoAlfrescoInafectacion to set
	 */
	public void setCodigoAlfrescoInafectacion(String codigoAlfrescoInafectacion) {
		this.codigoAlfrescoInafectacion = codigoAlfrescoInafectacion;
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
	 
	

}
