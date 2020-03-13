package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: EstadosXMapper.java
 * @description: 
 * @date: 17 de jul. de 2017
 * @author: ARCHY.
 */
public class EstadosXDonacionMapper implements RowMapper<ItemBean> {
	
	
	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean itemBean = new ItemBean();
		itemBean.setIcodigo(rs.getInt("IDE_ESTADO"));
		itemBean.setVcodigoParam2(rs.getString("NOM_ESTADO"));
		itemBean.setVcodigoParam3(rs.getString("FECHA"));
		itemBean.setVcodigoParam4(rs.getString("NOMBRE_USUARIO"));

		return itemBean;
	}

}
