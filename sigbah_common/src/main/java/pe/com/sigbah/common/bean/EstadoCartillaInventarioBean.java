package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: EstadoCartillaInventarioBean.java
 * @description: 
 * @date: 23 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class EstadoCartillaInventarioBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private BigDecimal item;
	private Integer idCartilla;	
	private String nroCartilla;
	private String fechaEstado;		
	private Integer idEstado;
	private String nombreEstado;
	private String usuario;
	private String observacion;
	

	/**
	 * 
	 */
	public EstadoCartillaInventarioBean() {
		super();
	}
	
	/**
	 * @param idCartilla
	 */
	public EstadoCartillaInventarioBean(Integer idCartilla) {
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
