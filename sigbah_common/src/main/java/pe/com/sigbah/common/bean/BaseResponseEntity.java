package pe.com.sigbah.common.bean;

import java.io.Serializable;

/**
 * @className: BaseResponseEntity.java
 * @description: Clase base para la respuesta generica.
 * @date: 28/07/2018
 * @author: Sumerio.
 */
public class BaseResponseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Object data;

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
