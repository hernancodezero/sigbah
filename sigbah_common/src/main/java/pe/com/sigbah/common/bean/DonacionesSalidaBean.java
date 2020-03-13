package pe.com.sigbah.common.bean;

/**
 * @className: DonacionesSalidaBean.java
 * @description: 
 * @date: 27 de jul. de 2017
 * @author: ARCHY.
 */
public class DonacionesSalidaBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private String codigoAnio;
	private String codigoMes;
	private String nombreDdi;
	private String fechaEmision;
	private Integer idEstado;
	private Integer idIngreso;
	private Integer idDdi;
	private String codigoDdi;
	private Integer idAlmacen;
	private String codAlmacen;
	private String nombreAlmacen;
	private Integer idDonacion;
	private String nombreDonante;
	private String nroOrdenSalida;
	private String nombreMovimiento;
	private Integer idMovimiento;
	private String codSalida;
	private Integer idMedTransporte;
	private Integer idTipoTransporte;
	private Integer idControlCalidad;
	private Integer idChofer;
	private String nroPlaca;
	private String flagControlCalidad;
	private Integer idEmpresaTrans;
	private Integer idResponsable;
	private Integer idTipoMovimiento;
	private Integer idAlmacenProcedencia;
	private String fechaLlegada;
	private String tipoOrigen;
	private String nombreTipoDonacion;
	private Integer idSalida;
	private String codigoUbigeo;
	private Integer idProgramacion;
	private Integer idResponsableExt;
	private Integer idSolicitante;
	private Integer idResponsableRecepcion;
	private Integer idProyectoManifiesto;
	private Integer idAlmacenDestino;
	private Integer idAlmacenDestinoExt;
	private String fechaEntrega;
	private String flagTipoDestino;	
	private String observacion;
	private String nroOrden;
	private Integer idDdiDestino;
	private String codRegion;
	private String codProvincia;
	private String codDepartamento;
	private String codDistrito;
	private String nombreSistema;
	private String nomAlmacenOrigen;
	private String nomAlmacenDestino;
	private String versionSistema;
	private String anio;
	private String mes;
