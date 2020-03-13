package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: OrdenIngresoBean.java
 * @description: 
 * @date: 3 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class OrdenIngresoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idIngreso;
	private Integer item;
	private String codigoAnio;
	private String codigoDdi;
	private String nombreDdi;
	private Integer idAlmacen;
	private String nombreAlmacen;
	private String codigoIngreso;
	private String nroOrdenIngreso;
	private String fechaEmision;
	private Integer idMovimiento;
	private String codigoMovimiento;
	private String nombreMovimiento;
	private String nombreEstado;
	private String tipoOrigen;
	private String codigoMes;
	private Integer idDdi;
	private String codigoAlmacen;
	private String tipo;	
	private Integer idMedioTransporte;
	private Integer idAlmacenProcedencia;
	private Integer idProveedor;
	private Integer idControlCalidad;
	private Integer idChofer;
	private String nroPlaca;
	private String flagTipoCompra;
	private String fechaLlegada;
	private String observacion;
	private Integer idEstado;
	private String nroOrdenCompra;
	private String flagControlCalidad;
	private Integer idEmpresaTransporte;
	private Integer idResponsable;
	private String tipoIngreso;
	private String codigoOrdenIngreso;
	private Integer idDonacion;
	private String provRep;
	private String concepto;
	private String representante;
	private String nombreAlmacenProcedencia;
	private String empresaTransporte;
	private String nombreChofer;
	private String responsable;
	private String codigoMesInicio;
	private String codigoMesFin;
	private BigDecimal importeTotal;
	private BigDecimal pesoTotalKgr;
	private String razonSocial;
	private Integer idSalida;
	private String nombreMes;
	
	private Integer idProducto;
	
	private String codigoSiga;
	private String nombreProducto;
	private String nombreUnidad;
	private BigDecimal cantidad;
	private BigDecimal precio;
	private BigDecimal importe;
	private String nombreMarca;
	private String fechaVencimiento;
	private String nombreAlmacenOrigen;
	private String nroOrdenSalida;
	private String nombreAlmacenDestino;
	private String destino;
	private String tipoExportacion;
	/**
	 * @return the idIngreso
	 */
	public Integer getIdIngreso() {
		return idIngreso;
	}
	/**
	 * @param idIngreso the idIngreso to set
	 */
	public void setIdIngreso(Integer idIngreso) {
		this.idIngreso = idIngreso;
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
	 * @return the nroOrdenIngreso
	 */
	public String getNroOrdenIngreso() {
		return nroOrdenIngreso;
	}
	/**
	 * @param nroOrdenIngreso the nroOrdenIngreso to set
	 */
	public void setNroOrdenIngreso(String nroOrdenIngreso) {
		this.nroOrdenIngreso = nroOrdenIngreso;
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
	 * @return the codigoIngreso
	 */
	public String getCodigoIngreso() {
		return codigoIngreso;
	}
	/**
	 * @param codigoIngreso the codigoIngreso to set
	 */
	public void setCodigoIngreso(String codigoIngreso) {
		this.codigoIngreso = codigoIngreso;
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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * @return the idAlmacenProcedencia
	 */
	public Integer getIdAlmacenProcedencia() {
		return idAlmacenProcedencia;
	}
	/**
	 * @param idAlmacenProcedencia the idAlmacenProcedencia to set
	 */
	public void setIdAlmacenProcedencia(Integer idAlmacenProcedencia) {
		this.idAlmacenProcedencia = idAlmacenProcedencia;
	}
	/**
	 * @return the idProveedor
	 */
	public Integer getIdProveedor() {
		return idProveedor;
	}
	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}
	/**
	 * @return the idControlCalidad
	 */
	public Integer getIdControlCalidad() {
		return idControlCalidad;
	}
	/**
	 * @param idControlCalidad the idControlCalidad to set
	 */
	public void setIdControlCalidad(Integer idControlCalidad) {
		this.idControlCalidad = idControlCalidad;
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
	 * @return the flagTipoCompra
	 */
	public String getFlagTipoCompra() {
		return flagTipoCompra;
	}
	/**
	 * @param flagTipoCompra the flagTipoCompra to set
	 */
	public void setFlagTipoCompra(String flagTipoCompra) {
		this.flagTipoCompra = flagTipoCompra;
	}
	/**
	 * @return the fechaLlegada
	 */
	public String getFechaLlegada() {
		return fechaLlegada;
	}
	/**
	 * @param fechaLlegada the fechaLlegada to set
	 */
	public void setFechaLlegada(String fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
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
	 * @return the nroOrdenCompra
	 */
	public String getNroOrdenCompra() {
		return nroOrdenCompra;
	}
	/**
	 * @param nroOrdenCompra the nroOrdenCompra to set
	 */
	public void setNroOrdenCompra(String nroOrdenCompra) {
		this.nroOrdenCompra = nroOrdenCompra;
	}
	/**
	 * @return the flagControlCalidad
	 */
	public String getFlagControlCalidad() {
		return flagControlCalidad;
	}
	/**
	 * @param flagControlCalidad the flagControlCalidad to set
	 */
	public void setFlagControlCalidad(String flagControlCalidad) {
		this.flagControlCalidad = flagControlCalidad;
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
	 * @return the tipoIngreso
	 */
	public String getTipoIngreso() {
		return tipoIngreso;
	}
	/**
	 * @param tipoIngreso the tipoIngreso to set
	 */
	public void setTipoIngreso(String tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}
	/**
	 * @return the codigoOrdenIngreso
	 */
	public String getCodigoOrdenIngreso() {
		return codigoOrdenIngreso;
	}
	/**
	 * @param codigoOrdenIngreso the codigoOrdenIngreso to set
	 */
	public void setCodigoOrdenIngreso(String codigoOrdenIngreso) {
		this.codigoOrdenIngreso = codigoOrdenIngreso;
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
	 * @return the provRep
	 */
	public String getProvRep() {
		return provRep;
	}
	/**
	 * @param provRep the provRep to set
	 */
	public void setProvRep(String provRep) {
		this.provRep = provRep;
	}
	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}
	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * @return the representante
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
	 * @return the nombreAlmacenProcedencia
	 */
	public String getNombreAlmacenProcedencia() {
		return nombreAlmacenProcedencia;
	}
	/**
	 * @param nombreAlmacenProcedencia the nombreAlmacenProcedencia to set
	 */
	public void setNombreAlmacenProcedencia(String nombreAlmacenProcedencia) {
		this.nombreAlmacenProcedencia = nombreAlmacenProcedencia;
	}
	/**
	 * @return the empresaTransporte
	 */
	public String getEmpresaTransporte() {
		return empresaTransporte;
	}
	/**
	 * @param empresaTransporte the empresaTransporte to set
	 */
	public void setEmpresaTransporte(String empresaTransporte) {
		this.empresaTransporte = empresaTransporte;
	}
	/**
	 * @return the nombreChofer
	 */
	public String getNombreChofer() {
		return nombreChofer;
	}
	/**
	 * @param nombreChofer the nombreChofer to set
	 */
	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}
	/**
	 * @return the responsable
	 */
	public String getResponsable() {
		return responsable;
	}
	/**
	 * @param responsable the responsable to set
	 */
	public void setResponsable(String responsable) {
		this.responsable = responsable;
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
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
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
	 * @return the codigoSiga
	 */
	public String getCodigoSiga() {
		return codigoSiga;
	}
	/**
	 * @param codigoSiga the codigoSiga to set
	 */
	public void setCodigoSiga(String codigoSiga) {
		this.codigoSiga = codigoSiga;
	}
	/**
	 * @return the nombreProducto
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}
	/**
	 * @param nombreProducto the nombreProducto to set
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	/**
	 * @return the nombreUnidad
	 */
	public String getNombreUnidad() {
		return nombreUnidad;
	}
	/**
	 * @param nombreUnidad the nombreUnidad to set
	 */
	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}
	/**
	 * @return the cantidad
	 */
	public BigDecimal getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the precio
	 */
	public BigDecimal getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	/**
	 * @return the nombreMarca
	 */
	public String getNombreMarca() {
		return nombreMarca;
	}
	/**
	 * @param nombreMarca the nombreMarca to set
	 */
	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}
	/**
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	/**
	 * @return the nombreAlmacenOrigen
	 */
	public String getNombreAlmacenOrigen() {
		return nombreAlmacenOrigen;
	}
	/**
	 * @param nombreAlmacenOrigen the nombreAlmacenOrigen to set
	 */
	public void setNombreAlmacenOrigen(String nombreAlmacenOrigen) {
		this.nombreAlmacenOrigen = nombreAlmacenOrigen;
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
	 * @return the destino
	 */
	public String getDestino() {
		return destino;
	}
	/**
	 * @param destino the destino to set
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}
	/**
	 * @return the tipoExportacion
	 */
	public String getTipoExportacion() {
		return tipoExportacion;
	}
	/**
	 * @param tipoExportacion the tipoExportacion to set
	 */
	public void setTipoExportacion(String tipoExportacion) {
		this.tipoExportacion = tipoExportacion;
	}

}
