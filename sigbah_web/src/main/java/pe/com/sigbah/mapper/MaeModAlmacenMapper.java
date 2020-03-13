package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeEstadoBean;
import pe.com.sigbah.common.bean.MaeEstadoProgramacionBean;
import pe.com.sigbah.common.bean.MaeModAlmacenBean;

/**
 * @className: MaeModAlmacenMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeModAlmacenMapper implements RowMapper<MaeModAlmacenBean> {
	

	@Override
	public MaeModAlmacenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeModAlmacenBean maeModAlmacenBean = new MaeModAlmacenBean();

		maeModAlmacenBean.setIdModAlmacen(rs.getInt("IDE_MOD_ALMACEN"));
		maeModAlmacenBean.setNombreModalidad(rs.getString("NOM_MODALIDAD"));

		
		return maeModAlmacenBean;
	}

}