//	private String codigoEstado;
//	private Integer idPaisDonante;
//	private String tipoDonante;
//	private String tipoOrigenDonante;
//	private Integer idOficina;
//	private Integer idPersonal;
//	private Integer idDonante;
//	private String finalidad;
//	private String observacion;
//	private String textoa;
//	private String textob;
//	private String tipoDonacion;
//	private Integer idDee;
//	private String textoCodigo;  
//	private String nombreDeclaratoria;
//	private String representante;
	private String nroGuiaRemision;
	private String nombreEstado;
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
//	public String getCodigoEstado() {
//		return codigoEstado;
//	}
//	/**
//	 * @param codigoEstado the codigoAlmacen to set
//	 */
//	public void setCodigoEstado(String codigoEstado) {
//		this.codigoEstado = codigoEstado;
//	}
//	/**
//	 * @param idPaisDonante the idPaisDonante to set
//	 */
//	public void setIdPaisDonante(Integer idPaisDonante) {
//		this.idPaisDonante = idPaisDonante;
//	}
//	/**
//	 * @return the idPaisDonante
//	 */
//	public Integer getIdPaisDonante() {
//		return idPaisDonante;
//	}
//	/**
//	 * @param tipoDonante the tipoDonante to set
//	 */
//	public void setTipoDonante(String tipoDonante) {
//		this.tipoDonante = tipoDonante;
//	}
//	/**
//	 * @return the tipoDonante
//	 */
//	public String getTipoDonante() {
//		return tipoDonante;
//	}
//	/**
//	 * @param tipoOrigenDonante the tipoOrigenDonante to set
//	 */
//	public void setTipoOrigenDonante(String tipoOrigenDonante) {
//		this.tipoOrigenDonante = tipoOrigenDonante;
//	}
//	/**
//	 * @return the tipoOrigenDonante
//	 */
//	public String getTipoOrigenDonante() {
//		return tipoOrigenDonante;
//	}
//	/**
//	 * @param idOficina the idOficina to set
//	 */
//	public void setIdOficina(Integer idOficina) {
//		this.idOficina = idOficina;
//	}
//	/**
//	 * @return the idOficina
//	 */
//	public Integer getIdOficina() {
//		return idOficina;
//	}
//	/**
//	 * @param idPersonal the idPersonal to set
//	 */
//	public void setIdPersonal(Integer idPersonal) {
//		this.idPersonal = idPersonal;
//	}
//	/**
//	 * @return the idPersonal
//	 */
//	public Integer getIdPersonal() {
//		return idPersonal;
//	}
//	/**
//	 * @param idDonante the idDonante to set
//	 */
//	public void setIdDonante(Integer idDonante) {
//		this.idDonante = idDonante;
//	}
//	/**
//	 * @return the idDonante
//	 */
//	public Integer getIdDonante() {
//		return idDonante;
//	}
//	/**
//	 * @return the finalidad
//	 */
//	public String getFinalidad() {
//		return finalidad;
//	}
//	/**
//	 * @param finalidad the finalidad to set
//	 */
//	public void setFinalidad(String finalidad) {
//		this.finalidad = finalidad;
//	}
//	/**
//	 * @return the observacion
//	 */
//	public String getObservacion() {
//		return observacion;
//	}
//	/**
//	 * @param observacion the observacion to set
//	 */
//	public void setObservacion(String observacion) {
//		this.observacion = observacion;
//	}
//	/**
//	 * @return the textoa
//	 */
//	public String getTextoa() {
//		return textoa;
//	}
//	/**
//	 * @param textoa the textoa to set
//	 */
//	public void setTextoa(String textoa) {
//		this.textoa = textoa;
//	}
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
//	/**
//	 * @param tipoDonacion the tipoDonacion to set
//	 */
//	public void setTipoDonacion(String tipoDonacion) {
//		this.tipoDonacion = tipoDonacion;
//	}
//	/**
//	 * @return the tipoDonacion
//	 */
//	public String getTipoDonacion() {
//		return tipoDonacion;
//	}
//	/**
//	 * @param idDee the idDee to set
//	 */
//	public void setIdDee(Integer idDee) {
//		this.idDee = idDee;
//	}
//	/**
//	 * @return the idDee
//	 */
//	public Integer getIdDee() {
//		return idDee;
//	}
//	/**
//	 * @return the textoCodigo
//	 */
//	public String getTextoCodigo() {
//		return textoCodigo;
//	}
//	/**
//	 * @param textoCodigo the textoCodigo to set
//	 */
//	public void setTextoCodigo(String textoCodigo) {
//		this.textoCodigo = textoCodigo;
//	}
//	/**
//	 * @return the nombreDeclaratoria
//	 */
//	public String getNombreDeclaratoria() {
//		return nombreDeclaratoria;
//	}
//	/**
//	 * @param nombreDeclaratoria the nombreDeclaratoria to set
//	 */
//	public void setNombreDeclaratoria(String nombreDeclaratoria) {
//		this.nombreDeclaratoria = nombreDeclaratoria;
//	}
//	/**
//	 * @return the nombreDeclaratoria
//	 */
//	public String getRepresentante() {
//		return representante;
//	}
//	/**
//	 * @param nombreDeclaratoria the nombreDeclaratoria to set
//	 */
//	public void setRepresentante(String representante) {
//		this.representante = representante;
//	}
	 
	
	public void setIdIngreso(Integer idIngreso) {
		this.idIngreso = idIngreso;
	}

	public Integer getIdIngreso() {
		return idIngreso;
	}
	
	public void setIdAlmacen(Integer idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public Integer getIdAlmacen() {
		return idAlmacen;
	}
	
	public String getCodAlmacen() {
		return codAlmacen;
	}

	public void setCodAlmacen(String codAlmacen) {
		this.codAlmacen = codAlmacen;
	}
	
	public String getNombreAlmacen() {
		return nombreAlmacen;
	}

	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}
	
	public String getNroOrdenSalida() {
		return nroOrdenSalida;
	}

	public void setNroOrdenSalida(String nroOrdenSalida) {
		this.nroOrdenSalida = nroOrdenSalida;
	}
	
	public String getNombreMovimiento() {
		return nombreMovimiento;
	}

	public void setNombreMovimiento(String nombreMovimiento) {
		this.nombreMovimiento = nombreMovimiento;
	}
	
	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Integer getIdMovimiento() {
		return idMovimiento;
	}
	
	public String getCodSalida() {
		return codSalida;
	}

	public void setCodSalida(String codSalida) {
		this.codSalida = codSalida;
	}
	
	public void setIdMedTransporte(Integer idMedTransporte) {
		this.idMedTransporte = idMedTransporte;
	}

	public Integer getIdMedTransporte() {
		return idMedTransporte;
	}
	
	public void setIdTipoTransporte(Integer idTipoTransporte) {
		this.idTipoTransporte = idTipoTransporte;
	}

	public Integer getIdTipoTransporte() {
		return idTipoTransporte;
	}
	
	public void setIdControlCalidad(Integer idControlCalidad) {
		this.idControlCalidad = idControlCalidad;
	}

	public Integer getIdControlCalidad() {
		return idControlCalidad;
	}
	
	public void setIdChofer(Integer idChofer) {
		this.idChofer = idChofer;
	}

	public Integer getIdChofer() {
		return idChofer;
	}
	
	public String getNroPlaca() {
		return nroPlaca;
	}

	public void setNroPlaca(String nroPlaca) {
		this.nroPlaca = nroPlaca;
	}
	
	public String getFlagControlCalidad() {
		return flagControlCalidad;
	}

	public void setFlagControlCalidad(String flagControlCalidad) {
		this.flagControlCalidad = flagControlCalidad;
	}
	
	public void setIdEmpresaTrans(Integer idEmpresaTrans) {
		this.idEmpresaTrans = idEmpresaTrans;
	}

	public Integer getIdEmpresaTrans() {
		return idEmpresaTrans;
	}
	
	public void setIdResponsable(Integer idResponsable) {
		this.idResponsable = idResponsable;
	}

	public Integer getIdResponsable() {
		return idResponsable;
	}
	
	public void setIdTipoMovimiento(Integer idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}

	public Integer getIdTipoMovimiento() {
		return idTipoMovimiento;
	}
	
	public void setIdAlmacenProcedencia(Integer idAlmacenProcedencia) {
		this.idAlmacenProcedencia = idAlmacenProcedencia;
	}

	public Integer getIdAlmacenProcedencia() {
		return idAlmacenProcedencia;
	}
	
	public String getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(String fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}
	
	public String getTipoOrigen() {
		return tipoOrigen;
	}

	public void setTipoOrigen(String tipoOrigen) {
		this.tipoOrigen = tipoOrigen;
	}
	
	public String getNombreTipoDonacion() {
		return nombreTipoDonacion;
	}

	public void setNombreTipoDonacion(String nombreTipoDonacion) {
		this.nombreTipoDonacion = nombreTipoDonacion;
	}
	
	public void setIdSalida(Integer idSalida) {
		this.idSalida = idSalida;
	}

	public Integer getIdSalida() {
		return idSalida;
	}
	
	public String getCodigoUbigeo() {
		return codigoUbigeo;
	}
	/**
	 * @param codigoUbigeo the codigoUbigeo to set
	 */
	public void setCodigoUbigeo(String codigoUbigeo) {
		this.codigoUbigeo = codigoUbigeo;
	}
	
	public Integer getIdProgramacion() {
		return idProgramacion;
	}
	/**
	 * @param idProgramacion the idProgramacion to set
	 */
	public void setIdProgramacion(Integer idProgramacion) {
		this.idProgramacion = idProgramacion;
	}
	
	public Integer getIdResponsableExt() {
		return idResponsableExt;
	}
	/**
	 * @param idResponsableExt the idResponsableExt to set
	 */
	public void setIdResponsableExt(Integer idResponsableExt) {
		this.idResponsableExt = idResponsableExt;
	}
	
	public Integer getIdSolicitante() {
		return idSolicitante;
	}
	/**
	 * @param idSolicitante the idSolicitante to set
	 */
	public void setIdSolicitante(Integer idSolicitante) {
		this.idSolicitante = idSolicitante;
	}
	
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
	
	public String getNroOrden() {
		return nroOrden;
	}

	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}
	
	public Integer getIdDdiDestino() {
		return idDdiDestino;
	}
	/**
	 * @param idDdi the idDdi to set
	 */
	public void setIdDdiDestino(Integer idDdiDestino) {
		this.idDdiDestino = idDdiDestino;
	}
	
	public String getCodRegion() {
		return codRegion;
	}

	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
	}
	
	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}
	
	public String getCodDepartamento() {
		return codDepartamento;
	}

	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
	
	public String getCodDistrito() {
		return codDistrito;
	}

	public void setCodDistrito(String codDistrito) {
		this.codDistrito = codDistrito;
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
	 * @return the nomAlmacenOrigen
	 */
	public String getNomAlmacenOrigen() {
		return nomAlmacenOrigen;
	}
	/**
	 * @param nomAlmacenOrigen the nomAlmacenOrigen to set
	 */
	public void setNomAlmacenOrigen(String nomAlmacenOrigen) {
		this.nomAlmacenOrigen = nomAlmacenOrigen;
	}
	/**
	 * @return the nomAlmacenDestino
	 */
	public String getNomAlmacenDestino() {
		return nomAlmacenDestino;
	}
	/**
	 * @param nomAlmacenDestino the nomAlmacenDestino to set
	 */
	public void setNomAlmacenDestino(String nomAlmacenDestino) {
		this.nomAlmacenDestino = nomAlmacenDestino;
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
	/**
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}
	/**
	 * @param anio the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}
	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}
	/**
	 * @param mes the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

}
