package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: MonedaMapper.java
 * @description: 
 * @date: 15 de jul. de 2017
 * @author: ARCHY.
 */
public class MonedaMapper implements RowMapper<ItemBean> {
	
	private boolean all_records = false; // Obtener todos los registros

	/**
	 * @param parametro
	 */
	public MonedaMapper(Integer parametro) {
		if (parametro==0) {
			all_records = true;
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean itemBean = new ItemBean();
		if (all_records) {
			itemBean.setIcodigo(rs.getInt("IDE_MONEDA"));
			itemBean.setDescripcion(rs.getString("NOMBRE"));
		} else {	
			itemBean.setIcodigo(rs.getInt("IDE_MONEDA"));
			itemBean.setDescripcion(rs.getString("NOMBRE"));
		}
		return itemBean;
	}

}
