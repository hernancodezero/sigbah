package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.RacionOperativaBean;

/**
 * @className: RacionOperativaMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class RacionOperativaMapper implements RowMapper<RacionOperativaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RacionOperativaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RacionOperativaBean racion = new RacionOperativaBean();
		racion.setIdRacionOperativa(rs.getInt("IDE_RAC_OPERATIVA"));
		racion.setCodigoRacion(rs.getString("COD_RACION"));
		racion.setNombreRacion(rs.getString("NOMBRE_RACION"));
		racion.setCodigoRacionOperativa(rs.getString("CODIGO_RACION"));
		return racion;
	}

}
