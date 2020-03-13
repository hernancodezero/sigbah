package pe.com.sigbah.common.bean;

import java.util.List;

/**
 * @className: ReporteConsultaProgramacionBean.java
 * @description: 
 * @date: 10 oct. 2017
 * @author: ARCHY.
 */
public class ReporteConsultaProgramacionBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private List<ProgramacionBean> lstCabecera;
	private List<ProductoConsultaProductoBean> lstAlimentaria;
	private List<ProductoConsultaProductoBean> lstNoAlimentaria;
	
	
	
	/**
	 * @return the lstCabecera
	 */
	public List<ProgramacionBean> getLstCabecera() {
		return lstCabecera;
	}
	/**
	 * @param lstCabecera the lstCabecera to set
	 */
	public void setLstCabecera(List<ProgramacionBean> lstCabecera) {
		this.lstCabecera = lstCabecera;
	}

	/**
	 * @return the lstAlimentaria
	 */
	public List<ProductoConsultaProductoBean> getLstAlimentaria() {
		return lstAlimentaria;
	}
	/**
	 * @param lstAlimentaria the lstAlimentaria to set
	 */
	public void setLstAlimentaria(List<ProductoConsultaProductoBean> lstAlimentaria) {
		this.lstAlimentaria = lstAlimentaria;
	}
	/**
	 * @return the lstNoAlimentaria
	 */
	public List<ProductoConsultaProductoBean> getLstNoAlimentaria() {
		return lstNoAlimentaria;
	}
	/**
	 * @param lstNoAlimentaria the lstNoAlimentaria to set
	 */
	public void setLstNoAlimentaria(List<ProductoConsultaProductoBean> lstNoAlimentaria) {
		this.lstNoAlimentaria = lstNoAlimentaria;
	}
	
	
}
