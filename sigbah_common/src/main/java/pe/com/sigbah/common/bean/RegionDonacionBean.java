package pe.com.sigbah.common.bean;

/**
 * @className: RegionDonacionBean.java
 * @description: Clase RegionDonacionBean.
 * @date: 18/07/2017 
 * @author: ARCHY.
 */
public class RegionDonacionBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	
	private Integer idRegion;
	private Integer idDonacion;
	private String nombreRegion;

	/**
	 * 
	 */
	public RegionDonacionBean() {
		super();
	}
	
	public RegionDonacionBean(Integer idRegion) {
		super();
		this.idRegion = idRegion;
	}


	public Integer getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}
	
	public Integer getIdDonacion() {
		return idDonacion;
	}

	public void setIdDonacion(Integer idDonacion) {
		this.idDonacion = idDonacion;
	}
	
	public String getNombreRegion() {
		return nombreRegion;
	}

	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}

}