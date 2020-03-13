package pe.com.sigbah.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;

/**
 * @className: SpringUtil.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class SpringUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @param hashMap
	 * @return Object[]
	 */
	public static Object[] getHashMapObjectsArray(LinkedHashMap<String, SqlParameter> hashMap) {
		SqlParameter[] arreglo_respuesta = null;
		if (hashMap != null) {
			arreglo_respuesta = new SqlParameter[hashMap.size()];
			List<String> keys = new ArrayList<String>(hashMap.keySet());
			for (int i = 0; i < keys.size(); i++) {
				String key = keys.get(i) + Constantes.EMPTY;
				arreglo_respuesta[i] = hashMap.get(key);
			}
		} else {
			arreglo_respuesta = new SqlParameter[0];
		}

		return arreglo_respuesta;
	}

}
