package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ProductoSalidaBean.java
 * @description: 
 * @date: 25 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoProyectoManifiestoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idDetalleProyecto;
	private Integer idProyectoManifiesto;
	private Integer idCategoria;
	private Integer idProducto;
	private String nombreProducto;
	private String nombreUnidad;
	private String nombreEnvase;
	private BigDecimal cantidad;
	private BigDecimal pesoUnitarioBruto;
	private BigDecimal pesoTotal;
	private BigDecimal volumenUnitario;
	private BigDecimal volumenTotal;
	private BigDecimal costoBruto;
	private BigDecimal costoTotal;
	private String arrIdDetalleProyecto;
	private Integer idAlmacen;
	private Integer idMovimiento;
	private String codigoAnio;
	private String codigoMesInicio;
	private String codigoMesFin;
	private String tipoOrigen;
	private String nombreDdi;
	private String nombreAlmacen;
	private String fechaEmision;
	private String nroProyectoManifiesto;
	private String nroProgramacion;
	private String nombreMovimiento;
	private String nombreAlmacenDestino;
	private String nombreEstado;
	private Integer idSalida;
	private BigDecimal cantidadProgramada;
	private BigDecimal cantidadDespachada;
	private BigDecimal cantidadPorDespachar;
	private BigDecimal cantidadSalida;
	private BigDecimal stockAlmacen;
	
	
	/**
	 * 
	 */
	public ProductoProyectoManifiestoBean() {
		super();
	}

	/**
	 * @param idDetalleProyecto
	 */
	public ProductoProyectoManifiestoBean(Integer idDetalleProyecto) {
		super();
		this.idDetalleProyecto = idDetalleProyecto;
	}

	/**
	 * @return the idDetalleProyecto
	 */
	public Integer getIdDetalleProyecto() {
		return idDetalleProyecto;
	}

	/**
	 * @param idDetalleProyecto the idDetalleProyecto to set
	 */
	public void setIdDetalleProyecto(Integer idDetalleProyecto) {
		this.idDetalleProyecto = idDetalleProyecto;
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
	 * @return the pesoUnitarioBruto
	 */
	public BigDecimal getPesoUnitarioBruto() {
		return pesoUnitarioBruto;
	}

	/**
	 * @param pesoUnitarioBruto the pesoUnitarioBruto to set
	 */
	public void setPesoUnitarioBruto(BigDecimal pesoUnitarioBruto) {
		this.pesoUnitarioBruto = pesoUnitarioBruto;
	}

	/**
	 * @return the pesoTotal
	 */
	public BigDecimal getPesoTotal() {
		return pesoTotal;
	}

	/**
	 * @param pesoTotal the pesoTotal to set
	 */
	public void setPesoTotal(BigDecimal pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

	/**
	 * @return the volumenUnitario
	 */
	public BigDecimal getVolumenUnitario() {
		return volumenUnitario;
	}

	/**
	 * @param volumenUnitario the volumenUnitario to set
	 */
	public void setVolumenUnitario(BigDecimal volumenUnitario) {
		this.volumenUnitario = volumenUnitario;
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
	 * @return the costoBruto
	 */
	public BigDecimal getCostoBruto() {
		return costoBruto;
	}

	/**
	 * @param costoBruto the costoBruto to set
	 */
	public void setCostoBruto(BigDecimal costoBruto) {
		this.costoBruto = costoBruto;
	}

	/**
	 * @return the costoTotal
	 */
	public BigDecimal getCostoTotal() {
		return costoTotal;
	}

	/**
	 * @param costoTotal the costoTotal to set
	 */
	public void setCostoTotal(BigDecimal costoTotal) {
		this.costoTotal = costoTotal;
	}

	/**
	 * @return the arrIdDetalleProyecto
	 */
	public String getArrIdDetalleProyecto() {
		return arrIdDetalleProyecto;
	}

	/**
	 * @param arrIdDetalleProyecto the arrIdDetalleProyecto to set
	 */
	public void setArrIdDetalleProyecto(String arrIdDetalleProyecto) {
		this.arrIdDetalleProyecto = arrIdDetalleProyecto;
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
	 * @return the cantidadProgramada
	 */
	public BigDecimal getCantidadProgramada() {
		return cantidadProgramada;
	}

	/**
	 * @param cantidadProgramada the cantidadProgramada to set
	 */
	public void setCantidadProgramada(BigDecimal cantidadProgramada) {
		this.cantidadProgramada = cantidadProgramada;
	}

	/**
	 * @return the cantidadDespachada
	 */
	public BigDecimal getCantidadDespachada() {
		return cantidadDespachada;
	}

	/**
	 * @param cantidadDespachada the cantidadDespachada to set
	 */
	public void setCantidadDespachada(BigDecimal cantidadDespachada) {
		this.cantidadDespachada = cantidadDespachada;
	}

	/**
	 * @return the cantidadPorDespachar
	 */
	public BigDecimal getCantidadPorDespachar() {
		return cantidadPorDespachar;
	}

	/**
	 * @param cantidadPorDespachar the cantidadPorDespachar to set
	 */
	public void setCantidadPorDespachar(BigDecimal cantidadPorDespachar) {
		this.cantidadPorDespachar = cantidadPorDespachar;
	}

	/**
	 * @return the cantidadSalida
	 */
	public BigDecimal getCantidadSalida() {
		return cantidadSalida;
	}

	/**
	 * @param cantidadSalida the cantidadSalida to set
	 */
	public void setCantidadSalida(BigDecimal cantidadSalida) {
		this.cantidadSalida = cantidadSalida;
	}

	/**
	 * @return the stockAlmacen
	 */
	public BigDecimal getStockAlmacen() {
		return stockAlmacen;
	}

	/**
	 * @param stockAlmacen the stockAlmacen to set
	 */
	public void setStockAlmacen(BigDecimal stockAlmacen) {
		this.stockAlmacen = stockAlmacen;
	}

}
