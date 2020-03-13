package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: CartillaInventarioBean.java
 * @description: 
 * @date: 23 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class CartillaInventarioBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idCartilla;
	private String codigoCartilla;
	private String codigoAnio;
	private String codigoMes;
	private Integer idAlmacen;
	private String codigoAlmacen;
	private String nombreAlmacen;
	private Integer idDdi;
	private String codigoDdi;
	private String nombreDdi;	
	private String nroCartilla;
	private String fechaCartilla;
	private Integer idResponsable;
	private String responsable;	
	private BigDecimal itemInventariados;	
	private Integer idEstado;
	private String nombreEstado;
	private String tipoOrigen;
	private String observacion;

	
	/**
	 * 
	 */
	public CartillaInventarioBean() {
		super();
	}
	
	/**
	 * @param idCartilla
	 */
	public CartillaInventarioBean(Integer idCartilla) {
		super();
		this.idCartilla = idCartilla;
	}

	/**
	 * @return the idCartilla
	 */
	public Integer getIdCartilla() {
		return idCartilla;
	}

	/**
	 * @param idCartilla the idCartilla to set
	 */
	public void setIdCartilla(Integer idCartilla) {
		this.idCartilla = idCartilla;
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
	 * @return the nroCartilla
	 */
	public String getNroCartilla() {
		return nroCartilla;
	}

	/**
	 * @param nroCartilla the nroCartilla to set
	 */
	public void setNroCartilla(String nroCartilla) {
		this.nroCartilla = nroCartilla;
	}

	/**
	 * @return the fechaCartilla
	 */
	public String getFechaCartilla() {
		return fechaCartilla;
	}

	/**
	 * @param fechaCartilla the fechaCartilla to set
	 */
	public void setFechaCartilla(String fechaCartilla) {
		this.fechaCartilla = fechaCartilla;
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
	 * @return the itemInventariados
	 */
	public BigDecimal getItemInventariados() {
		return itemInventariados;
	}

	/**
	 * @param itemInventariados the itemInventariados to set
	 */
	public void setItemInventariados(BigDecimal itemInventariados) {
		this.itemInventariados = itemInventariados;
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
	 * @return the codigoCartilla
	 */
	public String getCodigoCartilla() {
		return codigoCartilla;
	}

	/**
	 * @param codigoCartilla the codigoCartilla to set
	 */
	public void setCodigoCartilla(String codigoCartilla) {
		this.codigoCartilla = codigoCartilla;
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

}
