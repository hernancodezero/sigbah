package pe.com.sigbah.common.bean;

/**
 * @className: ProductoDonacionIngresoBean.java
 * @description: Clase ProductoDonacionIngresoBean.
 * @date: 21/07/2017 
 * @author: ARCHY.
 */
public class ProductoDonacionIngresoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	
	private Integer idIngreso;
	private Integer idIngresoDet;
	private Integer idProducto;
	private String nombreProducto;
	private String unidadMedida;
	private Double cantidad;
	private Double precioUnitario;
	private Double importeTotal;
	private String fecVencimiento;
	private Integer idDonacion;
	
	private Integer idDdi;
	private String codDdi;
	private Integer idAlmacen;
	private String codAlmacen;
	private String nombreEnvase;
	private String estadoConservacion;
	private String codigoAnio;
	private String codigoMes;
	private String nroLote;

	/**
	 * 
	 */
	public ProductoDonacionIngresoBean() {
		super();
	}

	public Integer getIdIngreso() {
		return idIngreso;
	}

	public void setIdIngreso(Integer idIngreso) {
		this.idIngreso = idIngreso;
	}

	
	//////////////////
	
	public Integer getIdIngresoDet() {
		return idIngresoDet;
	}

	public void setIdIngresoDet(Integer idIngresoDet) {
		this.idIngresoDet = idIngresoDet;
	}
	
	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	
	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	public String getFecVencimiento() {
		return fecVencimiento;
	}

	public void setFecVencimiento(String fecVencimiento) {
		this.fecVencimiento = fecVencimiento;
	}
	
	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}
	
	public Integer getIdDonacion() {
		return idDonacion;
	}

	public void setIdDonacion(Integer idDonacion) {
		this.idDonacion = idDonacion;
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
	
	public Integer getIdDdi() {
		return idDdi;
	}

	public void setIdDdi(Integer idDdi) {
		this.idDdi = idDdi;
	}
	
	public String getCodDdi() {
		return codDdi;
	}

	public void setCodDdi(String codDdi) {
		this.codDdi = codDdi;
	}
	
	public Integer getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(Integer idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	
	public String getCodAlmacen() {
		return codAlmacen;
	}

	public void setCodAlmacen(String codAlmacen) {
		this.codAlmacen = codAlmacen;
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
	 * @return the estadoConservacion
	 */
	public String getEstadoConservacion() {
		return estadoConservacion;
	}

	/**
	 * @param estadoConservacion the estadoConservacion to set
	 */
	public void setEstadoConservacion(String estadoConservacion) {
		this.estadoConservacion = estadoConservacion;
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


}