package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ProductoControlCalidadBean.java
 * @description: 
 * @date: 25 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProductoControlCalidadBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idDetalleControlCalidad;
	private Integer idControlCalidad;
	private Integer idCategoria;
	private Integer idProducto;
	private String nombreProducto;
	private String nombreUnidad;
	private BigDecimal cantidadLote;
	private BigDecimal cantidadMuestra;
	private String primario;
	private String valorPrimario;
	private String secundario;
	private String valorSecundario;
	private String parOlor;
	private String valorOlor;
	private String parColor;
	private String valorColor;
	private String parTextura;
	private String valorTextura;
	private String parSabor;
	private String valorSabor;
	private String flagConforProducto;
	private String valorConforProducto;
	private String flagEspecTecnicas;
	private String valorEspecTecnicas;
	private String flagTipoProducto;
	private String fechaVencimiento;
	private String arrIdDetalleControlCalidad;
	
	
	/**
	 * 
	 */
	public ProductoControlCalidadBean() {
		super();
	}

	/**
	 * @param idDetalleControlCalidad
	 */
	public ProductoControlCalidadBean(Integer idDetalleControlCalidad) {
		super();
		this.idDetalleControlCalidad = idDetalleControlCalidad;
	}
	
	/**
	 * @return the idDetalleControlCalidad
	 */
	public Integer getIdDetalleControlCalidad() {
		return idDetalleControlCalidad;
	}
	/**
	 * @param idDetalleControlCalidad the idDetalleControlCalidad to set
	 */
	public void setIdDetalleControlCalidad(Integer idDetalleControlCalidad) {
		this.idDetalleControlCalidad = idDetalleControlCalidad;
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
	 * @return the cantidadLote
	 */
	public BigDecimal getCantidadLote() {
		return cantidadLote;
	}
	/**
	 * @param cantidadLote the cantidadLote to set
	 */
	public void setCantidadLote(BigDecimal cantidadLote) {
		this.cantidadLote = cantidadLote;
	}
	/**
	 * @return the cantidadMuestra
	 */
	public BigDecimal getCantidadMuestra() {
		return cantidadMuestra;
	}
	/**
	 * @param cantidadMuestra the cantidadMuestra to set
	 */
	public void setCantidadMuestra(BigDecimal cantidadMuestra) {
		this.cantidadMuestra = cantidadMuestra;
	}
	/**
	 * @return the primario
	 */
	public String getPrimario() {
		return primario;
	}
	/**
	 * @param primario the primario to set
	 */
	public void setPrimario(String primario) {
		this.primario = primario;
	}
	/**
	 * @return the valorPrimario
	 */
	public String getValorPrimario() {
		return valorPrimario;
	}
	/**
	 * @param valorPrimario the valorPrimario to set
	 */
	public void setValorPrimario(String valorPrimario) {
		this.valorPrimario = valorPrimario;
	}
	/**
	 * @return the secundario
	 */
	public String getSecundario() {
		return secundario;
	}
	/**
	 * @param secundario the secundario to set
	 */
	public void setSecundario(String secundario) {
		this.secundario = secundario;
	}
	/**
	 * @return the valorSecundario
	 */
	public String getValorSecundario() {
		return valorSecundario;
	}
	/**
	 * @param valorSecundario the valorSecundario to set
	 */
	public void setValorSecundario(String valorSecundario) {
		this.valorSecundario = valorSecundario;
	}
	/**
	 * @return the parOlor
	 */
	public String getParOlor() {
		return parOlor;
	}
	/**
	 * @param parOlor the parOlor to set
	 */
	public void setParOlor(String parOlor) {
		this.parOlor = parOlor;
	}
	/**
	 * @return the valorOlor
	 */
	public String getValorOlor() {
		return valorOlor;
	}
	/**
	 * @param valorOlor the valorOlor to set
	 */
	public void setValorOlor(String valorOlor) {
		this.valorOlor = valorOlor;
	}
	/**
	 * @return the parColor
	 */
	public String getParColor() {
		return parColor;
	}
	/**
	 * @param parColor the parColor to set
	 */
	public void setParColor(String parColor) {
		this.parColor = parColor;
	}
	/**
	 * @return the valorColor
	 */
	public String getValorColor() {
		return valorColor;
	}
	/**
	 * @param valorColor the valorColor to set
	 */
	public void setValorColor(String valorColor) {
		this.valorColor = valorColor;
	}
	/**
	 * @return the parTextura
	 */
	public String getParTextura() {
		return parTextura;
	}
	/**
	 * @param parTextura the parTextura to set
	 */
	public void setParTextura(String parTextura) {
		this.parTextura = parTextura;
	}
	/**
	 * @return the valorTextura
	 */
	public String getValorTextura() {
		return valorTextura;
	}
	/**
	 * @param valorTextura the valorTextura to set
	 */
	public void setValorTextura(String valorTextura) {
		this.valorTextura = valorTextura;
	}
	/**
	 * @return the parSabor
	 */
	public String getParSabor() {
		return parSabor;
	}
	/**
	 * @param parSabor the parSabor to set
	 */
	public void setParSabor(String parSabor) {
		this.parSabor = parSabor;
	}
	/**
	 * @return the valorSabor
	 */
	public String getValorSabor() {
		return valorSabor;
	}
	/**
	 * @param valorSabor the valorSabor to set
	 */
	public void setValorSabor(String valorSabor) {
		this.valorSabor = valorSabor;
	}
	/**
	 * @return the flagConforProducto
	 */
	public String getFlagConforProducto() {
		return flagConforProducto;
	}
	/**
	 * @param flagConforProducto the flagConforProducto to set
	 */
	public void setFlagConforProducto(String flagConforProducto) {
		this.flagConforProducto = flagConforProducto;
	}
	/**
	 * @return the valorConforProducto
	 */
	public String getValorConforProducto() {
		return valorConforProducto;
	}
	/**
	 * @param valorConforProducto the valorConforProducto to set
	 */
	public void setValorConforProducto(String valorConforProducto) {
		this.valorConforProducto = valorConforProducto;
	}
	/**
	 * @return the flagEspecTecnicas
	 */
	public String getFlagEspecTecnicas() {
		return flagEspecTecnicas;
	}
	/**
	 * @param flagEspecTecnicas the flagEspecTecnicas to set
	 */
	public void setFlagEspecTecnicas(String flagEspecTecnicas) {
		this.flagEspecTecnicas = flagEspecTecnicas;
	}
	/**
	 * @return the valorEspecTecnicas
	 */
	public String getValorEspecTecnicas() {
		return valorEspecTecnicas;
	}
	/**
	 * @param valorEspecTecnicas the valorEspecTecnicas to set
	 */
	public void setValorEspecTecnicas(String valorEspecTecnicas) {
		this.valorEspecTecnicas = valorEspecTecnicas;
	}
	/**
	 * @return the flagTipoProducto
	 */
	public String getFlagTipoProducto() {
		return flagTipoProducto;
	}
	/**
	 * @param flagTipoProducto the flagTipoProducto to set
	 */
	public void setFlagTipoProducto(String flagTipoProducto) {
		this.flagTipoProducto = flagTipoProducto;
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
	 * @return the arrIdDetalleControlCalidad
	 */
	public String getArrIdDetalleControlCalidad() {
		return arrIdDetalleControlCalidad;
	}
	/**
	 * @param arrIdDetalleControlCalidad the arrIdDetalleControlCalidad to set
	 */
	public void setArrIdDetalleControlCalidad(String arrIdDetalleControlCalidad) {
		this.arrIdDetalleControlCalidad = arrIdDetalleControlCalidad;
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

}
