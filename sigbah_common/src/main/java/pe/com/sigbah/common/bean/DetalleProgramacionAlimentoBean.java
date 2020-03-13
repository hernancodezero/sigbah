package pe.com.sigbah.common.bean;

import java.util.List;

/**
 * @className: DetalleProgramacionAlimentoBean.java
 * @description: 
 * @date: 31 de jul. de 2017
 * @author: SUMERIO.
 */
public class DetalleProgramacionAlimentoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private List<ProgramacionAlimentoBean> listaProgramacionAlimento;
	private List<ResumenStockAlimentoBean> listaResumenStock;
	
	
	/**
	 * @return the listaProgramacionAlimento
	 */
	public List<ProgramacionAlimentoBean> getListaProgramacionAlimento() {
		return listaProgramacionAlimento;
	}
	/**
	 * @param listaProgramacionAlimento the listaProgramacionAlimento to set
	 */
	public void setListaProgramacionAlimento(List<ProgramacionAlimentoBean> listaProgramacionAlimento) {
		this.listaProgramacionAlimento = listaProgramacionAlimento;
	}
	/**
	 * @return the listaResumenStock
	 */
	public List<ResumenStockAlimentoBean> getListaResumenStock() {
		return listaResumenStock;
	}
	/**
	 * @param listaResumenStock the listaResumenStock to set
	 */
	public void setListaResumenStock(List<ResumenStockAlimentoBean> listaResumenStock) {
		this.listaResumenStock = listaResumenStock;
	}
	
}
