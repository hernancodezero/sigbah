package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: GuiaRemisionBean.java
 * @description: Clase GuiaRemisionBean.
 * @date: 18/02/2015 22:26:30
 * @author: Junior Huaman Flores.
 */
public class GuiaRemisionBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idGuiaRemision;
	private Integer idSalida;
	private String codigoAnio;
	private String codigoMes;
	private String nombreMes;
	private Integer idDdi;
	private String codigoDdi;
	private String nombreDdi;
	private Integer idAlmacen;
	private String codigoAlmacen;
	private String nombreAlmacen;
	private String fechaEmision;
	private Integer idMovimiento;
	private String codigoMovimiento;
	private String nombreMovimiento;
	private Integer idEstado;
	private String nombreEstado;
	private Integer idOrdenSalida;
	private String nroOrdenSalida;
	private String nroGuiaRemision;
	private Integer idManifiestoCarga;
	private String nroManifiestoCarga;
	private String nroActaEntregaRecepcion;
	private String observacionGuiaRemision;
	private String observacionManifiestoCarga;
	private String observacionActaEntregaRecepcion;
	private String tipoOrigen;
	private Integer idMotivoTraslado;
	private String motivoTraslado;
	private String codigoMesInicio;
	private String codigoMesFin;
	private String nombreAlmacenDestino;
	private BigDecimal pesoTotalKgr;
	private String fechaEntrega;
	
	private String fechaEmisionN;
	private String fechaTrasladoN;
	private String idGeneraGuia;
	
	private Integer idProducto;


	/**
	 * 
	 */
	public GuiaRemisionBean() {
		super();
	}

	/**
	 * @return the idGuiaRemision
	 */
	public Integer getIdGuiaRemision() {
		return idGuiaRemision;
	}

	/**
	 * @param idGuiaRemision the idGuiaRemision to set
	 */
	public void setIdGuiaRemision(Integer idGuiaRemision) {
		this.idGuiaRemision = idGuiaRemision;
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
	 * @return the nroOrdenSalida
	 */
	public String getNroOrdenSalida() {
		return nroOrdenSalida;
	}

	/**
	 * @param nroOrdenSalida the nroOrdenSalida to set
	 */
	public void setNroOrdenSalida(String nroOrdenSalida) {
		this.nroOrdenSalida = nroOrdenSalida;
	}

	/**
	 * @return the nroGuiaRemision
	 */
	public String getNroGuiaRemision() {
		return nroGuiaRemision;
	}

	/**
	 * @param nroGuiaRemision the nroGuiaRemision to set
	 */
	public void setNroGuiaRemision(String nroGuiaRemision) {
		this.nroGuiaRemision = nroGuiaRemision;
	}

	/**
	 * @return the nroManifiestoCarga
	 */
	public String getNroManifiestoCarga() {
		return nroManifiestoCarga;
	}

	/**
	 * @param nroManifiestoCarga the nroManifiestoCarga to set
	 */
	public void setNroManifiestoCarga(String nroManifiestoCarga) {
		this.nroManifiestoCarga = nroManifiestoCarga;
	}

	/**
	 * @return the nroActaEntregaRecepcion
	 */
	public String getNroActaEntregaRecepcion() {
		return nroActaEntregaRecepcion;
	}

	/**
	 * @param nroActaEntregaRecepcion the nroActaEntregaRecepcion to set
	 */
	public void setNroActaEntregaRecepcion(String nroActaEntregaRecepcion) {
		this.nroActaEntregaRecepcion = nroActaEntregaRecepcion;
	}

	/**
	 * @return the observacionGuiaRemision
	 */
	public String getObservacionGuiaRemision() {
		return observacionGuiaRemision;
	}

	/**
	 * @param observacionGuiaRemision the observacionGuiaRemision to set
	 */
	public void setObservacionGuiaRemision(String observacionGuiaRemision) {
		this.observacionGuiaRemision = observacionGuiaRemision;
	}

	/**
	 * @return the observacionManifiestoCarga
	 */
	public String getObservacionManifiestoCarga() {
		return observacionManifiestoCarga;
	}

	/**
	 * @param observacionManifiestoCarga the observacionManifiestoCarga to set
	 */
	public void setObservacionManifiestoCarga(String observacionManifiestoCarga) {
		this.observacionManifiestoCarga = observacionManifiestoCarga;
	}

	/**
	 * @return the observacionActaEntregaRecepcion
	 */
	public String getObservacionActaEntregaRecepcion() {
		return observacionActaEntregaRecepcion;
	}

	/**
	 * @param observacionActaEntregaRecepcion the observacionActaEntregaRecepcion to set
	 */
	public void setObservacionActaEntregaRecepcion(String observacionActaEntregaRecepcion) {
		this.observacionActaEntregaRecepcion = observacionActaEntregaRecepcion;
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
	 * @return the idOrdenSalida
	 */
	public Integer getIdOrdenSalida() {
		return idOrdenSalida;
	}

	/**
	 * @param idOrdenSalida the idOrdenSalida to set
	 */
	public void setIdOrdenSalida(Integer idOrdenSalida) {
		this.idOrdenSalida = idOrdenSalida;
	}

	/**
	 * @return the idManifiestoCarga
	 */
	public Integer getIdManifiestoCarga() {
		return idManifiestoCarga;
	}

	/**
	 * @param idManifiestoCarga the idManifiestoCarga to set
	 */
	public void setIdManifiestoCarga(Integer idManifiestoCarga) {
		this.idManifiestoCarga = idManifiestoCarga;
	}

	/**
	 * @return the idSalida
	 */
	public Integer getIdSalida() {
		return idSalida;
	}

	/**
	 * @param idSalida the idSalida to set
	 */
	public void setIdSalida(Integer idSalida) {
		this.idSalida = idSalida;
	}

	/**
	 * @return the idMotivoTraslado
	 */
	public Integer getIdMotivoTraslado() {
		return idMotivoTraslado;
	}

	/**
	 * @param idMotivoTraslado the idMotivoTraslado to set
	 */
	public void setIdMotivoTraslado(Integer idMotivoTraslado) {
		this.idMotivoTraslado = idMotivoTraslado;
	}

	/**
	 * @return the motivoTraslado
	 */
	public String getMotivoTraslado() {
		return motivoTraslado;
	}

	/**
	 * @param motivoTraslado the motivoTraslado to set
	 */
	public void setMotivoTraslado(String motivoTraslado) {
		this.motivoTraslado = motivoTraslado;
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
	 * @return the fechaEntrega
	 */
	public String getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
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
	 * @return the fechaEmisionN
	 */
	public String getFechaEmisionN() {
		return fechaEmisionN;
	}

	/**
	 * @param fechaEmisionN the fechaEmisionN to set
	 */
	public void setFechaEmisionN(String fechaEmisionN) {
		this.fechaEmisionN = fechaEmisionN;
	}

	/**
	 * @return the fechaTrasladoN
	 */
	public String getFechaTrasladoN() {
		return fechaTrasladoN;
	}

	/**
	 * @param fechaTrasladoN the fechaTrasladoN to set
	 */
	public void setFechaTrasladoN(String fechaTrasladoN) {
		this.fechaTrasladoN = fechaTrasladoN;
	}

	/**
	 * @return the idGeneraGuia
	 */
	public String getIdGeneraGuia() {
		return idGeneraGuia;
	}

	/**
	 * @param idGeneraGuia the idGeneraGuia to set
	 */
	public void setIdGeneraGuia(String idGeneraGuia) {
		this.idGeneraGuia = idGeneraGuia;
	}
	
}