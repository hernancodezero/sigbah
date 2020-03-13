package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;

/**
 * @className: CorreosDestinatariosMapper.java
 * @description: 
 * @date: 27 de set. de 2017
 * @author: ARCHY.
 */
public class HijosXMenuMapper implements RowMapper<ItemBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean item = new ItemBean();
		item.setIcodigo(rs.getInt("ID_MENU"));
		item.setDescripcion(rs.getString("NOMBRE_MENU"));
		item.setIcodigoParam2(rs.getInt("ID_PADRE"));
		item.setDescripcionCorta(rs.getString("NRO_ORDEN"));		

		return item;
	}

}
