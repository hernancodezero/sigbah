package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.RequerimientoBean;

/**
 * @className: ProgramacionRequerimientoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProgramacionRequerimientoMapper implements RowMapper<RequerimientoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RequerimientoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RequerimientoBean requerimiento = new RequerimientoBean();
		requerimiento.setIdRequerimiento(rs.getInt("IDE_REQUERIMIENTO"));
		requerimiento.setNomRequerimiento(rs.getString("NOM_REQUERIMIENTO"));
		requerimiento.setCodRequerimiento(rs.getString("CODIGO_REQUERIMIENTO"));
		return requerimiento;
	}

}
