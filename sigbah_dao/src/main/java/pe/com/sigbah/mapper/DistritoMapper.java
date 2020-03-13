package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.UbigeoBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: DepartamentoMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DistritoMapper implements RowMapper<UbigeoBean> {
	
	private boolean all_records = false; // Obtener todos los registros

	/**
	 * @param parametro
	 */
	public DistritoMapper(String parametro) {
		if (parametro.equals(Constantes.PORCENTAJE)) {
			all_records = true;
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public UbigeoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		UbigeoBean ubigeoBean = new UbigeoBean();
		if (all_records) {
			ubigeoBean.setCoddist(rs.getString("COD_DISTRITO"));
			ubigeoBean.setNombre(rs.getString("DES_DISTRITO"));
		} else {	
			ubigeoBean.setCoddist(rs.getString("COD_DISTRITO"));
			ubigeoBean.setNombre(rs.getString("DES_DISTRITO"));
		}
		return ubigeoBean;
	}

}
