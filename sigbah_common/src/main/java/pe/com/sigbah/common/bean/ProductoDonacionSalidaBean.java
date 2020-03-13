package pe.com.sigbah.common.bean;

/**
 * @className: ProductoDonacionSalidaBean.java
 * @description: Clase ProductoDonacionSalidaBean.
 * @date: 31/07/2017 
 * @author: ARCHY.
 */
public class ProductoDonacionSalidaBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	
	private Integer idSalida;
	private Integer idSalidaDet;
	private Integer idProducto;
	private String nombreProducto;
	private String unidadMedida;
	private Double cantidad;
	private Double precioUnitario;
	private Double importeTotal;
	private Double pesoBrutoTotal;
	private String fecVencimiento;
	private Integer idDonacion;
	private String codDonacion;
	private Integer idProgramacion;
	private Integer idCategoria;
	
	private Double pesoNetoUnitario;
	private Double pesoBrutoUnitario;
	private String nombreEnvase;
	private Integer nroLote;
	private String anio;
	private String mes;
	
	private Integer idDdi;
	private String codDdi;
	private Integer idAlmacen;
	private String codAlmacen;
	private Double stock;

	/**
	 * 
	 */
	public ProductoDonacionSalidaBean() {
		super();
	}

	public Integer getIdSalida() {
		return idSalida;
	}

	public void setIdSalida(Integer idSalida) {
		this.idSalida = idSalida;
	}

	
	//////////////////
	
	public Integer getIdSalidaDet() {
		return idSalidaDet;
	}

	public void setIdSalidaDet(Integer idSalidaDet) {
		this.idSalidaDet = idSalidaDet;
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
	
	public Double getPesoBrutoTotal() {
		return pesoBrutoTotal;
	}

	public void setPesoBrutoTotal(Double pesoBrutoTotal) {
		this.pesoBrutoTotal = pesoBrutoTotal;
	}
	
	public String getCodDonacion() {
		return codDonacion;
	}

	public void setCodDonacion(String codDonacion) {
		this.codDonacion = codDonacion;
	}
	
	public Integer getIdProgramacion() {
		return idProgramacion;
	}

	public void setIdProgramacion(Integer idProgramacion) {
		this.idProgramacion = idProgramacion;
	}
	
	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public Double getPesoNetoUnitario() {
		return pesoNetoUnitario;
	}

	public void setPesoNetoUnitario(Double pesoNetoUnitario) {
		this.pesoNetoUnitario = pesoNetoUnitario;
	}
	
	public Double getPesoBrutoUnitario() {
		return pesoBrutoUnitario;
	}

	public void setPesoBrutoUnitario(Double pesoBrutoUnitario) {
		this.pesoBrutoUnitario = pesoBrutoUnitario;
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
	 * @return the nroLote
	 */
	public Integer getNroLote() {
		return nroLote;
	}

	/**
	 * @param nroLote the nroLote to set
	 */
	public void setNroLote(Integer nroLote) {
		this.nroLote = nroLote;
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

	/**
	 * @return the stock
	 */
	public Double getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Double stock) {
		this.stock = stock;
	}


}