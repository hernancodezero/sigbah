package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ProductoBean.java
 * @description: Clase ProductoBean.
 * @date: 18/02/2015 22:26:30
 * @author: Junior Huaman Flores.
 */
public class ProductoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer idAlmacen;
	private Integer idProducto;
	private String nombreProducto;
	private String codigoProducto;
	private Integer idUnidadMedida;
	private String nombreUnidadMedida;
	private Integer idEnvase;
	private String nombreEnvase;
	private Integer idCategoria;
	private BigDecimal pesoUnitarioNeto;
	private BigDecimal pesoUnitarioBruto;
	private BigDecimal volumenUnitario;
	private Integer idDonacion;
	private String tipoOrigen;
	private String tipoProducto;
	
	private BigDecimal cantidad;
	private BigDecimal precioUnitario;
	
	private String nombreDdi;
	private String nombreCategoria;
	private String nombreAlmacen;
	private Integer idDdi;
	private String codigoAnio;
	private String nroLote;
	private String tipoMovimiento;
	private Integer idTipoMovimiento;
	private Integer idSalida;
	private String ordenCompra;
	private String tipoControl;
	
	private BigDecimal cantidad1;
	private String fecha1;
	private BigDecimal cantidad2;
	private String fecha2;
	private BigDecimal cantidad3;
	private String fecha3;
	private BigDecimal cantidad4;
	private String fecha4;
	private BigDecimal cantidad5;
	private String fecha5;
	private BigDecimal cantidad6;
	private String fecha6;
	private BigDecimal cantidad7;
	private String fecha7;
	private BigDecimal cantidad8;
	private String fecha8;
	private BigDecimal cantidad9;
	private String fecha9;
	private BigDecimal cantidad10;
	private String fecha10;
	private BigDecimal cantidad11;
	private String fecha11;
	private BigDecimal cantidad12;
	private String fecha12;
	
	private String nroOrden;
	private String usuario;
	public String getNombreDdi() {
		return nombreDdi;
	}

	public void setNombreDdi(String nombreDdi) {
		this.nombreDdi = nombreDdi;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getNombreAlmacen() {
		return nombreAlmacen;
	}

	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}

	public BigDecimal getCantidad1() {
		return cantidad1;
	}

	public void setCantidad1(BigDecimal cantidad1) {
		this.cantidad1 = cantidad1;
	}

	public String getFecha1() {
		return fecha1;
	}

	public void setFecha1(String fecha1) {
		this.fecha1 = fecha1;
	}

	public BigDecimal getCantidad2() {
		return cantidad2;
	}

	public void setCantidad2(BigDecimal cantidad2) {
		this.cantidad2 = cantidad2;
	}

	public String getFecha2() {
		return fecha2;
	}

	public void setFecha2(String fecha2) {
		this.fecha2 = fecha2;
	}

	public BigDecimal getCantidad3() {
		return cantidad3;
	}

	public void setCantidad3(BigDecimal cantidad3) {
		this.cantidad3 = cantidad3;
	}

	public String getFecha3() {
		return fecha3;
	}

	public void setFecha3(String fecha3) {
		this.fecha3 = fecha3;
	}

	public BigDecimal getCantidad4() {
		return cantidad4;
	}

	public void setCantidad4(BigDecimal cantidad4) {
		this.cantidad4 = cantidad4;
	}

	public String getFecha4() {
		return fecha4;
	}

	public void setFecha4(String fecha4) {
		this.fecha4 = fecha4;
	}

	public BigDecimal getCantidad5() {
		return cantidad5;
	}

	public void setCantidad5(BigDecimal cantidad5) {
		this.cantidad5 = cantidad5;
	}

	public String getFecha5() {
		return fecha5;
	}

	public void setFecha5(String fecha5) {
		this.fecha5 = fecha5;
	}

	public BigDecimal getCantidad6() {
		return cantidad6;
	}

	public void setCantidad6(BigDecimal cantidad6) {
		this.cantidad6 = cantidad6;
	}

	public String getFecha6() {
		return fecha6;
	}

	public void setFecha6(String fecha6) {
		this.fecha6 = fecha6;
	}

	public BigDecimal getCantidad7() {
		return cantidad7;
	}

	public void setCantidad7(BigDecimal cantidad7) {
		this.cantidad7 = cantidad7;
	}

	public String getFecha7() {
		return fecha7;
	}

	public void setFecha7(String fecha7) {
		this.fecha7 = fecha7;
	}

	public BigDecimal getCantidad8() {
		return cantidad8;
	}

	public void setCantidad8(BigDecimal cantidad8) {
		this.cantidad8 = cantidad8;
	}

	public String getFecha8() {
		return fecha8;
	}

	public void setFecha8(String fecha8) {
		this.fecha8 = fecha8;
	}

	public BigDecimal getCantidad9() {
		return cantidad9;
	}

	public void setCantidad9(BigDecimal cantidad9) {
		this.cantidad9 = cantidad9;
	}

	public String getFecha9() {
		return fecha9;
	}

	public void setFecha9(String fecha9) {
		this.fecha9 = fecha9;
	}

	public BigDecimal getCantidad10() {
		return cantidad10;
	}

	public void setCantidad10(BigDecimal cantidad10) {
		this.cantidad10 = cantidad10;
	}

	public String getFecha10() {
		return fecha10;
	}

	public void setFecha10(String fecha10) {
		this.fecha10 = fecha10;
	}

	public BigDecimal getCantidad11() {
		return cantidad11;
	}

	public void setCantidad11(BigDecimal cantidad11) {
		this.cantidad11 = cantidad11;
	}

	public String getFecha11() {
		return fecha11;
	}

	public void setFecha11(String fecha11) {
		this.fecha11 = fecha11;
	}

	public BigDecimal getCantidad12() {
		return cantidad12;
	}

	public void setCantidad12(BigDecimal cantidad12) {
		this.cantidad12 = cantidad12;
	}

	public String getFecha12() {
		return fecha12;
	}

	public void setFecha12(String fecha12) {
		this.fecha12 = fecha12;
	}

	
	
	
	
	/**
	 * 
	 */
	public ProductoBean() {
		super();
	}
	
	/**
	 * @param idProducto
	 * @param idCategoria
	 */
	public ProductoBean(Integer idProducto, Integer idCategoria) {
		super();
		this.idProducto = idProducto;
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
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the idUnidadMedida
	 */
	public Integer getIdUnidadMedida() {
		return idUnidadMedida;
	}

	/**
	 * @param idUnidadMedida the idUnidadMedida to set
	 */
	public void setIdUnidadMedida(Integer idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}

	/**
	 * @return the nombreUnidadMedida
	 */
	public String getNombreUnidadMedida() {
		return nombreUnidadMedida;
	}

	/**
	 * @param nombreUnidadMedida the nombreUnidadMedida to set
	 */
	public void setNombreUnidadMedida(String nombreUnidadMedida) {
		this.nombreUnidadMedida = nombreUnidadMedida;
	}

	/**
	 * @return the idEnvase
	 */
	public Integer getIdEnvase() {
		return idEnvase;
	}

	/**
	 * @param idEnvase the idEnvase to set
	 */
	public void setIdEnvase(Integer idEnvase) {
		this.idEnvase = idEnvase;
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
	 * @return the tipoMovimiento
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * @param tipoMovimiento the tipoMovimiento to set
	 */
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	/**
	 * @return the idTipoMovimiento
	 */
	public Integer getIdTipoMovimiento() {
		return idTipoMovimiento;
	}

	/**
	 * @param idTipoMovimiento the idTipoMovimiento to set
	 */
	public void setIdTipoMovimiento(Integer idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
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
	 * @return the ordenCompra
	 */
	public String getOrdenCompra() {
		return ordenCompra;
	}

	/**
	 * @param ordenCompra the ordenCompra to set
	 */
	public void setOrdenCompra(String ordenCompra) {
		this.ordenCompra = ordenCompra;
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
	 * @return the tipoProducto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
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
	 * @return the nroOrden
	 */
	public String getNroOrden() {
		return nroOrden;
	}

	/**
	 * @param nroOrden the nroOrden to set
	 */
	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}