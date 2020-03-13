package pe.com.sigbah.common.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @className: OrdenSalidaBean.java
 * @description: 
 * @date: 3 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class OrdenSalidaBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idSalida;
	private Integer item;
	private String codigoAnio;
	private Integer idDdi;
	private String codigoDdi;
	private String nombreDdi;
	private Integer idAlmacen;
	private String codigoAlmacen;
	private String nombreAlmacen;
	private String codigoMes;
	private String nombreMes;
	private String codigoSalida;
	private String nroOrdenSalida;
	private String fechaEmision;
	private Integer idMovimiento;
	private String codigoMovimiento;
	private String nombreMovimiento;
	private String nombreEstado;	
	private String nroGuiaRemision;
	private String nombreEmpresa;	
	private String tipoOrigen;
	private String codigoUbigeo;
	private String codigoRegion;
	private String codigoDepartamento;
	private String codigoProvincia;
	private String codigoDistrito;	
	private Integer idProgramacion;
	private Integer idResponsable;
	private Integer idResponsableExt;
	private Integer idSolicitante;
	private Integer idResponsableRecepcion;
	private Integer idProyectoManifiesto;
	private Integer idDdiDestino;
	private Integer idAlmacenDestino;
	private Integer idAlmacenDestinoExt;
	private Integer idMedioTransporte;
	private Integer idEmpresaTransporte;
	private Integer idChofer;
	private String nroPlaca;
	private String fechaEntrega;
	private String flagTipoDestino;	
	private String observacion;
	private Integer idEstado;
	private String indControl;
	private String nroRequerimiento;
	private String nroProyectoManifiesto;
	private String nroProgramacion;
	private String nombreAlmacenDestino;
	private String codigoMesInicio;
	private String codigoMesFin;
	private BigDecimal importeTotal;
	private BigDecimal pesoTotalKgr;
	private String nombreSistema;
	private String flagProyecto;
	
	private Integer idProducto;
	
	private List<OrdenSalidaBean> lstGeneral;
	private List<ProductoSalidaBean> lstProducto;
	private List<DocumentoSalidaBean> lstDocumento;
	
	/**
	 * 
	 */
	public OrdenSalidaBean() {
		super();
	}
	
	/**
	 * @param idSalida
	 */
	public OrdenSalidaBean(Integer idSalida) {
		super();
		this.idSalida = idSalida;
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
	 * @return the item
	 */
	public Integer getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(Integer item) {
		this.item = item;
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
	 * @return the codigoSalida
	 */
	public String getCodigoSalida() {
		return codigoSalida;
	}
	/**
	 * @param codigoSalida the codigoSalida to set
	 */
	public void setCodigoSalida(String codigoSalida) {
		this.codigoSalida = codigoSalida;
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
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	/**
	 * @param nombreEmpresa the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
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
	 * @return the codigoUbigeo
	 */
	public String getCodigoUbigeo() {
		return codigoUbigeo;
	}
	/**
	 * @param codigoUbigeo the codigoUbigeo to set
	 */
	public void setCodigoUbigeo(String codigoUbigeo) {
		this.codigoUbigeo = codigoUbigeo;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoDepartamento
	 */
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}
	/**
	 * @param codigoDepartamento the codigoDepartamento to set
	 */
	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}
	/**
	 * @return the codigoProvincia
	 */
	public String getCodigoProvincia() {
		return codigoProvincia;
	}
	/**
	 * @param codigoProvincia the codigoProvincia to set
	 */
	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}
	/**
	 * @return the codigoDistrito
	 */
	public String getCodigoDistrito() {
		return codigoDistrito;
	}
	/**
	 * @param codigoDistrito the codigoDistrito to set
	 */
	public void setCodigoDistrito(String codigoDistrito) {
		this.codigoDistrito = codigoDistrito;
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
	 * @return the idResponsable
	 */
	public Integer getIdResponsable() {
		return idResponsable;
	}
	/**
	 * @param idResponsable the idResponsable to set
	 */
	public void setIdResponsable(Integer idResponsable) {
		this.idResponsable = idResponsable;
	}
	/**
	 * @return the idResponsableExt
	 */
	public Integer getIdResponsableExt() {
		return idResponsableExt;
	}
	/**
	 * @param idResponsableExt the idResponsableExt to set
	 */
	public void setIdResponsableExt(Integer idResponsableExt) {
		this.idResponsableExt = idResponsableExt;
	}
	/**
	 * @return the idSolicitante
	 */
	public Integer getIdSolicitante() {
		return idSolicitante;
	}
	/**
	 * @param idSolicitante the idSolicitante to set
	 */
	public void setIdSolicitante(Integer idSolicitante) {
		this.idSolicitante = idSolicitante;
	}
	/**
	 * @return the idResponsableRecepcion
	 */
	public Integer getIdResponsableRecepcion() {
		return idResponsableRecepcion;
	}
	/**
	 * @param idResponsableRecepcion the idResponsableRecepcion to set
	 */
	public void setIdResponsableRecepcion(Integer idResponsableRecepcion) {
		this.idResponsableRecepcion = idResponsableRecepcion;
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
	 * @return the idDdiDestino
	 */
	public Integer getIdDdiDestino() {
		return idDdiDestino;
	}
	/**
	 * @param idDdiDestino the idDdiDestino to set
	 */
	public void setIdDdiDestino(Integer idDdiDestino) {
		this.idDdiDestino = idDdiDestino;
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
	 * @return the idAlmacenDestinoExt
	 */
	public Integer getIdAlmacenDestinoExt() {
		return idAlmacenDestinoExt;
	}
	/**
	 * @param idAlmacenDestinoExt the idAlmacenDestinoExt to set
	 */
	public void setIdAlmacenDestinoExt(Integer idAlmacenDestinoExt) {
		this.idAlmacenDestinoExt = idAlmacenDestinoExt;
	}
	/**
	 * @return the idMedioTransporte
	 */
	public Integer getIdMedioTransporte() {
		return idMedioTransporte;
	}
	/**
	 * @param idMedioTransporte the idMedioTransporte to set
	 */
	public void setIdMedioTransporte(Integer idMedioTransporte) {
		this.idMedioTransporte = idMedioTransporte;
	}
	/**
	 * @return the idEmpresaTransporte
	 */
	public Integer getIdEmpresaTransporte() {
		return idEmpresaTransporte;
	}
	/**
	 * @param idEmpresaTransporte the idEmpresaTransporte to set
	 */
	public void setIdEmpresaTransporte(Integer idEmpresaTransporte) {
		this.idEmpresaTransporte = idEmpresaTransporte;
	}
	/**
	 * @return the idChofer
	 */
	public Integer getIdChofer() {
		return idChofer;
	}
	/**
	 * @param idChofer the idChofer to set
	 */
	public void setIdChofer(Integer idChofer) {
		this.idChofer = idChofer;
	}
	/**
	 * @return the nroPlaca
	 */
	public String getNroPlaca() {
		return nroPlaca;
	}
	/**
	 * @param nroPlaca the nroPlaca to set
	 */
	public void setNroPlaca(String nroPlaca) {
		this.nroPlaca = nroPlaca;
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
	 * @return the flagTipoDestino
	 */
	public String getFlagTipoDestino() {
		return flagTipoDestino;
	}
	/**
	 * @param flagTipoDestino the flagTipoDestino to set
	 */
	public void setFlagTipoDestino(String flagTipoDestino) {
		this.flagTipoDestino = flagTipoDestino;
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
	 * @return the nroRequerimiento
	 */
	public String getNroRequerimiento() {
		return nroRequerimiento;
	}
	/**
	 * @param nroRequerimiento the nroRequerimiento to set
	 */
	public void setNroRequerimiento(String nroRequerimiento) {
		this.nroRequerimiento = nroRequerimiento;
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
	 * @return the lstGeneral
	 */
	public List<OrdenSalidaBean> getLstGeneral() {
		return lstGeneral;
	}

	/**
	 * @param lstGeneral the lstGeneral to set
	 */
	public void setLstGeneral(List<OrdenSalidaBean> lstGeneral) {
		this.lstGeneral = lstGeneral;
	}

	/**
	 * @return the lstProducto
	 */
	public List<ProductoSalidaBean> getLstProducto() {
		return lstProducto;
	}

	/**
	 * @param lstProducto the lstProducto to set
	 */
	public void setLstProducto(List<ProductoSalidaBean> lstProducto) {
		this.lstProducto = lstProducto;
	}

	/**
	 * @return the lstDocumento
	 */
	public List<DocumentoSalidaBean> getLstDocumento() {
		return lstDocumento;
	}

	/**
	 * @param lstDocumento the lstDocumento to set
	 */
	public void setLstDocumento(List<DocumentoSalidaBean> lstDocumento) {
		this.lstDocumento = lstDocumento;
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
	 * @return the flagProyecto
	 */
	public String getFlagProyecto() {
		return flagProyecto;
	}

	/**
	 * @param flagProyecto the flagProyecto to set
	 */
	public void setFlagProyecto(String flagProyecto) {
		this.flagProyecto = flagProyecto;
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
