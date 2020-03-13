package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ProductoIngresoBean.java
 * @description: 
 * @date: 25 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoIngresoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idDetalleIngreso;
	private Integer idIngreso;
	private Integer idCategoria;
	private Integer idProducto;
	private String nombreProducto;
	private String nombreUnidad;
	private String nombreEnvase;
	private BigDecimal cantidad;
	private BigDecimal precioUnitario;
	private BigDecimal importeTotal;
	private String fechaVencimiento;
	private String fechaIngreso;
	private Integer idProgramacion;
	private Integer idDonacion;
	private String nroLote;
	private String arrIdDetalleIngreso;
	private String codigoAnio;
	private String codigoMesInicio;
	private String codigoMesFin;
	private Integer idAlmacen;
	private Integer idMovimiento;
	private String tipoOrigen;
	private String nombreDdi;
	private String nombreAlmacen;
	private String nombreAlmacenProcedencia;
	private String fechaEmision;
	private String nroOrdenIngreso;
	private String nroOrdenCompra;
	private String nombreMovimiento;
	private String nombreAlmacenDestino;
	private String nombreEstado;
	private BigDecimal pesoTotalKgr;
	private Integer idDdi;
	private Integer idEstado;
	
	
	/**
	 * 
	 */
	public ProductoIngresoBean() {
		super();
	}

	/**
	 * @param idDetalleIngreso
	 */
	public ProductoIngresoBean(Integer idDetalleIngreso) {
		super();
		this.idDetalleIngreso = idDetalleIngreso;
	}

	/**
	 * @return the idCategoria
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}
	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return the idDetalleIngreso
	 */
	public Integer getIdDetalleIngreso() {
		return idDetalleIngreso;
	}

	/**
	 * @param idDetalleIngreso the idDetalleIngreso to set
	 */
	public void setIdDetalleIngreso(Integer idDetalleIngreso) {
		this.idDetalleIngreso = idDetalleIngreso;
	}

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
	 * @return the precioUnitario
	 */
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
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
	 * @return the fechaIngreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
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
	 * @return the nroLote
	 */
	public String getNroLote() {
		return nroLote;
	}

	/**
	 * @param nroLote the nroLote to set
	 */
	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}

	/**
	 * @return the arrIdDetalleIngreso
	 */
	public String getArrIdDetalleIngreso() {
		return arrIdDetalleIngreso;
	}

	/**
	 * @param arrIdDetalleIngreso the arrIdDetalleIngreso to set
	 */
	public void setArrIdDetalleIngreso(String arrIdDetalleIngreso) {
		this.arrIdDetalleIngreso = arrIdDetalleIngreso;
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
	 * @return the nombreEnvase
	 */
	public String getNombreEnvase() {
		return nombreEnvase;
	}

	/**
	 * @param nombreEnvase the nombreEnvase to set
	 */
	public void setNombreEnvase(String nombreEnvase) {
		this.nombreEnvase = nombreEnvase;
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

}
