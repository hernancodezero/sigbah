package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: EstadoProgramacionBean.java
 * @description: 
 * @date: 23 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class EstadoProgramacionBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private BigDecimal item;
	private Integer idProgramacion;	
	private String nroProgramacion;
	private String fechaEstado;		
	private Integer idEstado;
	private String nombreEstado;
	private String usuario;
	private String observacion;
	

	/**
	 * 
	 */
	public EstadoProgramacionBean() {
		super();
	}
	
	/**
	 * @param idProgramacion
	 */
	public EstadoProgramacionBean(Integer idProgramacion) {
		super();
		this.idProgramacion = idProgramacion;
	}

	/**
	 * @return the idProgramacion
	 */
	public Integer getIdProgramacion() {
		return idProgramacion;
	}

	/**
	 * @param idProgramacion the idProgramacion to set
	 */
	public void setIdProgramacion(Integer idProgramacion) {
		this.idProgramacion = idProgramacion;
	}

	/**
	 * @return the item
	 */
	public BigDecimal getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(BigDecimal item) {
		this.item = item;
	}

	/**
	 * @return the nroProgramacion
	 */
	public String getNroProgramacion() {
		return nroProgramacion;
	}

	/**
	 * @param nroProgramacion the nroProgramacion to set
	 */
	public void setNroProgramacion(String nroProgramacion) {
		this.nroProgramacion = nroProgramacion;
	}

	/**
	 * @return the fechaEstado
	 */
	public String getFechaEstado() {
		return fechaEstado;
	}

	/**
	 * @param fechaEstado the fechaEstado to set
	 */
	public void setFechaEstado(String fechaEstado) {
		this.fechaEstado = fechaEstado;
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
