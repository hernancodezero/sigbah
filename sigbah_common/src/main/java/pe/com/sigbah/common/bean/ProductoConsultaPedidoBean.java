package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ProductoBean.java
 * @description: Clase ProductoBean.
 * @date: 18/02/2015 22:26:30
 * @author: Junior Huaman Flores.
 */
public class ProductoConsultaPedidoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private String codAnio;
	private Integer idEstado;
	private Integer idMotivo;//tipo pedido
	private Integer idDdi;
	private String nomDdi;
	private String motivo;
	
	private BigDecimal totalToneladasAli;
	private BigDecimal totalSolesAli;
	private BigDecimal totalToneladasBna;
	private BigDecimal totalSolesBna;
	
	private String estado;
	
	private String nomProducto;
	private String nomCategoria;
	private String nomUnidadMedida;
	private BigDecimal totalCantidad;
	
	private Integer idPedido;
	private String numPedido;
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}


	/**
	 * 
	 */
	public ProductoConsultaPedidoBean() {
		super();
	}


	/**
	 * @return the codAnio
	 */
	public String getCodAnio() {
		return codAnio;
	}


	/**
	 * @param codAnio the codAnio to set
	 */
	public void setCodAnio(String codAnio) {
		this.codAnio = codAnio;
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
	 * @return the idMotivo
	 */
	public Integer getIdMotivo() {
		return idMotivo;
	}


	/**
	 * @param idMotivo the idMotivo to set
	 */
	public void setIdMotivo(Integer idMotivo) {
		this.idMotivo = idMotivo;
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
	 * @return the totalToneladasAli
	 */
	public BigDecimal getTotalToneladasAli() {
		return totalToneladasAli;
	}


	/**
	 * @param totalToneladasAli the totalToneladasAli to set
	 */
	public void setTotalToneladasAli(BigDecimal totalToneladasAli) {
		this.totalToneladasAli = totalToneladasAli;
	}


	/**
	 * @return the totalSolesAli
	 */
	public BigDecimal getTotalSolesAli() {
		return totalSolesAli;
	}


	/**
	 * @param totalSolesAli the totalSolesAli to set
	 */
	public void setTotalSolesAli(BigDecimal totalSolesAli) {
		this.totalSolesAli = totalSolesAli;
	}


	/**
	 * @return the totalToneladasBna
	 */
	public BigDecimal getTotalToneladasBna() {
		return totalToneladasBna;
	}


	/**
	 * @param totalToneladasBna the totalToneladasBna to set
	 */
	public void setTotalToneladasBna(BigDecimal totalToneladasBna) {
		this.totalToneladasBna = totalToneladasBna;
	}


	/**
	 * @return the totalSolesBna
	 */
	public BigDecimal getTotalSolesBna() {
		return totalSolesBna;
	}


	/**
	 * @param totalSolesBna the totalSolesBna to set
	 */
	public void setTotalSolesBna(BigDecimal totalSolesBna) {
		this.totalSolesBna = totalSolesBna;
	}


	/**
	 * @return the nomProducto
	 */
	public String getNomProducto() {
		return nomProducto;
	}


	/**
	 * @param nomProducto the nomProducto to set
	 */
	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}


	/**
	 * @return the nomUnidadMedida
	 */
	public String getNomUnidadMedida() {
		return nomUnidadMedida;
	}


	/**
	 * @param nomUnidadMedida the nomUnidadMedida to set
	 */
	public void setNomUnidadMedida(String nomUnidadMedida) {
		this.nomUnidadMedida = nomUnidadMedida;
	}


	/**
	 * @return the totalCantidad
	 */
	public BigDecimal getTotalCantidad() {
		return totalCantidad;
	}


	/**
	 * @param totalCantidad the totalCantidad to set
	 */
	public void setTotalCantidad(BigDecimal totalCantidad) {
		this.totalCantidad = totalCantidad;
	}


	/**
	 * @return the nomCategoria
	 */
	public String getNomCategoria() {
		return nomCategoria;
	}


	/**
	 * @param nomCategoria the nomCategoria to set
	 */
	public void setNomCategoria(String nomCategoria) {
		this.nomCategoria = nomCategoria;
	}


	/**
	 * @return the idPedido
	 */
	public Integer getIdPedido() {
		return idPedido;
	}


	/**
	 * @param idPedido the idPedido to set
	 */
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
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