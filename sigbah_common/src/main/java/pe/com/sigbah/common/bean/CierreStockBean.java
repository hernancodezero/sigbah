package pe.com.sigbah.common.bean;

/**
 * @className: CierreStockBean.java
 * @description: 
 * @date: 23 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class CierreStockBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private String codigoAnio;
	private String codigoMes;
	private String nombreMes;
	private Integer idAlmacen;
	private String codigoAlmacen;
	private String nombreAlmacen;
	private Integer idResponsable;
	private String responsable;
	private String flagCierreAlmacen;
	private Integer idEstado;
	private String nombreEstado;
	private String observacion;
	private String fecha;
	private Integer idDdi;

	/**
	 * 
	 */
	public CierreStockBean() {
		super();
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
	 * @return the flagCierreAlmacen
	 */
	public String getFlagCierreAlmacen() {
		return flagCierreAlmacen;
	}

	/**
	 * @param flagCierreAlmacen the flagCierreAlmacen to set
	 */
	public void setFlagCierreAlmacen(String flagCierreAlmacen) {
		this.flagCierreAlmacen = flagCierreAlmacen;
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
	
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
}
