package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import pe.com.sigbah.common.bean.MaeAniosBean;
import pe.com.sigbah.common.bean.MaeCategoriaBean;
import pe.com.sigbah.common.bean.MaeEstadoBean;
import pe.com.sigbah.common.bean.MaeEstadoProgramacionBean;
import pe.com.sigbah.common.bean.MaeModAlmacenBean;
import pe.com.sigbah.common.bean.MaeMonedaBean;

/**
 * @className: MaeModAlmacenMapper.java
 * @description: 
 * @date: 15 de ago. de 2017
 * @author: ARCHY.
 */
public class MaeMonedaMapper implements RowMapper<MaeMonedaBean> {
	

	@Override
	public MaeMonedaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaeMonedaBean maeMonedaBean = new MaeMonedaBean();

		maeMonedaBean.setIdMoneda(rs.getInt("IDE_MONEDA"));
		maeMonedaBean.setCodMoneda(rs.getString("COD_MONEDA"));
		maeMonedaBean.setNombreMoneda(rs.getString("NOMBRE"));
		maeMonedaBean.setFlagActivo(rs.getString("ESTADO"));
		return maeMonedaBean;
	}

}
