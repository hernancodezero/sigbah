package pe.com.sigbah.common.bean;

import java.util.List;

/**
 * @className: EmergenciaBean.java
 * @description: 
 * @date: 14 jul. 2017
 * @author: whr.
 */
public class ListaRespuestaRequerimientoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private List<RequerimientoBean> lstCabecera;
	private List<EmergenciaBean> lstDetalle;
	/**
	 * @return the lstCabecera
	 */
	public List<RequerimientoBean> getLstCabecera() {
		return lstCabecera;
	}
	/**
	 * @param lstCabecera the lstCabecera to set
	 */
	public void setLstCabecera(List<RequerimientoBean> lstCabecera) {
		this.lstCabecera = lstCabecera;
	}
	/**
	 * @return the lstDetalle
	 */
	public List<EmergenciaBean> getLstDetalle() {
		return lstDetalle;
	}
	/**
	 * @param lstDetalle the lstDetalle to set
	 */
	public void setLstDetalle(List<EmergenciaBean> lstDetalle) {
		this.lstDetalle = lstDetalle;
	}
	
	
	
}
