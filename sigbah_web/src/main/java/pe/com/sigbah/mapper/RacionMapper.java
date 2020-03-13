package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.RacionBean;


/**
 * @className: racionMapper.java
 * @description: 
 * @date: 19 jul. 2017
 * @author: whr.
 */
public class RacionMapper implements RowMapper<RacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RacionBean racion = new RacionBean();
		
		racion.setCodAnio(rs.getString("COD_ANIO"));
		racion.setNombreMesRacion(rs.getString("NOM_MES"));
		racion.setCodRacion(rs.getString("COD_RACION"));
		racion.setFechaRacion(rs.getString("FECHA_RACION"));
		racion.setTipoRacion(rs.getString("TIPO_RACION"));
		racion.setNombreRacion(rs.getString("NOMBRE_RACION"));
		racion.setDiasAtencion(rs.getString("DIAS_ATENCION"));		
		racion.setIdDdi(rs.getInt("IDE_DDI"));
		racion.setIdRacionOpe(rs.getInt("IDE_RAC_OPERATIVA"));
		
       return racion;
	}

}
