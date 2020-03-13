package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.util.Constantes;


/**
 * @className: FenomenoMapper.java
 * @description: 
 * @date: 13 jul. 2017
 * @author: whr.
 */
public class FenomenoMapper implements RowMapper<ItemBean> {
	
	private boolean all_records = false; // Obtener todos los registros

	/**
	 * @param parametro
	 */
	public FenomenoMapper(Integer parametro) {
		if (parametro.equals(Constantes.ZERO_INT)) {
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
			itemBean.setIcodigo(rs.getInt("IDE_FENOMENO"));
			itemBean.setDescripcion(rs.getString("DESC_FENOMENO"));
		} else {	
			itemBean.setIcodigo(rs.getInt("IDE_FENOMENO"));
			itemBean.setDescripcion(rs.getString("DESC_FENOMENO"));
		}
		return itemBean;
		
	}

}
