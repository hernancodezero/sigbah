package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: MBaseBean.java
 * @description: Clase genérica que contiene los campos asociados a cada objeto bean.
 * @date: 28/01/2015 11:17:55
 * @author: Hernando Huaman.
 */
public class MBaseBean implements Serializable {

	private static final long serialVersionUID = 1736301694787409541L;
	protected Date fec_creacion;
	protected String vnomusuario;
	
	
	/**
	 * @return the fec_creacion
	 */
	public Date getFec_creacion() {
		return fec_creacion;
	}
	/**
	 * @param fec_creacion the fec_creacion to set
	 */
	public void setFec_creacion(Date fec_creacion) {
		this.fec_creacion = fec_creacion;
	}
	/**
	 * @return the vnomusuario
	 */
	public String getVnomusuario() {
		return vnomusuario;
	}
	/**
	 * @param vnomusuario the vnomusuario to set
	 */
	public void setVnomusuario(String vnomusuario) {
		this.vnomusuario = vnomusuario;
	}	

}
