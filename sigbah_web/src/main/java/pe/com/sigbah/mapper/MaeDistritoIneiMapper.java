package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeDistritoIneiBean;

/**
 * @className: MaeCategoriaMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeDistritoIneiMapper implements RowMapper<MaeDistritoIneiBean> {
	

	@Override
	public MaeDistritoIneiBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeDistritoIneiBean maeDistritoIneiBean = new MaeDistritoIneiBean();

		maeDistritoIneiBean.setCodAnio(rs.getString("COD_ANIO"));
		maeDistritoIneiBean.setCodDistrito(rs.getString("COD_DISTRITO"));
		maeDistritoIneiBean.setDesDepartamento(rs.getString("DES_DEPARTAMENTO"));
		maeDistritoIneiBean.setDesProvincia(rs.getString("DES_PROVINCIA"));
		maeDistritoIneiBean.setDesDistrito(rs.getString("DES_DISTRITO"));
		maeDistritoIneiBean.setPoblacion(rs.getInt("POBLACION"));
		

		
		return maeDistritoIneiBean;
	}

}
