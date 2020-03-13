package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.MaeUnidadMedidaBean;

/**
 * @className: MaeUnidadMedidaMapper.java
 * @description: 
 * @date: 16 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeUnidadMedidaMapper implements RowMapper<MaeUnidadMedidaBean> {
	

	@Override
	public MaeUnidadMedidaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeUnidadMedidaBean maeUnidadMedidaBean = new MaeUnidadMedidaBean();

		maeUnidadMedidaBean.setIdUnidad(rs.getInt("IDE_UNID_MEDIDA"));
		maeUnidadMedidaBean.setNombreUnidad(rs.getString("NOM_UNIDAD"));
		maeUnidadMedidaBean.setNombreCorto(rs.getString("NOM_CORTO"));
		maeUnidadMedidaBean.setFlagActivo(rs.getString("ESTADO"));

		return maeUnidadMedidaBean;
	}

}
