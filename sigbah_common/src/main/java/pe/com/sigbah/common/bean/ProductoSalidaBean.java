package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ProductoSalidaBean.java
 * @description: 
 * @date: 25 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoSalidaBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idDetalleSalida;
	private Integer idSalida;
	private Integer idCategoria;
	private Integer idProducto;
	private String nombreProducto;
	private String nombreUnidad;
	private String nombreEnvase;
	private BigDecimal cantidad;
	private BigDecimal precioUnitario;
	private BigDecimal importeTotal;
	private BigDecimal pesoNetoTotal;
	private BigDecimal pesoBrutoTotal;
	private BigDecimal pesoUnitarioNeto;
	private BigDecimal pesoUnitarioBruto;
	private String nroLote;
	private String indControl;
	private String arrIdDetalleSalida;
	private String codigoAnio;
	private String codigoMesInicio;
	private String codigoMesFin;
	private Integer idAlmacen;
	private Integer idMovimiento;
	private String tipoOrigen;
	private String nombreDdi;
	private String nombreAlmacen;
	private String fechaEmision;
	private String nroOrdenSalida;
	private String nroGuiaRemision;
	private String nombreMovimiento;
	private String nombreAlmacenDestino;
	private String nombreEstado;
	
	
	/**
	 * 
	 */
	public ProductoSalidaBean() {
		super();
	}

	/**
	 * @param idDetalleSalida
	 */
	public ProductoSalidaBean(Integer idDetalleSalida) {
		super();
		this.idDetalleSalida = idDetalleSalida;
	}

	/**
	 * @return the idDetalleSalida
	 */
	public Integer getIdDetalleSalida() {
		return idDetalleSalida;
	}

	/**
	 * @param idDetalleSalida the idDetalleSalida to set
	 */
	public void setIdDetalleSalida(Integer idDetalleSalida) {
		this.idDetalleSalida = idDetalleSalida;
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
	 * @return the pesoNetoTotal
	 */
	public BigDecimal getPesoNetoTotal() {
		return pesoNetoTotal;
	}

	/**
	 * @param pesoNetoTotal the pesoNetoTotal to set
	 */
	public void setPesoNetoTotal(BigDecimal pesoNetoTotal) {
		this.pesoNetoTotal = pesoNetoTotal;
	}

	/**
	 * @return the pesoBrutoTotal
	 */
	public BigDecimal getPesoBrutoTotal() {
		return pesoBrutoTotal;
	}

	/**
	 * @param pesoBrutoTotal the pesoBrutoTotal to set
	 */
	public void setPesoBrutoTotal(BigDecimal pesoBrutoTotal) {
		this.pesoBrutoTotal = pesoBrutoTotal;
	}
	
	/**
	 * @return the pesoUnitarioNeto
	 */
	public BigDecimal getPesoUnitarioNeto() {
		return pesoUnitarioNeto;
	}

	/**
	 * @param pesoUnitarioNeto the pesoUnitarioNeto to set
	 */
	public void setPesoUnitarioNeto(BigDecimal pesoUnitarioNeto) {
		this.pesoUnitarioNeto = pesoUnitarioNeto;
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
	 * @return the arrIdDetalleSalida
	 */
	public String getArrIdDetalleSalida() {
		return arrIdDetalleSalida;
	}

	/**
	 * @param arrIdDetalleSalida the arrIdDetalleSalida to set
	 */
	public void setArrIdDetalleSalida(String arrIdDetalleSalida) {
		this.arrIdDetalleSalida = arrIdDetalleSalida;
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
	
}
