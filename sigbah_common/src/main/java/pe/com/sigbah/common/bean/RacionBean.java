package pe.com.sigbah.common.bean;


/**
 * @className: EmergenciaBean.java
 * @description: 
 * @date: 14 jul. 2017
 * @author: whr.
 */
public class RacionBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer idRacionOpe;
	private String codAnio;
	private String nombreMesRacion;
	private String codMesRacion;
	private String codRacion;
	private String fechaRacion;
	private String tipoRacion;
	private String nombreRacion;
	private String diasAtencion;
	private Integer idDdi;
	private String control;
	/**
	 * 
	 */
	public RacionBean() {
		super();
	}

	/**
	 * @param idRacionOpe
	 */
	public RacionBean(Integer idRacionOpe) {
		super();
		this.idRacionOpe = idRacionOpe;
	}

	/**
	 * @return the idRacionOpe
	 */
	public Integer getIdRacionOpe() {
		return idRacionOpe;
	}

	/**
	 * @param idRacionOpe the idRacionOpe to set
	 */
	public void setIdRacionOpe(Integer idRacionOpe) {
		this.idRacionOpe = idRacionOpe;
	}

	/**
	 * @return the codAnio
	 */
	public String getCodAnio() {
		return codAnio;
	}

	/**
	 * @param codAnio the codAnio to set
	 */
	public void setCodAnio(String codAnio) {
		this.codAnio = codAnio;
	}

	/**
	 * @return the nombreMesRacion
	 */
	public String getNombreMesRacion() {
		return nombreMesRacion;
	}

	/**
	 * @param nombreMesRacion the nombreMesRacion to set
	 */
	public void setNombreMesRacion(String nombreMesRacion) {
		this.nombreMesRacion = nombreMesRacion;
	}

	/**
	 * @return the codRacion
	 */
	public String getCodRacion() {
		return codRacion;
	}

	/**
	 * @param codRacion the codRacion to set
	 */
	public void setCodRacion(String codRacion) {
		this.codRacion = codRacion;
	}

	/**
	 * @return the fechaRacion
	 */
	public String getFechaRacion() {
		return fechaRacion;
	}

	/**
	 * @param fechaRacion the fechaRacion to set
	 */
	public void setFechaRacion(String fechaRacion) {
		this.fechaRacion = fechaRacion;
	}

	/**
	 * @return the tipoRacion
	 */
	public String getTipoRacion() {
		return tipoRacion;
	}

	/**
	 * @param tipoRacion the tipoRacion to set
	 */
	public void setTipoRacion(String tipoRacion) {
		this.tipoRacion = tipoRacion;
	}

	/**
	 * @return the nombreRacion
	 */
	public String getNombreRacion() {
		return nombreRacion;
	}

	/**
	 * @param nombreRacion the nombreRacion to set
	 */
	public void setNombreRacion(String nombreRacion) {
		this.nombreRacion = nombreRacion;
	}

	/**
	 * @return the diasAtencion
	 */
	public String getDiasAtencion() {
		return diasAtencion;
	}

	/**
	 * @param diasAtencion the diasAtencion to set
	 */
	public void setDiasAtencion(String diasAtencion) {
		this.diasAtencion = diasAtencion;
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
	 * @return the codMesRacion
	 */
	public String getCodMesRacion() {
		return codMesRacion;
	}

	/**
	 * @param codMesRacion the codMesRacion to set
	 */
	public void setCodMesRacion(String codMesRacion) {
		this.codMesRacion = codMesRacion;
	}

	/**
	 * @return the control
	 */
	public String getControl() {
		return control;
	}

	/**
	 * @param control the control to set
	 */
	public void setControl(String control) {
		this.control = control;
	}
	
	
	
	
}
