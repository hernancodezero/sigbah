package pe.com.sigbah.common.bean;


/**
 * @className: EmergenciaBean.java
 * @description: 
 * @date: 14 jul. 2017
 * @author: whr.
 */
public class CabeceraEmergenciaBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer idEmergencia;
	private String nombreEmergencia;
	private String fechaEmergencia;
	private String descFenomeno;
	private String ubigeo;
	
	
	/**
	 * 
	 */
	public CabeceraEmergenciaBean() {
		super();
	}
	/**
	 * @param idEmergencia
	 */
	public CabeceraEmergenciaBean(Integer idEmergencia) {
		super();
		this.idEmergencia = idEmergencia;
	}
	/**
	 * @return the idEmergencia
	 */
	public Integer getIdEmergencia() {
		return idEmergencia;
	}
	/**
	 * @param idEmergencia the idEmergencia to set
	 */
	public void setIdEmergencia(Integer idEmergencia) {
		this.idEmergencia = idEmergencia;
	}
	/**
	 * @return the nombreEmergencia
	 */
	public String getNombreEmergencia() {
		return nombreEmergencia;
	}
	/**
	 * @param nombreEmergencia the nombreEmergencia to set
	 */
	public void setNombreEmergencia(String nombreEmergencia) {
		this.nombreEmergencia = nombreEmergencia;
	}
	/**
	 * @return the fechaEmergencia
	 */
	public String getFechaEmergencia() {
		return fechaEmergencia;
	}
	/**
	 * @param fechaEmergencia the fechaEmergencia to set
	 */
	public void setFechaEmergencia(String fechaEmergencia) {
		this.fechaEmergencia = fechaEmergencia;
	}
	
	/**
	 * @return the descFenomeno
	 */
	public String getDescFenomeno() {
		return descFenomeno;
	}
	/**
	 * @param descFenomeno the descFenomeno to set
	 */
	public void setDescFenomeno(String descFenomeno) {
		this.descFenomeno = descFenomeno;
	}
	/**
	 * @return the ubigeo
	 */
	public String getUbigeo() {
		return ubigeo;
	}
	/**
	 * @param ubigeo the ubigeo to set
	 */
	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}
	
	
	
}
