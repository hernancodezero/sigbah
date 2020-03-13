package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;

/**
 * @className: MaeCategoriaMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeCategoriaMapper implements RowMapper<MaeCategoriaBean> {
	

	@Override
	public MaeCategoriaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeCategoriaBean maeAlmacenBean = new MaeCategoriaBean();

		maeAlmacenBean.setIdCategoria(rs.getInt("ID"));
		maeAlmacenBean.setNombreCategoria(rs.getString("NOMBRE_CATEGORIA"));

		
		return maeAlmacenBean;
	}

}
