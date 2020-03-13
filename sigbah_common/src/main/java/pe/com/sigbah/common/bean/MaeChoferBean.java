package pe.com.sigbah.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @className: MaeCategoriaBean.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeChoferBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idChofer;
	private String nombreChofer;
	private String nroDni;
	private String nroBrevete;
	private String nombreEmpresa;
	private Integer idEmpresaTransporte;

	private String usuario;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private String fecha;
	private String flagActivo;;
	
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	 * @return the idChofer
	 */
	public Integer getIdChofer() {
		return idChofer;
	}
	/**
	 * @param idChofer the idChofer to set
	 */
	public void setIdChofer(Integer idChofer) {
		this.idChofer = idChofer;
	}
	/**
	 * @return the nombreChofer
	 */
	public String getNombreChofer() {
		return nombreChofer;
	}
	/**
	 * @param nombreChofer the nombreChofer to set
	 */
	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}
	/**
	 * @return the nroDni
	 */
	public String getNroDni() {
		return nroDni;
	}
	/**
	 * @param nroDni the nroDni to set
	 */
	public void setNroDni(String nroDni) {
		this.nroDni = nroDni;
	}
	/**
	 * @return the nroBrevete
	 */
	public String getNroBrevete() {
		return nroBrevete;
	}
	/**
	 * @param nroBrevete the nroBrevete to set
	 */
	public void setNroBrevete(String nroBrevete) {
		this.nroBrevete = nroBrevete;
	}
	/**
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	/**
	 * @param nombreEmpresa the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	/**
	 * @return the idEmpresaTransporte
	 */
	public Integer getIdEmpresaTransporte() {
		return idEmpresaTransporte;
	}
	/**
	 * @param idEmpresaTransporte the idEmpresaTransporte to set
	 */
	public void setIdEmpresaTransporte(Integer idEmpresaTransporte) {
		this.idEmpresaTransporte = idEmpresaTransporte;
	}
	/**
	 * @return the flagActivo
	 */
	public String getFlagActivo() {
		return flagActivo;
	}
	/**
	 * @param flagActivo the flagActivo to set
	 */
	public void setFlagActivo(String flagActivo) {
		this.flagActivo = flagActivo;
	}

	

}
