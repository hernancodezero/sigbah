package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;

/**
 * @className: EstadosModuloMapper.java
 * @description: 
 * @date: 27 de set. de 2017
 * @author: ARCHY.
 */
public class EstadosModuloMapper implements RowMapper<ItemBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean itemBean = new ItemBean();
		itemBean.setIcodigo(rs.getInt("IDE_ESTADO"));
		itemBean.setDescripcion(rs.getString("NOM_ESTADO"));	

		return itemBean;
	}

}
