package pe.com.sigbah.common.bean;

import java.util.List;

/**
 * @className: ProgramacionBean.java
 * @description: 
 * @date: 29/07/2017
 * @author: Junior Huaman Flores.
 */
public class ProgramacionBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idProgramacion;
	private String codigoAnio;
	private String codigoMes;
	private String nombreMes;	
	private Integer idAlmacen;
	private String codigoAlmacen;
	private String nombreAlmacen;
	private Integer idDdi;
	private String codigoDdi;
	private String nombreDdi;
	private String fechaProgramacion;
	private String codigoProgramacion;
	private String nroProgramacion;
	private String nombreProgramacion;	
	private Integer idFenomeno;
	private String nombreFenomeno;
	private Integer idNroDee;
	private String nroDee;
	private String nombreDeclarion;
	private Integer idEstado;
	private String nombreEstado;
	private Integer idRegion;
	private String nombreRegion;
	private Integer idRequerimiento;
	private String nombreRequerimiento;	
	private Integer idRacion;
	private String nombreRacion;	
	private Integer tipoAtencion;
	private String observacion;
	private String tipoControl;
	private List<ProgramacionAlmacenBean> almacenes;
	private String nombreSistema;
	
	private String codRequerimiento;	
	private String codRacion;
	
	
	
	/**
	 * 
	 */
	public ProgramacionBean() {
		super();
	}
	/**
	 * @param idProgramacion
	 */
	public ProgramacionBean(Integer idProgramacion) {
		super();
		this.idProgramacion = idProgramacion;
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
	 * @return the nombreMes
	 */
	public String getNombreMes() {
		return nombreMes;
	}
	/**
	 * @param nombreMes the nombreMes to set
	 */
	public void setNombreMes(String nombreMes) {
		this.nombreMes = nombreMes;
	}
	/**
	 * @return the idAlmacen
	 */
	public Integer getIdAlmacen() {
		return idAlmacen;
	}
	/**
	 * @param idAlmacen the idAlmacen to set
	 */
	public void setIdAlmacen(Integer idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	/**
	 * @return the codigoAlmacen
	 */
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}
	/**
	 * @param codigoAlmacen the codigoAlmacen to set
	 */
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}
	/**
	 * @return the nombreAlmacen
	 */
	public String getNombreAlmacen() {
		return nombreAlmacen;
	}
	/**
	 * @param nombreAlmacen the nombreAlmacen to set
	 */
	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
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
	 * @return the fechaProgramacion
	 */
	public String getFechaProgramacion() {
		return fechaProgramacion;
	}
	/**
	 * @param fechaProgramacion the fechaProgramacion to set
	 */
	public void setFechaProgramacion(String fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}
	/**
	 * @return the nroProgramacion
	 */
	public String getNroProgramacion() {
		return nroProgramacion;
	}
	/**
	 * @param nroProgramacion the nroProgramacion to set
	 */
	public void setNroProgramacion(String nroProgramacion) {
		this.nroProgramacion = nroProgramacion;
	}
	/**
	 * @return the nombreProgramacion
	 */
	public String getNombreProgramacion() {
		return nombreProgramacion;
	}
	/**
	 * @param nombreProgramacion the nombreProgramacion to set
	 */
	public void setNombreProgramacion(String nombreProgramacion) {
		this.nombreProgramacion = nombreProgramacion;
	}
	/**
	 * @return the idFenomeno
	 */
	public Integer getIdFenomeno() {
		return idFenomeno;
	}
	/**
	 * @param idFenomeno the idFenomeno to set
	 */
	public void setIdFenomeno(Integer idFenomeno) {
		this.idFenomeno = idFenomeno;
	}
	/**
	 * @return the nombreFenomeno
	 */
	public String getNombreFenomeno() {
		return nombreFenomeno;
	}
	/**
	 * @param nombreFenomeno the nombreFenomeno to set
	 */
	public void setNombreFenomeno(String nombreFenomeno) {
		this.nombreFenomeno = nombreFenomeno;
	}
	/**
	 * @return the idNroDee
	 */
	public Integer getIdNroDee() {
		return idNroDee;
	}
	/**
	 * @param idNroDee the idNroDee to set
	 */
	public void setIdNroDee(Integer idNroDee) {
		this.idNroDee = idNroDee;
	}
	/**
	 * @return the nroDee
	 */
	public String getNroDee() {
		return nroDee;
	}
	/**
	 * @param nroDee the nroDee to set
	 */
	public void setNroDee(String nroDee) {
		this.nroDee = nroDee;
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
	 * @return the idRegion
	 */
	public Integer getIdRegion() {
		return idRegion;
	}
	/**
	 * @param idRegion the idRegion to set
	 */
	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}
	/**
	 * @return the nombreRegion
	 */
	public String getNombreRegion() {
		return nombreRegion;
	}
	/**
	 * @param nombreRegion the nombreRegion to set
	 */
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}
	/**
	 * @return the idRequerimiento
	 */
	public Integer getIdRequerimiento() {
		return idRequerimiento;
	}
	/**
	 * @param idRequerimiento the idRequerimiento to set
	 */
	public void setIdRequerimiento(Integer idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}
	/**
	 * @return the nombreRequerimiento
	 */
	public String getNombreRequerimiento() {
		return nombreRequerimiento;
	}
	/**
	 * @param nombreRequerimiento the nombreRequerimiento to set
	 */
	public void setNombreRequerimiento(String nombreRequerimiento) {
		this.nombreRequerimiento = nombreRequerimiento;
	}
	/**
	 * @return the idRacion
	 */
	public Integer getIdRacion() {
		return idRacion;
	}
	/**
	 * @param idRacion the idRacion to set
	 */
	public void setIdRacion(Integer idRacion) {
		this.idRacion = idRacion;
	}
	/**
	 * @return the nombreRacion
	 */
	public String getNombreRacion() {
		return nombreRacion;
	}
	/**
	 * @param nombreRacion the nombreRacion to set
	 */
	public void setNombreRacion(String nombreRacion) {
		this.nombreRacion = nombreRacion;
	}
	/**
	 * @return the tipoAtencion
	 */
	public Integer getTipoAtencion() {
		return tipoAtencion;
	}
	/**
	 * @param tipoAtencion the tipoAtencion to set
	 */
	public void setTipoAtencion(Integer tipoAtencion) {
		this.tipoAtencion = tipoAtencion;
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
	 * @return the codigoProgramacion
	 */
	public String getCodigoProgramacion() {
		return codigoProgramacion;
	}
	/**
	 * @param codigoProgramacion the codigoProgramacion to set
	 */
	public void setCodigoProgramacion(String codigoProgramacion) {
		this.codigoProgramacion = codigoProgramacion;
	}
	/**
	 * @return the nombreDeclarion
	 */
	public String getNombreDeclarion() {
		return nombreDeclarion;
	}
	/**
	 * @param nombreDeclarion the nombreDeclarion to set
	 */
	public void setNombreDeclarion(String nombreDeclarion) {
		this.nombreDeclarion = nombreDeclarion;
	}
	/**
	 * @return the tipoControl
	 */
	public String getTipoControl() {
		return tipoControl;
	}
	/**
	 * @param tipoControl the tipoControl to set
	 */
	public void setTipoControl(String tipoControl) {
		this.tipoControl = tipoControl;
	}
	/**
	 * @return the almacenes
	 */
	public List<ProgramacionAlmacenBean> getAlmacenes() {
		return almacenes;
	}
	/**
	 * @param almacenes the almacenes to set
	 */
	public void setAlmacenes(List<ProgramacionAlmacenBean> almacenes) {
		this.almacenes = almacenes;
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
	 * @return the codRequerimiento
	 */
	public String getCodRequerimiento() {
		return codRequerimiento;
	}
	/**
	 * @param codRequerimiento the codRequerimiento to set
	 */
	public void setCodRequerimiento(String codRequerimiento) {
		this.codRequerimiento = codRequerimiento;
	}
	/**
	 * @return the codRacion
	 */
	public String getCodRacion() {
		return codRacion;
	}
	/**
	 * @param codRacion the codRacion to set
	 */
	public void setCodRacion(String codRacion) {
		this.codRacion = codRacion;
	}

}
