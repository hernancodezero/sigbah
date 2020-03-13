package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ControlCalidadBean;

/**
 * @className: ControlCalidadDonIngresoMapper.java
 * @description: 
 * @date: 20 de jul. de 2017
 * @author: ARCHY.
 */
public class ControlCalidadDonIngresoMapper implements RowMapper<ControlCalidadBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ControlCalidadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ControlCalidadBean controlCalidad = new ControlCalidadBean();
		controlCalidad.setIdControlCalidad(rs.getInt("IDE_CONTROL_CALIDAD"));
		controlCalidad.setNroControlCalidad(rs.getString("NRO_CONTROL_CALIDAD"));
		return controlCalidad;
	}

}
