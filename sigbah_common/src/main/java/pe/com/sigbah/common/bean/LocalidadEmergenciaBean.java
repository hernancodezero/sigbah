package pe.com.sigbah.common.bean;


/**
 * @className: EmergenciaBean.java
 * @description: 
 * @date: 14 jul. 2017
 * @author: whr.
 */
public class LocalidadEmergenciaBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer idEmergencia;
	private String anio;
	private String codDepartamento;
	private String desDepartamento;
	private String codProvincia;
	private String desProvincia;	
	private String codDistrito;
	private String desDistrito;
	private String codCentroPoblado;
	private String desCentroPoblado;
	private Integer famAfectado;
	private Integer famDamnificado;
	private Integer totalFam;
	private Integer persoAfectado;
	private Integer persoDamnificado;
	private Integer totalPerso;
	
	
	
	
	/**
	 * 
	 */
	public LocalidadEmergenciaBean() {
		super();
	}
	
	
	/**
	 * @param idEmergencia
	 */
	public LocalidadEmergenciaBean(Integer idEmergencia) {
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
	 * @return the codDepartamento
	 */
	public String getCodDepartamento() {
		return codDepartamento;
	}
	/**
	 * @param codDepartamento the codDepartamento to set
	 */
	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
	/**
	 * @return the desDepartamento
	 */
	public String getDesDepartamento() {
		return desDepartamento;
	}
	/**
	 * @param desDepartamento the desDepartamento to set
	 */
	public void setDesDepartamento(String desDepartamento) {
		this.desDepartamento = desDepartamento;
	}
	/**
	 * @return the codProvincia
	 */
	public String getCodProvincia() {
		return codProvincia;
	}
	/**
	 * @param codProvincia the codProvincia to set
	 */
	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}
	/**
	 * @return the desProvincia
	 */
	public String getDesProvincia() {
		return desProvincia;
	}
	/**
	 * @param desProvincia the desProvincia to set
	 */
	public void setDesProvincia(String desProvincia) {
		this.desProvincia = desProvincia;
	}
	/**
	 * @return the codDistrito
	 */
	public String getCodDistrito() {
		return codDistrito;
	}
	/**
	 * @param codDistrito the codDistrito to set
	 */
	public void setCodDistrito(String codDistrito) {
		this.codDistrito = codDistrito;
	}
	/**
	 * @return the desDistrito
	 */
	public String getDesDistrito() {
		return desDistrito;
	}
	/**
	 * @param desDistrito the desDistrito to set
	 */
	public void setDesDistrito(String desDistrito) {
		this.desDistrito = desDistrito;
	}
	/**
	 * @return the famAfectado
	 */
	public Integer getFamAfectado() {
		return famAfectado;
	}
	/**
	 * @param famAfectado the famAfectado to set
	 */
	public void setFamAfectado(Integer famAfectado) {
		this.famAfectado = famAfectado;
	}
	/**
	 * @return the famDamnificado
	 */
	public Integer getFamDamnificado() {
		return famDamnificado;
	}
	/**
	 * @param famDamnificado the famDamnificado to set
	 */
	public void setFamDamnificado(Integer famDamnificado) {
		this.famDamnificado = famDamnificado;
	}
	/**
	 * @return the persoAfectado
	 */
	public Integer getPersoAfectado() {
		return persoAfectado;
	}
	/**
	 * @param persoAfectado the persoAfectado to set
	 */
	public void setPersoAfectado(Integer persoAfectado) {
		this.persoAfectado = persoAfectado;
	}
	/**
	 * @return the persoDamnificado
	 */
	public Integer getPersoDamnificado() {
		return persoDamnificado;
	}
	/**
	 * @param persoDamnificado the persoDamnificado to set
	 */
	public void setPersoDamnificado(Integer persoDamnificado) {
		this.persoDamnificado = persoDamnificado;
	}


	/**
	 * @return the desCentroPoblado
	 */
	public String getDesCentroPoblado() {
		return desCentroPoblado;
	}


	/**
	 * @param desCentroPoblado the desCentroPoblado to set
	 */
	public void setDesCentroPoblado(String desCentroPoblado) {
		this.desCentroPoblado = desCentroPoblado;
	}


	/**
	 * @return the codCentroPoblado
	 */
	public String getCodCentroPoblado() {
		return codCentroPoblado;
	}


	/**
	 * @param codCentroPoblado the codCentroPoblado to set
	 */
	public void setCodCentroPoblado(String codCentroPoblado) {
		this.codCentroPoblado = codCentroPoblado;
	}


	/**
	 * @return the totalFam
	 */
	public Integer getTotalFam() {
		return totalFam;
	}


	/**
	 * @param totalFam the totalFam to set
	 */
	public void setTotalFam(Integer totalFam) {
		this.totalFam = totalFam;
	}


	/**
	 * @return the totalPerso
	 */
	public Integer getTotalPerso() {
		return totalPerso;
	}


	/**
	 * @param totalPerso the totalPerso to set
	 */
	public void setTotalPerso(Integer totalPerso) {
		this.totalPerso = totalPerso;
	}
	
	
}
