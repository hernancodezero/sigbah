package pe.com.sigbah.common.bean;

import java.util.List;

/**
 * @className: ProgramacionBean.java
 * @description: 
 * @date: 29/07/2017
 * @author: Junior Huaman Flores.
 */
public class ConsultaPedidoCompraBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer idPedidoCompra;
	private String codigoAnio;
	private Integer idDdi;
	private Integer tipoPedido;//idmotivo
	private String motivo;
	private Integer idEstado;
	private String nomDdi;
	
	private Double totalTonAlimento;
	private Double totalSolesAlimento;
	private Double totalTonBna;
	private Double totalSolesBna;
	
	private String numPedido;
	
	/**
	 * 
	 */
	public ConsultaPedidoCompraBean() {
		super();
	}



	/**
	 * @return the idPedidoCompra
	 */
	public Integer getIdPedidoCompra() {
		return idPedidoCompra;
	}



	/**
	 * @param idPedidoCompra the idPedidoCompra to set
	 */
	public void setIdPedidoCompra(Integer idPedidoCompra) {
		this.idPedidoCompra = idPedidoCompra;
	}



	/**
	 * @return the codigoAnio
	 */
	public String getCodigoAnio() {
		return codigoAnio;
	}



	/**
	 * @param codigoAnio the codigoAnio to set
	 */
	public void setCodigoAnio(String codigoAnio) {
		this.codigoAnio = codigoAnio;
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
	 * @return the tipoPedido
	 */
	public Integer getTipoPedido() {
		return tipoPedido;
	}



	/**
	 * @param tipoPedido the tipoPedido to set
	 */
	public void setTipoPedido(Integer tipoPedido) {
		this.tipoPedido = tipoPedido;
	}



	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}



	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}



	/**
	 * @return the idEstado
	 */
	public Integer getIdEstado() {
		return idEstado;
	}



	/**
	 * @param idEstado the idEstado to set
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}



	/**
	 * @return the nomDdi
	 */
	public String getNomDdi() {
		return nomDdi;
	}



	/**
	 * @param nomDdi the nomDdi to set
	 */
	public void setNomDdi(String nomDdi) {
		this.nomDdi = nomDdi;
	}



	/**
	 * @return the totalTonAlimento
	 */
	public Double getTotalTonAlimento() {
		return totalTonAlimento;
	}



	/**
	 * @param totalTonAlimento the totalTonAlimento to set
	 */
	public void setTotalTonAlimento(Double totalTonAlimento) {
		this.totalTonAlimento = totalTonAlimento;
	}



	/**
	 * @return the totalSolesAlimento
	 */
	public Double getTotalSolesAlimento() {
		return totalSolesAlimento;
	}



	/**
	 * @param totalSolesAlimento the totalSolesAlimento to set
	 */
	public void setTotalSolesAlimento(Double totalSolesAlimento) {
		this.totalSolesAlimento = totalSolesAlimento;
	}



	/**
	 * @return the totalTonBna
	 */
	public Double getTotalTonBna() {
		return totalTonBna;
	}



	/**
	 * @param totalTonBna the totalTonBna to set
	 */
	public void setTotalTonBna(Double totalTonBna) {
		this.totalTonBna = totalTonBna;
	}



	/**
	 * @return the totalSolesBna
	 */
	public Double getTotalSolesBna() {
		return totalSolesBna;
	}



	/**
	 * @param totalSolesBna the totalSolesBna to set
	 */
	public void setTotalSolesBna(Double totalSolesBna) {
		this.totalSolesBna = totalSolesBna;
	}



	/**
	 * @return the numPedido
	 */
	public String getNumPedido() {
		return numPedido;
	}



	/**
	 * @param numPedido the numPedido to set
	 */
	public void setNumPedido(String numPedido) {
		this.numPedido = numPedido;
	}
	
	
}
