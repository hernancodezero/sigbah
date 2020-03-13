package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;

/**
 * @className: PersonalExternoLocalMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class PersonalExternoLocalMapper implements RowMapper<ItemBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.coreO.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean itemBean = new ItemBean();
		itemBean.setIcodigo(rs.getInt("IDE_PERSONAL_EXT"));
		itemBean.setDescripcion(rs.getString("NOMBRE_PERSONAL_EXTERNO"));
		return itemBean;
	}

}
