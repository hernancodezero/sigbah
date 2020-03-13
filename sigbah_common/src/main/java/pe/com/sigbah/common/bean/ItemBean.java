package pe.com.sigbah.common.bean;

import java.math.BigDecimal;

/**
 * @className: ItemBean.java
 * @description: POJO genérico para cargar combos, autocompletar, etc.
 * @date: 18/02/2015 22:26:30
 * @author: Junior Huaman Flores.
 */
public class ItemBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer icodigo;
	private Integer icodigoParam2;
	private Integer icodigoParam3;
	private Integer icodigoParam4;
	private Integer icodigoParam5;
	private String vcodigo;
	private String vcodigoParam2;
	private String vcodigoParam3;
	private String vcodigoParam4;
	private String vcodigoParam5;
	private BigDecimal bcodigo;
	private BigDecimal bcodigoParam2;
	private BigDecimal bcodigoParam3;
	private BigDecimal bcodigoParam4;
	private BigDecimal bcodigoParam5;
	private String descripcion;
	private String descripcionCorta;
	
	/**
	 * 
	 */
	public ItemBean() {
		super();
	}

	
	/**
	 * @param icodigo
	 */
	public ItemBean(Integer icodigo) {
		super();
		this.icodigo = icodigo;
	}

	/**
	 * @param vcodigo
	 */
	public ItemBean(String vcodigo) {
		super();
		this.vcodigo = vcodigo;
	}

	/**
	 * @param icodigo - Código de la entidad, tipo Integer.
	 * @param descripcion - Descripción de la entidad, tipo String.
	 * @param descripcionCorta - Descripción corta de la entidad, tipo String.
	 */
	public ItemBean(Integer icodigo, String descripcion, String descripcionCorta) {
		super();
		this.icodigo = icodigo;
		this.descripcion = descripcion;
		this.descripcionCorta = descripcionCorta;
	}
	
	/**
	 * @param icodigo - Código de la entidad, tipo Integer.
	 * @param descripcion - Descripción de la entidad, tipo String.
	 */
	public ItemBean(Integer icodigo, String descripcion) {
		super();
		this.icodigo = icodigo;
		this.descripcion = descripcion;
	}

	/**
	 * @param vcodigo
	 * @param descripcion
	 */
	public ItemBean(String vcodigo, String descripcion) {
		super();
		this.vcodigo = vcodigo;
		this.descripcion = descripcion;
	}
	
	/**
	 * @param icodigo
	 * @param icodigoParam2
	 */
	public ItemBean(Integer icodigo, Integer icodigoParam2) {
		super();
		this.icodigo = icodigo;
		this.icodigoParam2 = icodigoParam2;
	}

	/**
	 * @return the icodigo
	 */
	public Integer getIcodigo() {
		return icodigo;
	}

	/**
	 * @param icodigo the icodigo to set
	 */
	public void setIcodigo(Integer icodigo) {
		this.icodigo = icodigo;
	}

	/**
	 * @return the vcodigo
	 */
	public String getVcodigo() {
		return vcodigo;
	}

	/**
	 * @param vcodigo the vcodigo to set
	 */
	public void setVcodigo(String vcodigo) {
		this.vcodigo = vcodigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the descripcionCorta
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	
	/**
	 * @param descripcionCorta the descripcionCorta to set
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	/**
	 * @return the icodigoParam2
	 */
	public Integer getIcodigoParam2() {
		return icodigoParam2;
	}

	/**
	 * @param icodigoParam2 the icodigoParam2 to set
	 */
	public void setIcodigoParam2(Integer icodigoParam2) {
		this.icodigoParam2 = icodigoParam2;
	}

	/**
	 * @return the vcodigoParam2
	 */
	public String getVcodigoParam2() {
		return vcodigoParam2;
	}

	/**
	 * @param vcodigoParam2 the vcodigoParam2 to set
	 */
	public void setVcodigoParam2(String vcodigoParam2) {
		this.vcodigoParam2 = vcodigoParam2;
	}

	/**
	 * @return the icodigoParam3
	 */
	public Integer getIcodigoParam3() {
		return icodigoParam3;
	}

	/**
	 * @param icodigoParam3 the icodigoParam3 to set
	 */
	public void setIcodigoParam3(Integer icodigoParam3) {
		this.icodigoParam3 = icodigoParam3;
	}

	/**
	 * @return the icodigoParam4
	 */
	public Integer getIcodigoParam4() {
		return icodigoParam4;
	}

	/**
	 * @param icodigoParam4 the icodigoParam4 to set
	 */
	public void setIcodigoParam4(Integer icodigoParam4) {
		this.icodigoParam4 = icodigoParam4;
	}

	/**
	 * @return the icodigoParam5
	 */
	public Integer getIcodigoParam5() {
		return icodigoParam5;
	}

	/**
	 * @param icodigoParam5 the icodigoParam5 to set
	 */
	public void setIcodigoParam5(Integer icodigoParam5) {
		this.icodigoParam5 = icodigoParam5;
	}

	/**
	 * @return the vcodigoParam3
	 */
	public String getVcodigoParam3() {
		return vcodigoParam3;
	}

	/**
	 * @param vcodigoParam3 the vcodigoParam3 to set
	 */
	public void setVcodigoParam3(String vcodigoParam3) {
		this.vcodigoParam3 = vcodigoParam3;
	}

	/**
	 * @return the vcodigoParam4
	 */
	public String getVcodigoParam4() {
		return vcodigoParam4;
	}

	/**
	 * @param vcodigoParam4 the vcodigoParam4 to set
	 */
	public void setVcodigoParam4(String vcodigoParam4) {
		this.vcodigoParam4 = vcodigoParam4;
	}

	/**
	 * @return the vcodigoParam5
	 */
	public String getVcodigoParam5() {
		return vcodigoParam5;
	}

	/**
	 * @param vcodigoParam5 the vcodigoParam5 to set
	 */
	public void setVcodigoParam5(String vcodigoParam5) {
		this.vcodigoParam5 = vcodigoParam5;
	}

	/**
	 * @return the bcodigo
	 */
	public BigDecimal getBcodigo() {
		return bcodigo;
	}

	/**
	 * @param bcodigo the bcodigo to set
	 */
	public void setBcodigo(BigDecimal bcodigo) {
		this.bcodigo = bcodigo;
	}

	/**
	 * @return the bcodigoParam2
	 */
	public BigDecimal getBcodigoParam2() {
		return bcodigoParam2;
	}

	/**
	 * @param bcodigoParam2 the bcodigoParam2 to set
	 */
	public void setBcodigoParam2(BigDecimal bcodigoParam2) {
		this.bcodigoParam2 = bcodigoParam2;
	}

	/**
	 * @return the bcodigoParam3
	 */
	public BigDecimal getBcodigoParam3() {
		return bcodigoParam3;
	}

	/**
	 * @param bcodigoParam3 the bcodigoParam3 to set
	 */
	public void setBcodigoParam3(BigDecimal bcodigoParam3) {
		this.bcodigoParam3 = bcodigoParam3;
	}

	/**
	 * @return the bcodigoParam4
	 */
	public BigDecimal getBcodigoParam4() {
		return bcodigoParam4;
	}

	/**
	 * @param bcodigoParam4 the bcodigoParam4 to set
	 */
	public void setBcodigoParam4(BigDecimal bcodigoParam4) {
		this.bcodigoParam4 = bcodigoParam4;
	}

	/**
	 * @return the bcodigoParam5
	 */
	public BigDecimal getBcodigoParam5() {
		return bcodigoParam5;
	}

	/**
	 * @param bcodigoParam5 the bcodigoParam5 to set
	 */
	public void setBcodigoParam5(BigDecimal bcodigoParam5) {
		this.bcodigoParam5 = bcodigoParam5;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemBean [icodigo=" + icodigo + ", icodigoParam2=" + icodigoParam2 + ", icodigoParam3=" + icodigoParam3
				+ ", icodigoParam4=" + icodigoParam4 + ", icodigoParam5=" + icodigoParam5 + ", vcodigo=" + vcodigo
				+ ", vcodigoParam2=" + vcodigoParam2 + ", vcodigoParam3=" + vcodigoParam3 + ", vcodigoParam4="
				+ vcodigoParam4 + ", vcodigoParam5=" + vcodigoParam5 + ", bcodigo=" + bcodigo + ", bcodigoParam2="
				+ bcodigoParam2 + ", bcodigoParam3=" + bcodigoParam3 + ", bcodigoParam4=" + bcodigoParam4
				+ ", bcodigoParam5=" + bcodigoParam5 + ", descripcion=" + descripcion + ", descripcionCorta="
				+ descripcionCorta + "]";
	}
	
}