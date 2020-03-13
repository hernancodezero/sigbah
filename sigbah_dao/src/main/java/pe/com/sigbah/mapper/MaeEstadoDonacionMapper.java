package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeEstadoBean;
import pe.com.sigbah.common.bean.MaeEstadoDonacionBean;

/**
 * @className: MaeEstadoDonacionMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeEstadoDonacionMapper implements RowMapper<MaeEstadoDonacionBean> {
	

	@Override
	public MaeEstadoDonacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeEstadoDonacionBean maeEstadoDonacionBean = new MaeEstadoDonacionBean();

		maeEstadoDonacionBean.setIdEstado(rs.getInt("IDE_ESTADO"));
		maeEstadoDonacionBean.setNombreEstado(rs.getString("NOM_ESTADO"));

		
		return maeEstadoDonacionBean;
	}

}
