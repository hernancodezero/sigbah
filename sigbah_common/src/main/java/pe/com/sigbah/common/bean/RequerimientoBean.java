package pe.com.sigbah.common.bean;

/**
 * @className: RequerimientoBean.java
 * @description: 
 * @date: 19 jul. 2017
 * @author: whr.
 */
public class RequerimientoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer idRequerimiento;
	private String codAnio;
	private String codMes;
	private String nomMes;
	private Integer fkIdeDdi;
	private String nombreDdi;
	private Integer fkIdeFenomeno;
	private String descFenomeno;
	private String codRequerimiento;
	private String nomRequerimiento;
	private Integer fkIdeRegion;
	private String nomRegion;
	private Integer idDdi;
	private String codDdi;
	private Integer idFenomeno;
	private String fechaRequerimiento;
	private String numRequerimiento;
	private String flgSinpad;
	private String observacion;
	
	private String codDpto;
	private Integer idProvincia;
	private String codProvincia;
	private String control;
	
	private String nombreSistema;
	private String estado;
	private Integer idEstado;
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
	 * 
	 */
	public RequerimientoBean() {
		super();
	}
	/**
	 * @param idRequerimiento
	 */
	public RequerimientoBean(Integer idRequerimiento) {
		super();
		this.idRequerimiento = idRequerimiento;
	}
	/**
	 * @return the idRequerimiento
	 */
	public Integer getIdRequerimiento() {
		return idRequerimiento;
	}
	/**
	 * @param idRequerimiento the idRequerimiento to set
	 */
	public void setIdRequerimiento(Integer idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
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
	 * @return the codMes
	 */
	public String getCodMes() {
		return codMes;
	}
	/**
	 * @param codMes the codMes to set
	 */
	public void setCodMes(String codMes) {
		this.codMes = codMes;
	}
	/**
	 * @return the fkIdeDdi
	 */
	public Integer getFkIdeDdi() {
		return fkIdeDdi;
	}
	/**
	 * @param fkIdeDdi the fkIdeDdi to set
	 */
	public void setFkIdeDdi(Integer fkIdeDdi) {
		this.fkIdeDdi = fkIdeDdi;
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
	 * @return the fkIdeFenomeno
	 */
	public Integer getFkIdeFenomeno() {
		return fkIdeFenomeno;
	}
	/**
	 * @param fkIdeFenomeno the fkIdeFenomeno to set
	 */
	public void setFkIdeFenomeno(Integer fkIdeFenomeno) {
		this.fkIdeFenomeno = fkIdeFenomeno;
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
	 * @return the codRequerimiento
	 */
	public String getCodRequerimiento() {
		return codRequerimiento;
	}
	/**
	 * @param codRequerimiento the codRequerimiento to set
	 */
	public void setCodRequerimiento(String codRequerimiento) {
		this.codRequerimiento = codRequerimiento;
	}
	/**
	 * @return the nomRequerimiento
	 */
	public String getNomRequerimiento() {
		return nomRequerimiento;
	}
	/**
	 * @param nomRequerimiento the nomRequerimiento to set
	 */
	public void setNomRequerimiento(String nomRequerimiento) {
		this.nomRequerimiento = nomRequerimiento;
	}
	/**
	 * @return the fkIdeRegion
	 */
	public Integer getFkIdeRegion() {
		return fkIdeRegion;
	}
	/**
	 * @param fkIdeRegion the fkIdeRegion to set
	 */
	public void setFkIdeRegion(Integer fkIdeRegion) {
		this.fkIdeRegion = fkIdeRegion;
	}
	/**
	 * @return the nomRegion
	 */
	public String getNomRegion() {
		return nomRegion;
	}
	/**
	 * @param nomRegion the nomRegion to set
	 */
	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
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
	 * @return the codDdi
	 */
	public String getCodDdi() {
		return codDdi;
	}
	/**
	 * @param codDdi the codDdi to set
	 */
	public void setCodDdi(String codDdi) {
		this.codDdi = codDdi;
	}
	
	/**
	 * @return the idFenomeno
	 */
	public Integer getIdFenomeno() {
		return idFenomeno;
	}
	/**
	 * @param idFenomeno the idFenomeno to set
	 */
	public void setIdFenomeno(Integer idFenomeno) {
		this.idFenomeno = idFenomeno;
	}
	/**
	 * @return the fechaRequerimiento
	 */
	public String getFechaRequerimiento() {
		return fechaRequerimiento;
	}
	/**
	 * @param fechaRequerimiento the fechaRequerimiento to set
	 */
	public void setFechaRequerimiento(String fechaRequerimiento) {
		this.fechaRequerimiento = fechaRequerimiento;
	}
	/**
	 * @return the numRequerimiento
	 */
	public String getNumRequerimiento() {
		return numRequerimiento;
	}
	/**
	 * @param numRequerimiento the numRequerimiento to set
	 */
	public void setNumRequerimiento(String numRequerimiento) {
		this.numRequerimiento = numRequerimiento;
	}
	/**
	 * @return the flgSinpad
	 */
	public String getFlgSinpad() {
		return flgSinpad;
	}
	/**
	 * @param flgSinpad the flgSinpad to set
	 */
	public void setFlgSinpad(String flgSinpad) {
		this.flgSinpad = flgSinpad;
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
	 * @return the codDpto
	 */
	public String getCodDpto() {
		return codDpto;
	}
	/**
	 * @param codDpto the codDpto to set
	 */
	public void setCodDpto(String codDpto) {
		this.codDpto = codDpto;
	}
	/**
	 * @return the idProvincia
	 */
	public Integer getIdProvincia() {
		return idProvincia;
	}
	/**
	 * @param idProvincia the idProvincia to set
	 */
	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
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
	 * @return the nomMes
	 */
	public String getNomMes() {
		return nomMes;
	}
	/**
	 * @param nomMes the nomMes to set
	 */
	public void setNomMes(String nomMes) {
		this.nomMes = nomMes;
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
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
