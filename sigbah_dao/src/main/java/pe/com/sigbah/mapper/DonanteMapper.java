package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;


/**
 * @className: DonanteMapper.java
 * @description: 
 * @date: 15 de jul. de 2017
 * @author: ARCHY.
 */
public class DonanteMapper implements RowMapper<ItemBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean donaciones = new ItemBean();
		donaciones.setIcodigo(rs.getInt("IDE_DONANTE"));
		donaciones.setDescripcionCorta(rs.getString("NOM_DONANTE"));
		donaciones.setDescripcion(rs.getString("REPRESENTANTE"));
		return donaciones;
	}

}
