package pe.com.sigbah.common.bean;

import java.util.Date;

/**
 * @className: EntidadFinancieraBean.java
 * @description: 
 * @date: 16 de jun. de 2016
 * @author: Junior Huaman Flores.
 */
public class UbigeoBean extends BaseOutputBean {
	
	private static final long serialVersionUID = 1L;
	private int idubigeo;
	private String coddpto;
	private String codprov;
	private String coddist;
	private String nombre;
	private String chrCodUsuaCreacion;
	private Date dteFecCreacion;
	private String chrCodUsuaModifica;
	private Date dteFecModifica;
	
	/**
	 * 
	 */
	public UbigeoBean() {
		super();
	}
	
	
	/**
	 * @param coddpto
	 * @param nombre
	 */
	public UbigeoBean(String coddpto, String nombre) {
		super();
		this.coddpto = coddpto;
		this.nombre = nombre;
	}



	/**
	 * @return the idubigeo
	 */
	public int getIdubigeo() {
		return idubigeo;
	}

	/**
	 * @param idubigeo the idubigeo to set
	 */
	public void setIdubigeo(int idubigeo) {
		this.idubigeo = idubigeo;
	}

	/**
	 * @return the coddpto
	 */
	public String getCoddpto() {
		return coddpto;
	}

	/**
	 * @param coddpto the coddpto to set
	 */
	public void setCoddpto(String coddpto) {
		this.coddpto = coddpto;
	}

	/**
	 * @return the codprov
	 */
	public String getCodprov() {
		return codprov;
	}

	/**
	 * @param codprov the codprov to set
	 */
	public void setCodprov(String codprov) {
		this.codprov = codprov;
	}

	/**
	 * @return the coddist
	 */
	public String getCoddist() {
		return coddist;
	}

	/**
	 * @param coddist the coddist to set
	 */
	public void setCoddist(String coddist) {
		this.coddist = coddist;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the chrCodUsuaCreacion
	 */
	public String getChrCodUsuaCreacion() {
		return chrCodUsuaCreacion;
	}


	/**
	 * @param chrCodUsuaCreacion the chrCodUsuaCreacion to set
	 */
	public void setChrCodUsuaCreacion(String chrCodUsuaCreacion) {
		this.chrCodUsuaCreacion = chrCodUsuaCreacion;
	}


	/**
	 * @return the dteFecCreacion
	 */
	public Date getDteFecCreacion() {
		return dteFecCreacion;
	}


	/**
	 * @param dteFecCreacion the dteFecCreacion to set
	 */
	public void setDteFecCreacion(Date dteFecCreacion) {
		this.dteFecCreacion = dteFecCreacion;
	}


	/**
	 * @return the chrCodUsuaModifica
	 */
	public String getChrCodUsuaModifica() {
		return chrCodUsuaModifica;
	}


	/**
	 * @param chrCodUsuaModifica the chrCodUsuaModifica to set
	 */
	public void setChrCodUsuaModifica(String chrCodUsuaModifica) {
		this.chrCodUsuaModifica = chrCodUsuaModifica;
	}


	/**
	 * @return the dteFecModifica
	 */
	public Date getDteFecModifica() {
		return dteFecModifica;
	}


	/**
	 * @param dteFecModifica the dteFecModifica to set
	 */
	public void setDteFecModifica(Date dteFecModifica) {
		this.dteFecModifica = dteFecModifica;
	}


	


}
