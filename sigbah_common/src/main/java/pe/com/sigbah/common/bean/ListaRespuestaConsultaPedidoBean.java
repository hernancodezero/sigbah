package pe.com.sigbah.common.bean;

import java.util.List;

/**
 * @className: EmergenciaBean.java
 * @description: 
 * @date: 14 jul. 2017
 * @author: whr.
 */
public class ListaRespuestaConsultaPedidoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private List<ProductoConsultaPedidoBean> lstCabecera;
	private List<ProductoConsultaPedidoBean> lstAlimentaria;
	private List<ProductoConsultaPedidoBean> lstNoAlimentaria;
	/**
	 * @return the lstCabecera
	 */
	public List<ProductoConsultaPedidoBean> getLstCabecera() {
		return lstCabecera;
	}
	/**
	 * @param lstCabecera the lstCabecera to set
	 */
	public void setLstCabecera(List<ProductoConsultaPedidoBean> lstCabecera) {
		this.lstCabecera = lstCabecera;
	}
	/**
	 * @return the lstAlimentaria
	 */
	public List<ProductoConsultaPedidoBean> getLstAlimentaria() {
		return lstAlimentaria;
	}
	/**
	 * @param lstAlimentaria the lstAlimentaria to set
	 */
	public void setLstAlimentaria(List<ProductoConsultaPedidoBean> lstAlimentaria) {
		this.lstAlimentaria = lstAlimentaria;
	}
	/**
	 * @return the lstNoAlimentaria
	 */
	public List<ProductoConsultaPedidoBean> getLstNoAlimentaria() {
		return lstNoAlimentaria;
	}
	/**
	 * @param lstNoAlimentaria the lstNoAlimentaria to set
	 */
	public void setLstNoAlimentaria(List<ProductoConsultaPedidoBean> lstNoAlimentaria) {
		this.lstNoAlimentaria = lstNoAlimentaria;
	}
	
	
	
}
