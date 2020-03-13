package pe.com.sigbah.common.bean;

/**
 * @className: PerfilBean.java
 * @description: 
 * @date: 13 de jul. de 2016
 * @author: Junior Huaman Flores.
 */
public class PerfilBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	private Integer cod_rol;
	private String des_rol;
	private String nom_modulo;
	private String des_sistema;
	private String fla_rol;
	private String det_menu;
	private Integer cod_rol_act;
	
	
	/**
	 * 
	 */
	public PerfilBean() {
		super();
	}

	/**
	 * @param fla_rol
	 */
	public PerfilBean(String fla_rol) {
		super();
		this.fla_rol = fla_rol;
	}
	
	/**
	 * @return the cod_rol
	 */
	public Integer getCod_rol() {
		return cod_rol;
	}
	/**
	 * @param cod_rol the cod_rol to set
	 */
	public void setCod_rol(Integer cod_rol) {
		this.cod_rol = cod_rol;
	}
	/**
	 * @return the des_rol
	 */
	public String getDes_rol() {
		return des_rol;
	}
	/**
	 * @param des_rol the des_rol to set
	 */
	public void setDes_rol(String des_rol) {
		this.des_rol = des_rol;
	}
	/**
	 * @return the nom_modulo
	 */
	public String getNom_modulo() {
		return nom_modulo;
	}
	/**
	 * @param nom_modulo the nom_modulo to set
	 */
	public void setNom_modulo(String nom_modulo) {
		this.nom_modulo = nom_modulo;
	}
	/**
	 * @return the des_sistema
	 */
	public String getDes_sistema() {
		return des_sistema;
	}
	/**
	 * @param des_sistema the des_sistema to set
	 */
	public void setDes_sistema(String des_sistema) {
		this.des_sistema = des_sistema;
	}
	/**
	 * @return the fla_rol
	 */
	public String getFla_rol() {
		return fla_rol;
	}
	/**
	 * @param fla_rol the fla_rol to set
	 */
	public void setFla_rol(String fla_rol) {
		this.fla_rol = fla_rol;
	}
	/**
	 * @return the det_menu
	 */
	public String getDet_menu() {
		return det_menu;
	}
	/**
	 * @param det_menu the det_menu to set
	 */
	public void setDet_menu(String det_menu) {
		this.det_menu = det_menu;
	}
	/**
	 * @return the cod_rol_act
	 */
	public Integer getCod_rol_act() {
		return cod_rol_act;
	}
	/**
	 * @param cod_rol_act the cod_rol_act to set
	 */
	public void setCod_rol_act(Integer cod_rol_act) {
		this.cod_rol_act = cod_rol_act;
	}
	
}
