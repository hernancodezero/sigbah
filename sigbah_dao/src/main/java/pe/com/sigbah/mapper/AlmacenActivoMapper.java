package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ControlCalidadBean;

/**
 * @className: AlmacenActivoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class AlmacenActivoMapper implements RowMapper<ControlCalidadBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ControlCalidadBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ControlCalidadBean controlCalidad = new ControlCalidadBean();
		controlCalidad.setCodigoAnio(rs.getString("COD_ANIO"));
		controlCalidad.setCodigoMes(rs.getString("COD_MES"));
		controlCalidad.setIdAlmacen(rs.getInt("IDE_ALMACEN"));
		controlCalidad.setCodigoAlmacen(StringUtils.trimToEmpty(rs.getString("COD_ALMACEN")));
		controlCalidad.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		controlCalidad.setNombreMes(rs.getString("NOMBRE_MES"));
		return controlCalidad;
	}

}
