package pe.com.sigbah.common.bean;



/**
 * @className: PedidoCompraBean.java
 * @description: 
 * @date: 30 jul. 2017
 * @author: whr.
 */
public class DeeBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer idDee;
	private String codAnio;
	private String codMes;
	private String nomMes;
	private String fechaIni;
	private String fechaFin;
	private String numDias;
	private String numDee;
	private String nomDee;
	private Integer idEstado;
	private String nomEstado;
	private String motivo;
	private String observacion;
	private String flgProrroga;
	private String nombreArchivo;
	private String control;
	private Integer plazo;
	private String codigoArchivoAlfresco;
	private String tipoFenomeno;
	
	private String codDistrito;
	/**
	 * 
	 */
	public DeeBean() {
		super();
	}
	
	

	/**
	 * @param idDee
	 */
	public DeeBean(Integer idDee) {
		super();
		this.idDee = idDee;
	}



	/**
	 * @return the idDee
	 */
	public Integer getIdDee() {
		return idDee;
	}

	/**
	 * @param idDee the idDee to set
	 */
	public void setIdDee(Integer idDee) {
		this.idDee = idDee;
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
	 * @return the fechaIni
	 */
	public String getFechaIni() {
		return fechaIni;
	}

	/**
	 * @param fechaIni the fechaIni to set
	 */
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the numDias
	 */
	public String getNumDias() {
		return numDias;
	}

	/**
	 * @param numDias the numDias to set
	 */
	public void setNumDias(String numDias) {
		this.numDias = numDias;
	}

	/**
	 * @return the nomDee
	 */
	public String getNomDee() {
		return nomDee;
	}

	/**
	 * @param nomDee the nomDee to set
	 */
	public void setNomDee(String nomDee) {
		this.nomDee = nomDee;
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
	 * @return the nomEstado
	 */
	public String getNomEstado() {
		return nomEstado;
	}

	/**
	 * @param nomEstado the nomEstado to set
	 */
	public void setNomEstado(String nomEstado) {
		this.nomEstado = nomEstado;
	}



	/**
	 * @return the numDee
	 */
	public String getNumDee() {
		return numDee;
	}



	/**
	 * @param numDee the numDee to set
	 */
	public void setNumDee(String numDee) {
		this.numDee = numDee;
	}



	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}



	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
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
	 * @return the flgProrroga
	 */
	public String getFlgProrroga() {
		return flgProrroga;
	}



	/**
	 * @param flgProrroga the flgProrroga to set
	 */
	public void setFlgProrroga(String flgProrroga) {
		this.flgProrroga = flgProrroga;
	}



	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}



	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
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
	 * @return the plazo
	 */
	public Integer getPlazo() {
		return plazo;
	}



	/**
	 * @param plazo the plazo to set
	 */
	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}



	/**
	 * @return the codigoArchivoAlfresco
	 */
	public String getCodigoArchivoAlfresco() {
		return codigoArchivoAlfresco;
	}



	/**
	 * @param codigoArchivoAlfresco the codigoArchivoAlfresco to set
	 */
	public void setCodigoArchivoAlfresco(String codigoArchivoAlfresco) {
		this.codigoArchivoAlfresco = codigoArchivoAlfresco;
	}



	/**
	 * @return the tipoFenomeno
	 */
	public String getTipoFenomeno() {
		return tipoFenomeno;
	}



	/**
	 * @param tipoFenomeno the tipoFenomeno to set
	 */
	public void setTipoFenomeno(String tipoFenomeno) {
		this.tipoFenomeno = tipoFenomeno;
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

	
	
}
