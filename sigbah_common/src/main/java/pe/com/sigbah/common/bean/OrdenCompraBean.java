package pe.com.sigbah.common.bean;

/**
 * @className: OrdenCompraBean.java
 * @description: 
 * @date: 24/06/2017
 * @author: Sumerio.
 */
public class OrdenCompraBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private String nroOrdenCompra;
	private String concepto;
	private Integer idProveedor;
	private String razonSocial;
	private String responsable;
	
	/**
	 * @return the nroOrdenCompra
	 */
	public String getNroOrdenCompra() {
		return nroOrdenCompra;
	}
	/**
	 * @param nroOrdenCompra the nroOrdenCompra to set
	 */
	public void setNroOrdenCompra(String nroOrdenCompra) {
		this.nroOrdenCompra = nroOrdenCompra;
	}
	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}
	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * @return the idProveedor
	 */
	public Integer getIdProveedor() {
		return idProveedor;
	}
	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}
	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return the representante
	 */
	public String getResponsable() {
		return responsable;
	}
	/**
	 * @param representante the representante to set
	 */
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

}
