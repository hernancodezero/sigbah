package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.CabeceraEmergenciaBean;


/**
 * @className: EmergenciaMapper.java
 * @description: 
 * @date: 15 jul. 2017
 * @author: whr.
 */
public class RegistroCabeceraEmergenciaMapper implements RowMapper<CabeceraEmergenciaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public CabeceraEmergenciaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		CabeceraEmergenciaBean emergencia = new CabeceraEmergenciaBean();
		emergencia.setIdEmergencia(rs.getInt("IDE_EMERGENCIA"));
		emergencia.setNombreEmergencia(rs.getString("NOMBRE_EMERGENCIA"));
		emergencia.setFechaEmergencia(rs.getString("FEC_EMERGENCIA"));
		emergencia.setDescFenomeno(rs.getString("DESC_FENOMENO"));		
		emergencia.setUbigeo(rs.getString("UBIGEO"));
		
       return emergencia;
	}

}
