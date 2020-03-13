package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: CatalogoProductoBean.java
 * @description: Clase CatalogoProductoBean.
 * @date: 23/08/2017
 * @author: ARCHY.
 */
public class CatalogoProductoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer idProducto;
	private String nombreProducto;
	private String codigoSiga;
	private Integer idCategoria;
	private Integer idEnvase;
	private Integer idUnidadMedida;
	private Integer idFamProducto;
	private String observacion;
	private Integer idEjecutora;
	private String tipoBien;
	private String grupoBien;
	private String claseBien;
	private String familiaBien;
	private String itemBien;
	private String usuario;
	private String fecha;
	private String estado;
	private String codigoProducto;
	private BigDecimal dimLargo;
	private BigDecimal dimAncho;
	private BigDecimal dimAlto;
	private BigDecimal pesoNeto;
	private BigDecimal pesoBruto;
	private String nombreCategoria;
	private String nombreUnidadMedida;
	private String nombreEnvase;
	
	private Integer tipoEnvaseSec;
	private Integer unidadEnvaseSec;
	private String descripcionEnvaseSec;
	
	/**
	 * 
	 */
	public CatalogoProductoBean() {
		super();
	}
	
	/**
	 * @param idProducto
	 * @param idCategoria
	 */
	public CatalogoProductoBean(Integer idProducto, Integer idCategoria) {
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

	public String getCodigoSiga() {
		return codigoSiga;
	}

	public void setCodigoSiga(String codigoSiga) {
		this.codigoSiga = codigoSiga;
	}

	public Integer getIdFamProducto() {
		return idFamProducto;
	}

	public void setIdFamProducto(Integer idFamProducto) {
		this.idFamProducto = idFamProducto;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Integer getIdEjecutora() {
		return idEjecutora;
	}

	public void setIdEjecutora(Integer idEjecutora) {
		this.idEjecutora = idEjecutora;
	}

	public String getTipoBien() {
		return tipoBien;
	}

	public void setTipoBien(String tipoBien) {
		this.tipoBien = tipoBien;
	}

	public String getGrupoBien() {
		return grupoBien;
	}

	public void setGrupoBien(String grupoBien) {
		this.grupoBien = grupoBien;
	}

	public String getClaseBien() {
		return claseBien;
	}

	public void setClaseBien(String claseBien) {
		this.claseBien = claseBien;
	}

	public String getFamiliaBien() {
		return familiaBien;
	}

	public void setFamiliaBien(String familiaBien) {
		this.familiaBien = familiaBien;
	}

	public String getItemBien() {
		return itemBien;
	}

	public void setItemBien(String itemBien) {
		this.itemBien = itemBien;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getDimLargo() {
		return dimLargo;
	}

	public void setDimLargo(BigDecimal dimLargo) {
		this.dimLargo = dimLargo;
	}

	public BigDecimal getDimAncho() {
		return dimAncho;
	}

	public void setDimAncho(BigDecimal dimAncho) {
		this.dimAncho = dimAncho;
	}

	public BigDecimal getDimAlto() {
		return dimAlto;
	}

	public void setDimAlto(BigDecimal dimAlto) {
		this.dimAlto = dimAlto;
	}

	public BigDecimal getPesoNeto() {
		return pesoNeto;
	}

	public void setPesoNeto(BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
	}

	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getNombreUnidadMedida() {
		return nombreUnidadMedida;
	}

	public void setNombreUnidadMedida(String nombreUnidadMedida) {
		this.nombreUnidadMedida = nombreUnidadMedida;
	}

	public String getNombreEnvase() {
		return nombreEnvase;
	}

	public void setNombreEnvase(String nombreEnvase) {
		this.nombreEnvase = nombreEnvase;
	}

	/**
	 * @return the tipoEnvaseSec
	 */
	public Integer getTipoEnvaseSec() {
		return tipoEnvaseSec;
	}

	/**
	 * @param tipoEnvaseSec the tipoEnvaseSec to set
	 */
	public void setTipoEnvaseSec(Integer tipoEnvaseSec) {
		this.tipoEnvaseSec = tipoEnvaseSec;
	}

	/**
	 * @return the unidadEnvaseSec
	 */
	public Integer getUnidadEnvaseSec() {
		return unidadEnvaseSec;
	}

	/**
	 * @param unidadEnvaseSec the unidadEnvaseSec to set
	 */
	public void setUnidadEnvaseSec(Integer unidadEnvaseSec) {
		this.unidadEnvaseSec = unidadEnvaseSec;
	}

	/**
	 * @return the descripcionEnvaseSec
	 */
	public String getDescripcionEnvaseSec() {
		return descripcionEnvaseSec;
	}

	/**
	 * @param descripcionEnvaseSec the descripcionEnvaseSec to set
	 */
	public void setDescripcionEnvaseSec(String descripcionEnvaseSec) {
		this.descripcionEnvaseSec = descripcionEnvaseSec;
	}

}