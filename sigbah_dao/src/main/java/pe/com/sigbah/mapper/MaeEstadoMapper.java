package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeEstadoBean;

/**
 * @className: MaeEstadoMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeEstadoMapper implements RowMapper<MaeEstadoBean> {
	

	@Override
	public MaeEstadoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeEstadoBean maeEstadoBean = new MaeEstadoBean();

		maeEstadoBean.setIdEstado(rs.getInt("IDE_ESTADO"));
		maeEstadoBean.setNombreEstado(rs.getString("NOM_ESTADO"));

		
		return maeEstadoBean;
	}

}
