package pe.com.sigbah.common.bean;

import java.io.Serializable;

/**
 * @className: AlmacenesWSBean.java
 * @description: Clase asociado a almacenes.
 * @date: 14/08/2018
 * @author: Alan Chumpitaz.
 */
public class AlmacenesWSBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idDdi;
	private Integer idAlmacen;
	private String nombreAlmacen;
	
	/**
	 * 
	 */
	public AlmacenesWSBean() {
		super();
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
	 * @return the idAlmacen
	 */
	public Integer getIdAlmacen() {
		return idAlmacen;
	}
	/**
	 * @param idAlmacen the idAlmacen to set
	 */
	public void setIdAlmacen(Integer idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	
	/**
	 * @return the nombreAlmacen
	 */
	public String getNombreAlmacen() {
		return nombreAlmacen;
	}
	/**
	 * @param nombreAlmacen the nombreAlmacen to set
	 */
	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}

}
