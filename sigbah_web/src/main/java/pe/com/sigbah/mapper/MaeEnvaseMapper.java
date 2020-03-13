package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeEnvaseBean;

/**
 * @className: MaeCategoriaMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeEnvaseMapper implements RowMapper<MaeEnvaseBean> {
	

	@Override
	public MaeEnvaseBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeEnvaseBean maeEnvaseBean = new MaeEnvaseBean();

		maeEnvaseBean.setIdEnvase(rs.getInt("IDE_ENVASE"));
		maeEnvaseBean.setNombreEnvase(rs.getString("NOM_ENVASE"));
		maeEnvaseBean.setDescripcion(rs.getString("DESC_CORTA"));

		return maeEnvaseBean;
	}

}
