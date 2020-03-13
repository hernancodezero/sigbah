package pe.com.sigbah.common.bean;

import java.util.List;

/**
 * @className: EmergenciaBean.java
 * @description: 
 * @date: 14 jul. 2017
 * @author: whr.
 */
public class ListaRespuestaConsultaProductoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	
	private List<ProductoConsultaProductoBean> lstAlimentaria;
	private List<ProductoConsultaProductoBean> lstNoAlimentaria;
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
