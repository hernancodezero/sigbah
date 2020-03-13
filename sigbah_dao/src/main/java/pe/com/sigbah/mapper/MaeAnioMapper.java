package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;

/**
 * @className: MaeAnioMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeAnioMapper implements RowMapper<MaeAniosBean> {
	

	@Override
	public MaeAniosBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeAniosBean maeAlmacenBean = new MaeAniosBean();

		maeAlmacenBean.setCodAnio(rs.getString("COD_ANIO"));
		maeAlmacenBean.setDescripcion(rs.getString("DESCRIPCION"));

		
		return maeAlmacenBean;
	}

}
