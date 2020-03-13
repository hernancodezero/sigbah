package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProgramacionBean;

/**
 * @className: CabeceraConsultaProgramacionMapper.java
 * @description: 
 * @date: 10 de oct. de 2017
 * @author: ARCHY.
 */
public class CabeceraConsultaProgramacionMapper implements RowMapper<ProgramacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProgramacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProgramacionBean programacion = new ProgramacionBean();
		programacion.setNombreSistema(rs.getString("NOMBRE_SISTEMA"));
		programacion.setNroDee(rs.getString("NRO_DEE"));

		return programacion;
	}

}
