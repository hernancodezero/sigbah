package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeEstadoBean;
import pe.com.sigbah.common.bean.MaeEstadoProgramacionBean;

/**
 * @className: MaeEstadoProgramacionMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeEstadoProgramacionMapper implements RowMapper<MaeEstadoProgramacionBean> {
	

	@Override
	public MaeEstadoProgramacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeEstadoProgramacionBean maeEstadoProgramacionBean = new MaeEstadoProgramacionBean();

		maeEstadoProgramacionBean.setIdEstado(rs.getInt("IDE_ESTADO_PROGRAMACION"));
		maeEstadoProgramacionBean.setNombreEstado(rs.getString("NOM_ESTADO_PROGRAMACION"));

		
		return maeEstadoProgramacionBean;
	}

}
