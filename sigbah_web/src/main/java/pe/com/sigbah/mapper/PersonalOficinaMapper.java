package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: PersonalOficinaMapper.java
 * @description: 
 * @date: 16 de jul. de 2017
 * @author: SUMERIO.
 */
public class PersonalOficinaMapper implements RowMapper<ItemBean> {

	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean itemBean = new ItemBean();

		itemBean.setIcodigo(rs.getInt("IDE_PERSONAL"));
		itemBean.setDescripcion(rs.getString("NOMBRE_PERSONAL"));
		return itemBean;
	}

}
