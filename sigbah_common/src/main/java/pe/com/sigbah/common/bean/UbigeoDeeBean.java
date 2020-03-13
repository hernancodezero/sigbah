package pe.com.sigbah.common.bean;

import java.util.Date;

/**
 * @className: EntidadFinancieraBean.java
 * @description: 
 * @date: 16 de jun. de 2016
 * @author: Junior Huaman Flores.
 */
public class UbigeoDeeBean extends BaseOutputBean {
	
	private static final long serialVersionUID = 1L;
	private int idDeeDetalle;

	private String coddist;
	private String desdpto;
	private String desprov;
	private String desdist;

	
	/**
	 * 
	 */
	public UbigeoDeeBean() {
		super();
	}


	/**
	 * @param idDeeDetalle
	 */
	public UbigeoDeeBean(int idDeeDetalle) {
		super();
		this.idDeeDetalle = idDeeDetalle;
	}


	/**
	 * @return the idDeeDetalle
	 */
	public int getIdDeeDetalle() {
		return idDeeDetalle;
	}


	/**
	 * @param idDeeDetalle the idDeeDetalle to set
	 */
	public void setIdDeeDetalle(int idDeeDetalle) {
		this.idDeeDetalle = idDeeDetalle;
	}


	/**
	 * @return the coddist
	 */
	public String getCoddist() {
		return coddist;
	}


	/**
	 * @param coddist the coddist to set
	 */
	public void setCoddist(String coddist) {
		this.coddist = coddist;
	}


	/**
	 * @return the desdpto
	 */
	public String getDesdpto() {
		return desdpto;
	}


	/**
	 * @param desdpto the desdpto to set
	 */
	public void setDesdpto(String desdpto) {
		this.desdpto = desdpto;
	}


	/**
	 * @return the desprov
	 */
	public String getDesprov() {
		return desprov;
	}


	/**
	 * @param desprov the desprov to set
	 */
	public void setDesprov(String desprov) {
		this.desprov = desprov;
	}


	/**
	 * @return the desdist
	 */
	public String getDesdist() {
		return desdist;
	}


	/**
	 * @param desdist the desdist to set
	 */
	public void setDesdist(String desdist) {
		this.desdist = desdist;
	}
	
	
	

}
