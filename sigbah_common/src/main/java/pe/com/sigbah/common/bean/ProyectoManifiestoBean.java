package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ProyectoManifiestoBean.java
 * @description: Clase ProyectoManifiestoBean.
 * @date: 18/02/2015 22:26:30
 * @author: Junior Huaman Flores.
 */
public class ProyectoManifiestoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idProyectoManifiesto;
	private String codigoAnio;
	private String codigoMes;
	private String nombreMes;
	private Integer idDdi;
	private String codigoDdi;
	private String nombreDdi;
	private Integer idAlmacen;
	private String codigoAlmacen;
	private String nombreAlmacen;
	private Integer idProgramacion;
	private String nroProyectoManifiesto;
	private String nroProgramacion;
	private String fechaEmision;
	private String codigoProgramacion;
	private Integer idMovimiento;
	private String codigoMovimiento;
	private String nombreMovimiento;
	private Integer idEstado;
	private String nombreEstado;
	private String observacion;
	private String tipoOrigen;
	private String flagProgramacion;
	private Integer idAlmacenDestino;
	private String nombreAlmacenDestino;
	private String codigoProyectoManifiesto;
	private String tipoControl;
	private String codigoMesInicio;
	private String codigoMesFin;
	private BigDecimal volumenTotal;
	private BigDecimal pesoTotalKgr;
	
	private Integer idProducto;
	

	/**
	 * 
	 */
	public ProyectoManifiestoBean() {
		super();
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
	 * @return the nroProyectoManifiesto
	 */
	public String getNroProyectoManifiesto() {
		return nroProyectoManifiesto;
	}

	/**
	 * @param nroProyectoManifiesto the nroProyectoManifiesto to set
	 */
	public void setNroProyectoManifiesto(String nroProyectoManifiesto) {
		this.nroProyectoManifiesto = nroProyectoManifiesto;
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
	 * @return the idMovimiento
	 */
	public Integer getIdMovimiento() {
		return idMovimiento;
	}

	/**
	 * @param idMovimiento the idMovimiento to set
	 */
	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	/**
	 * @return the codigoMovimiento
	 */
	public String getCodigoMovimiento() {
		return codigoMovimiento;
	}

	/**
	 * @param codigoMovimiento the codigoMovimiento to set
	 */
	public void setCodigoMovimiento(String codigoMovimiento) {
		this.codigoMovimiento = codigoMovimiento;
	}

	/**
	 * @return the nombreMovimiento
	 */
	public String getNombreMovimiento() {
		return nombreMovimiento;
	}

	/**
	 * @param nombreMovimiento the nombreMovimiento to set
	 */
	public void setNombreMovimiento(String nombreMovimiento) {
		this.nombreMovimiento = nombreMovimiento;
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

	/**
	 * @return the flagProgramacion
	 */
	public String getFlagProgramacion() {
		return flagProgramacion;
	}

	/**
	 * @param flagProgramacion the flagProgramacion to set
	 */
	public void setFlagProgramacion(String flagProgramacion) {
		this.flagProgramacion = flagProgramacion;
	}

	/**
	 * @return the idAlmacenDestino
	 */
	public Integer getIdAlmacenDestino() {
		return idAlmacenDestino;
	}

	/**
	 * @param idAlmacenDestino the idAlmacenDestino to set
	 */
	public void setIdAlmacenDestino(Integer idAlmacenDestino) {
		this.idAlmacenDestino = idAlmacenDestino;
	}

	/**
	 * @return the codigoProyectoManifiesto
	 */
	public String getCodigoProyectoManifiesto() {
		return codigoProyectoManifiesto;
	}

	/**
	 * @param codigoProyectoManifiesto the codigoProyectoManifiesto to set
	 */
	public void setCodigoProyectoManifiesto(String codigoProyectoManifiesto) {
		this.codigoProyectoManifiesto = codigoProyectoManifiesto;
	}

	/**
	 * @return the nombreAlmacenDestino
	 */
	public String getNombreAlmacenDestino() {
		return nombreAlmacenDestino;
	}

	/**
	 * @param nombreAlmacenDestino the nombreAlmacenDestino to set
	 */
	public void setNombreAlmacenDestino(String nombreAlmacenDestino) {
		this.nombreAlmacenDestino = nombreAlmacenDestino;
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
	 * @return the codigoMesInicio
	 */
	public String getCodigoMesInicio() {
		return codigoMesInicio;
	}

	/**
	 * @param codigoMesInicio the codigoMesInicio to set
	 */
	public void setCodigoMesInicio(String codigoMesInicio) {
		this.codigoMesInicio = codigoMesInicio;
	}

	/**
	 * @return the codigoMesFin
	 */
	public String getCodigoMesFin() {
		return codigoMesFin;
	}

	/**
	 * @param codigoMesFin the codigoMesFin to set
	 */
	public void setCodigoMesFin(String codigoMesFin) {
		this.codigoMesFin = codigoMesFin;
	}

	/**
	 * @return the volumenTotal
	 */
	public BigDecimal getVolumenTotal() {
		return volumenTotal;
	}

	/**
	 * @param volumenTotal the volumenTotal to set
	 */
	public void setVolumenTotal(BigDecimal volumenTotal) {
		this.volumenTotal = volumenTotal;
	}

	/**
	 * @return the pesoTotalKgr
	 */
	public BigDecimal getPesoTotalKgr() {
		return pesoTotalKgr;
	}

	/**
	 * @param pesoTotalKgr the pesoTotalKgr to set
	 */
	public void setPesoTotalKgr(BigDecimal pesoTotalKgr) {
		this.pesoTotalKgr = pesoTotalKgr;
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

}