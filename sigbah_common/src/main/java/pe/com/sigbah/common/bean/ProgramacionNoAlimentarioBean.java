package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @className: ProgramacionNoAlimentarioBean.java
 * @description: 
 * @date: 29/07/2017
 * @author: Junior Huaman Flores.
 */
public class ProgramacionNoAlimentarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idProgramacionUbigeo;
	private String codigoAnio;
	private Integer idDdi;
	private Integer idProgramacion;
	private String codigoDistrito;
	private String departamento;
	private String provincia;
	private String distrito;	
	private BigDecimal famAfect;
	private BigDecimal famDam;
	private BigDecimal totalFam;
	private BigDecimal persAfect;
	private BigDecimal persDam;
	private BigDecimal totalPers;	
	private BigDecimal totalTm;
	private List<ProductoAlimentoBean> listaProducto;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private String usuarioRegistro;
	
	
	/**
	 * 
	 */
	public ProgramacionNoAlimentarioBean() {
		super();
	}

	/**
	 * @param idProgramacionUbigeo
	 */
	public ProgramacionNoAlimentarioBean(Integer idProgramacionUbigeo) {
		super();
		this.idProgramacionUbigeo = idProgramacionUbigeo;
	}
	
	/**
	 * @return the idProgramacionUbigeo
	 */
	public Integer getIdProgramacionUbigeo() {
		return idProgramacionUbigeo;
	}
	/**
	 * @param idProgramacionUbigeo the idProgramacionUbigeo to set
	 */
	public void setIdProgramacionUbigeo(Integer idProgramacionUbigeo) {
		this.idProgramacionUbigeo = idProgramacionUbigeo;
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
	 * @return the codigoDistrito
	 */
	public String getCodigoDistrito() {
		return codigoDistrito;
	}
	/**
	 * @param codigoDistrito the codigoDistrito to set
	 */
	public void setCodigoDistrito(String codigoDistrito) {
		this.codigoDistrito = codigoDistrito;
	}
	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}
	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 * @return the distrito
	 */
	public String getDistrito() {
		return distrito;
	}
	/**
	 * @param distrito the distrito to set
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	/**
	 * @return the persAfect
	 */
	public BigDecimal getPersAfect() {
		return persAfect;
	}
	/**
	 * @param persAfect the persAfect to set
	 */
	public void setPersAfect(BigDecimal persAfect) {
		this.persAfect = persAfect;
	}
	/**
	 * @return the persDam
	 */
	public BigDecimal getPersDam() {
		return persDam;
	}
	/**
	 * @param persDam the persDam to set
	 */
	public void setPersDam(BigDecimal persDam) {
		this.persDam = persDam;
	}
	/**
	 * @return the totalPers
	 */
	public BigDecimal getTotalPers() {
		return totalPers;
	}
	/**
	 * @param totalPers the totalPers to set
	 */
	public void setTotalPers(BigDecimal totalPers) {
		this.totalPers = totalPers;
	}
	/**
	 * @return the famAfect
	 */
	public BigDecimal getFamAfect() {
		return famAfect;
	}
	/**
	 * @param famAfect the famAfect to set
	 */
	public void setFamAfect(BigDecimal famAfect) {
		this.famAfect = famAfect;
	}
	/**
	 * @return the famDam
	 */
	public BigDecimal getFamDam() {
		return famDam;
	}
	/**
	 * @param famDam the famDam to set
	 */
	public void setFamDam(BigDecimal famDam) {
		this.famDam = famDam;
	}
	/**
	 * @return the totalFam
	 */
	public BigDecimal getTotalFam() {
		return totalFam;
	}
	/**
	 * @param totalFam the totalFam to set
	 */
	public void setTotalFam(BigDecimal totalFam) {
		this.totalFam = totalFam;
	}
	/**
	 * @return the totalTm
	 */
	public BigDecimal getTotalTm() {
		return totalTm;
	}
	/**
	 * @param totalTm the totalTm to set
	 */
	public void setTotalTm(BigDecimal totalTm) {
		this.totalTm = totalTm;
	}
	/**
	 * @return the listaProducto
	 */
	public List<ProductoAlimentoBean> getListaProducto() {
		return listaProducto;
	}
	/**
	 * @param listaProducto the listaProducto to set
	 */
	public void setListaProducto(List<ProductoAlimentoBean> listaProducto) {
		this.listaProducto = listaProducto;
	}

	/**
	 * @return the codigoRespuesta
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	/**
	 * @param codigoRespuesta the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	/**
	 * @return the mensajeRespuesta
	 */
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	/**
	 * @param mensajeRespuesta the mensajeRespuesta to set
	 */
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	/**
	 * @return the usuarioRegistro
	 */
	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	/**
	 * @param usuarioRegistro the usuarioRegistro to set
	 */
	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
		
}
