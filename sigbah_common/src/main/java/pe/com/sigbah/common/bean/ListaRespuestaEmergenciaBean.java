package pe.com.sigbah.common.bean;

import java.util.List;

/**
 * @className: EmergenciaBean.java
 * @description: 
 * @date: 14 jul. 2017
 * @author: whr.
 */
public class ListaRespuestaEmergenciaBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private List<CabeceraEmergenciaBean> lstCabecera;
	private List<LocalidadEmergenciaBean> lstLocalidad;
	private List<AlimentariaEmergenciaBean> lstAlimentaria;
	private List<NoAlimentariaEmergenciaBean> lstNoAlimentaria;
	/**
	 * @return the lstCabecera
	 */
	public List<CabeceraEmergenciaBean> getLstCabecera() {
		return lstCabecera;
	}
	/**
	 * @param lstCabecera the lstCabecera to set
	 */
	public void setLstCabecera(List<CabeceraEmergenciaBean> lstCabecera) {
		this.lstCabecera = lstCabecera;
	}
	/**
	 * @return the lstLocalidad
	 */
	public List<LocalidadEmergenciaBean> getLstLocalidad() {
		return lstLocalidad;
	}
	/**
	 * @param lstLocalidad the lstLocalidad to set
	 */
	public void setLstLocalidad(List<LocalidadEmergenciaBean> lstLocalidad) {
		this.lstLocalidad = lstLocalidad;
	}
	/**
	 * @return the lstAlimentaria
	 */
	public List<AlimentariaEmergenciaBean> getLstAlimentaria() {
		return lstAlimentaria;
	}
	/**
	 * @param lstAlimentaria the lstAlimentaria to set
	 */
	public void setLstAlimentaria(List<AlimentariaEmergenciaBean> lstAlimentaria) {
		this.lstAlimentaria = lstAlimentaria;
	}
	/**
	 * @return the lstNoAlimentaria
	 */
	public List<NoAlimentariaEmergenciaBean> getLstNoAlimentaria() {
		return lstNoAlimentaria;
	}
	/**
	 * @param lstNoAlimentaria the lstNoAlimentaria to set
	 */
	public void setLstNoAlimentaria(List<NoAlimentariaEmergenciaBean> lstNoAlimentaria) {
		this.lstNoAlimentaria = lstNoAlimentaria;
	}
	
	
}
