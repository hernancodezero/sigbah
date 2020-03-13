package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ProductoBean.java
 * @description: Clase ProductoBean.
 * @date: 18/02/2015 22:26:30
 * @author: Junior Huaman Flores.
 */
public class ProductoRacionBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer idDetaRacion;
	private Integer idRacion;
	private Integer fkIdProducto;
	private BigDecimal pesoUnitarioPres;
	private BigDecimal cantRacionKg;
	private String codAnio;
	private String nombProducto;
	private String control;
	
	
	/**
	 * 
	 */
	public ProductoRacionBean() {
		super();
	}



	/**
	 * @param fkIdProducto
	 */
	public ProductoRacionBean(Integer fkIdProducto) {
		super();
		this.fkIdProducto = fkIdProducto;
	}



	/**
	 * @return the idDetaRacion
	 */
	public Integer getIdDetaRacion() {
		return idDetaRacion;
	}



	/**
	 * @param idDetaRacion the idDetaRacion to set
	 */
	public void setIdDetaRacion(Integer idDetaRacion) {
		this.idDetaRacion = idDetaRacion;
	}



	/**
	 * @return the idRacion
	 */
	public Integer getIdRacion() {
		return idRacion;
	}



	/**
	 * @param idRacion the idRacion to set
	 */
	public void setIdRacion(Integer idRacion) {
		this.idRacion = idRacion;
	}



	/**
	 * @return the fkIdProducto
	 */
	public Integer getFkIdProducto() {
		return fkIdProducto;
	}



	/**
	 * @param fkIdProducto the fkIdProducto to set
	 */
	public void setFkIdProducto(Integer fkIdProducto) {
		this.fkIdProducto = fkIdProducto;
	}



	/**
	 * @return the pesoUnitarioPres
	 */
	public BigDecimal getPesoUnitarioPres() {
		return pesoUnitarioPres;
	}



	/**
	 * @param pesoUnitarioPres the pesoUnitarioPres to set
	 */
	public void setPesoUnitarioPres(BigDecimal pesoUnitarioPres) {
		this.pesoUnitarioPres = pesoUnitarioPres;
	}



	/**
	 * @return the cantRacionKg
	 */
	public BigDecimal getCantRacionKg() {
		return cantRacionKg;
	}



	/**
	 * @param cantRacionKg the cantRacionKg to set
	 */
	public void setCantRacionKg(BigDecimal cantRacionKg) {
		this.cantRacionKg = cantRacionKg;
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
	 * @return the nombProducto
	 */
	public String getNombProducto() {
		return nombProducto;
	}



	/**
	 * @param nombProducto the nombProducto to set
	 */
	public void setNombProducto(String nombProducto) {
		this.nombProducto = nombProducto;
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