package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ControlCalidadBean;
import pe.com.sigbah.common.bean.RacionBean;

/**
 * @className: RegistroControlCalidadMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class RegistroRacionMapper implements RowMapper<RacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RacionBean racion = new RacionBean();
		racion.setIdDdi(rs.getInt("IDE_DDI"));
		racion.setIdRacionOpe(rs.getInt("IDE_RAC_OPERATIVA"));
		racion.setTipoRacion(rs.getString("TIP_RACION"));
		racion.setCodRacion(rs.getString("COD_RACION"));
		racion.setNombreRacion(rs.getString("NOMBRE_RACION"));
		racion.setDiasAtencion(rs.getString("DIAS_ATENCION"));
		racion.setFechaRacion(rs.getString("FECHA"));
		return racion;
		
	}

}
