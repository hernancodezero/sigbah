package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ProductoBean.java
 * @description: Clase ProductoBean.
 * @date: 18/02/2015 22:26:30
 * @author: Junior Huaman Flores.
 */
public class ProductoConsultaProductoBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer idProgramacion;
	private Integer idProducto;
	private String nombreCategoria;
	private String nombreProducto;
	private String nombreUnidadMedida;
	
	private Integer nroUnidades;
	private BigDecimal pesoUniPres;
	private BigDecimal totalKilos;
	private BigDecimal totalToneladas;
	
	
	
	/**
	 * 
	 */
	public ProductoConsultaProductoBean() {
		super();
	}



	/**
	 * @return the idProgramacion
	 */
	public Integer getIdProgramacion() {
		return idProgramacion;
	}



	/**
	 * @param idProgramacion the idProgramacion to set
	 */
	public void setIdProgramacion(Integer idProgramacion) {
		this.idProgramacion = idProgramacion;
	}



	/**
	 * @return the idProducto
	 */
	public Integer getIdProducto() {
		return idProducto;
	}



	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}



	/**
	 * @return the nombreCategoria
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}



	/**
	 * @param nombreCategoria the nombreCategoria to set
	 */
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}



	/**
	 * @return the nombreProducto
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}



	/**
	 * @param nombreProducto the nombreProducto to set
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}



	/**
	 * @return the nombreUnidadMedida
	 */
	public String getNombreUnidadMedida() {
		return nombreUnidadMedida;
	}



	/**
	 * @param nombreUnidadMedida the nombreUnidadMedida to set
	 */
	public void setNombreUnidadMedida(String nombreUnidadMedida) {
		this.nombreUnidadMedida = nombreUnidadMedida;
	}



	/**
	 * @return the nroUnidades
	 */
	public Integer getNroUnidades() {
		return nroUnidades;
	}



	/**
	 * @param nroUnidades the nroUnidades to set
	 */
	public void setNroUnidades(Integer nroUnidades) {
		this.nroUnidades = nroUnidades;
	}



	/**
	 * @return the pesoUniPres
	 */
	public BigDecimal getPesoUniPres() {
		return pesoUniPres;
	}



	/**
	 * @param pesoUniPres the pesoUniPres to set
	 */
	public void setPesoUniPres(BigDecimal pesoUniPres) {
		this.pesoUniPres = pesoUniPres;
	}



	/**
	 * @return the totalKilos
	 */
	public BigDecimal getTotalKilos() {
		return totalKilos;
	}



	/**
	 * @param totalKilos the totalKilos to set
	 */
	public void setTotalKilos(BigDecimal totalKilos) {
		this.totalKilos = totalKilos;
	}



	/**
	 * @return the totalToneladas
	 */
	public BigDecimal getTotalToneladas() {
		return totalToneladas;
	}



	/**
	 * @param totalToneladas the totalToneladas to set
	 */
	public void setTotalToneladas(BigDecimal totalToneladas) {
		this.totalToneladas = totalToneladas;
	}
	
	
}