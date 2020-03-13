package pe.com.sigbah.common.bean;


/**
 * @className: EmergenciaBean.java
 * @description: 
 * @date: 14 jul. 2017
 * @author: whr.
 */
public class NoAlimentariaEmergenciaBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer idEmergencia;
	private String codAnio;
	private String codProducto;
	private String tipoProducto;
	private String codCategoria;
	private String categoriaProducto;
	private Integer cantidad;
	
	
	
	
	/**
	 * 
	 */
	public NoAlimentariaEmergenciaBean() {
		super();
	}
	
	
	/**
	 * @param idEmergencia
	 */
	public NoAlimentariaEmergenciaBean(Integer idEmergencia) {
		super();
		this.idEmergencia = idEmergencia;
	}


	/**
	 * @return the idEmergencia
	 */
	public Integer getIdEmergencia() {
		return idEmergencia;
	}
	/**
	 * @param idEmergencia the idEmergencia to set
	 */
	public void setIdEmergencia(Integer idEmergencia) {
		this.idEmergencia = idEmergencia;
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
	 * @return the codProducto
	 */
	public String getCodProducto() {
		return codProducto;
	}
	/**
	 * @param codProducto the codProducto to set
	 */
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	/**
	 * @return the tipoProducto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}
	/**
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	/**
	 * @return the codCategoria
	 */
	public String getCodCategoria() {
		return codCategoria;
	}
	/**
	 * @param codCategoria the codCategoria to set
	 */
	public void setCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}
	/**
	 * @return the categoriaProducto
	 */
	public String getCategoriaProducto() {
		return categoriaProducto;
	}
	/**
	 * @param categoriaProducto the categoriaProducto to set
	 */
	public void setCategoriaProducto(String categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}
	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
}
