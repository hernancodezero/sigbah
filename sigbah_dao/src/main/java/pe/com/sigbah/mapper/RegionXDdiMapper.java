package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: RegionXDdiMapper.java
 * @description: 
 * @date: 17 de ago. de 2017
 * @author: Archy.
 */
public class RegionXDdiMapper implements RowMapper<ItemBean> {
	
	private boolean all_records = false; // Obtener todos los registros


	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean itemBean = new ItemBean();

		itemBean.setIcodigo(rs.getInt("ID_REGION"));
		itemBean.setDescripcion(rs.getString("NOM_REGION"));

		return itemBean;
	}

}
