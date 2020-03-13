package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ItemBean;
import pe.com.sigbah.common.util.Constantes;

/**
 * @className: DeeMapper.java
 * @description: 
 * @date: 15 de jul. de 2017
 * @author: ARCHY.
 */
public class TransporteSalidaMapper implements RowMapper<ItemBean> {
	
	@Override
	public ItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ItemBean itemBean = new ItemBean();

		itemBean.setIcodigo(rs.getInt("FK_IDE_MED_TRANSPORTE"));
		itemBean.setIcodigoParam2(rs.getInt("FK_IDE_EMP_TRANSPORTE"));
		itemBean.setDescripcion(rs.getString("NRO_PLACA"));
		itemBean.setIcodigoParam3(rs.getInt("IDE_CHOFER"));
		
		return itemBean;
	}

}
