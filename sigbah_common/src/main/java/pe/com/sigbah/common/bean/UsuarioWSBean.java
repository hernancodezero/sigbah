package pe.com.sigbah.common.bean;

import java.io.Serializable;

/**
 * @className: UsuarioWSBean.java
 * @description: 
 * @date: 14 de ago. de 2018
 * @author: alan Chumpitaz.
 */
public class UsuarioWSBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String usuario;
	private String password;
	private String email;

	
	/**
	 * 
	 */
	public UsuarioWSBean() {
		super();
	}

	/**
	 * @param usuario
	 * @param password
	 */
	public UsuarioWSBean(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
}
