package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;

/**
 * @className: FamiliaProductoMapper.java
 * @description: 
 * @date: 23 de ago. de 2017
 * @author: ARCHY.
 */
public class FamiliaProductoMapper implements RowMapper<ItemBean> {
	

	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean itemBean = new ItemBean();

		itemBean.setVcodigo(rs.getString("FAMILIA_BIEN"));
		itemBean.setDescripcion(rs.getString("NOMBRE_FAM"));

		return itemBean;
	}

}
