package pe.com.sigbah.common.bean;

import java.util.List;

/**
 * @className: DetalleUsuarioBean.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DetalleUsuarioBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;	
	private String indicadorUsuario;
	private List<UsuarioBean> datosUsuario;
	
	
	/**
	 * @return the indicadorUsuario
	 */
	public String getIndicadorUsuario() {
		return indicadorUsuario;
	}
	/**
	 * @param indicadorUsuario the indicadorUsuario to set
	 */
	public void setIndicadorUsuario(String indicadorUsuario) {
		this.indicadorUsuario = indicadorUsuario;
	}
	/**
	 * @return the datosUsuario
	 */
	public List<UsuarioBean> getDatosUsuario() {
		return datosUsuario;
	}
	/**
	 * @param datosUsuario the datosUsuario to set
	 */
	public void setDatosUsuario(List<UsuarioBean> datosUsuario) {
		this.datosUsuario = datosUsuario;
	}

}
