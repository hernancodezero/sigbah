package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: SalidaIngresoMapper.java
 * @description: 
 * @date: 26 de jul. de 2017
 * @author: ARCHY.
 */
public class SalidaIngresoMapper implements RowMapper<ItemBean> {

	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean itemBean = new ItemBean();
	
		itemBean.setIcodigo(rs.getInt("IDE_SALIDA"));
		itemBean.setDescripcion(rs.getString("NRO_ORDEN_SALIDA"));

		return itemBean;
	}

}
