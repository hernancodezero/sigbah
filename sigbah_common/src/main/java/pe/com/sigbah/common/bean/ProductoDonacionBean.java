package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ProductoBean.java
 * @description: Clase ProductoDonacionBean.
 * @date: 18/07/2017 
 * @author: ARCHY.
 */
public class ProductoDonacionBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	
	private Integer idProducto;
	private Integer idDonacion;
	private Integer idDetDonacion;
	private Integer idMoneda;
	private Double cantidad;
	private String fecVencimiento;
	private Double monOrigen;
	private Double monSoles;
	private Double monDolares;
	private String usuario;
	
	private String nombreProducto;
	private String unidadMedida;
	private Integer idCategoria;
	private String nombreCategoria;
	private String nombreMoneda;
	private BigDecimal precio; 
	
	private BigDecimal cantidadIngreso; 
	private BigDecimal cantidadSalida; 
	private BigDecimal importeSalida; 
	private BigDecimal importeIngreso; 
	private BigDecimal saldo; 
	
	private String tipoOrigen;
	private Integer idDdi;
	private String nombreDdi;
	private Integer idAlmacen;
	private String nombreAlmacen;
	private BigDecimal importeTotal; 
	
	private String entidadDestino;
	private String nroOrdenSalida;

	private String codigoAnio;
	private String codigoMes;
	private String nombreMes;
	private String nombreSistema;
	private String nroKardex;
	private String motivo;
	private String nroOrden;
	private String tipo;

	/**
	 * 
	 */
	public ProductoDonacionBean() {
		super();
	}
	
	public ProductoDonacionBean(Integer idDetDonacion) {
		super();
		this.idDetDonacion = idDetDonacion;
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

	
	//////////////////
	
	public Integer getIdDonacion() {
		return idDonacion;
	}

	public void setIdDonacion(Integer idDonacion) {
		this.idDonacion = idDonacion;
	}
	
	public Integer getIdDetDonacion() {
		return idDetDonacion;
	}

	public void setIdDetDonacion(Integer idDetDonacion) {
		this.idDetDonacion = idDetDonacion;
	}
	
	public Integer getIdMoneda() {
		return idMoneda;
	}

	public void setIdMoneda(Integer idMoneda) {
		this.idMoneda = idMoneda;
	}
	
	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getFecVencimiento() {
		return fecVencimiento;
	}

	public void setFecVencimiento(String fecVencimiento) {
		this.fecVencimiento = fecVencimiento;
	}
	
	public Double getMonOrigen() {
		return monOrigen;
	}

	public void setMonOrigen(Double monOrigen) {
		this.monOrigen = monOrigen;
	}
	
	public Double getMonSoles() {
		return monSoles;
	}

	public void setMonSoles(Double monSoles) {
		this.monSoles = monSoles;
	}
	
	public Double getMonDolares() {
		return monDolares;
	}

	public void setMonDolares(Double monDolares) {
		this.monDolares = monDolares;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	
	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
	public String getNombreMoneda() {
		return nombreMoneda;
	}

	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public BigDecimal getCantidadIngreso() {
		return cantidadIngreso;
	}

	public void setCantidadIngreso(BigDecimal cantidadIngreso) {
		this.cantidadIngreso = cantidadIngreso;
	}

	public BigDecimal getCantidadSalida() {
		return cantidadSalida;
	}

	public void setCantidadSalida(BigDecimal cantidadSalida) {
		this.cantidadSalida = cantidadSalida;
	}

	public BigDecimal getImporteSalida() {
		return importeSalida;
	}

	public void setImporteSalida(BigDecimal importeSalida) {
		this.importeSalida = importeSalida;
	}

	public BigDecimal getImporteIngreso() {
		return importeIngreso;
	}

	public void setImporteIngreso(BigDecimal importeIngreso) {
		this.importeIngreso = importeIngreso;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
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
	 * @return the entidadDestino
	 */
	public String getEntidadDestino() {
		return entidadDestino;
	}

	/**
	 * @param entidadDestino the entidadDestino to set
	 */
	public void setEntidadDestino(String entidadDestino) {
		this.entidadDestino = entidadDestino;
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
	 * @return the nroKardex
	 */
	public String getNroKardex() {
		return nroKardex;
	}

	/**
	 * @param nroKardex the nroKardex to set
	 */
	public void setNroKardex(String nroKardex) {
		this.nroKardex = nroKardex;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
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


}