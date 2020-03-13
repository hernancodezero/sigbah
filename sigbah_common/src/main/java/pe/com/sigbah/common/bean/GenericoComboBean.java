package pe.com.sigbah.common.bean;

import java.io.Serializable;

/**
 * @className: GenericoComboBean.java
 * @description: 
 * @date: 3 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class GenericoComboBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descripcion;
	private String flagAux1;
	
	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the flagAux1
	 */
	public String getFlagAux1() {
		return flagAux1;
	}
	/**
	 * @param flagAux1 the flagAux1 to set
	 */
	public void setFlagAux1(String flagAux1) {
		this.flagAux1 = flagAux1;
	}

}
