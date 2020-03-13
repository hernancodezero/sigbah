package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: TablasGeneralesMapper.java
 * @description: 
 * @date: 11 de ago. de 2017
 * @author: ARCHY.
 */
public class TablasGeneralesMapper implements RowMapper<ItemBean> {


	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean itemBean = new ItemBean();

		itemBean.setIcodigo(rs.getInt("IDE_TABLA"));
		itemBean.setDescripcion(rs.getString("NOMBRE_PROPIO"));

		return itemBean;
	}

}
