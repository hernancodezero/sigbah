package pe.com.sigbah.common.bean;

import java.util.List;

/**
 * @className: EmergenciaBean.java
 * @description: 
 * @date: 14 jul. 2017
 * @author: whr.
 */
public class ListaRespuestaPedidoCompraBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private List<PedidoCompraReporteBean> lstCabecera;
	private List<PedidoCompraReporteBean> lstDetalle;
	/**
	 * @return the lstCabecera
	 */
	/**
	 * @return the lstCabecera
	 */
	public List<PedidoCompraReporteBean> getLstCabecera() {
		return lstCabecera;
	}
	/**
	 * @param lstCabecera the lstCabecera to set
	 */
	public void setLstCabecera(List<PedidoCompraReporteBean> lstCabecera) {
		this.lstCabecera = lstCabecera;
	}
	/**
	 * @return the lstDetalle
	 */
	public List<PedidoCompraReporteBean> getLstDetalle() {
		return lstDetalle;
	}
	/**
	 * @param lstDetalle the lstDetalle to set
	 */
	public void setLstDetalle(List<PedidoCompraReporteBean> lstDetalle) {
		this.lstDetalle = lstDetalle;
	}
	
	
	
}
