package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProgramacionBean;

/**
 * @className: ProgramacionMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProgramacionMapper implements RowMapper<ProgramacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProgramacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProgramacionBean programacion = new ProgramacionBean();
		programacion.setIdProgramacion(rs.getInt("IDE_PROGRAMACION"));
		programacion.setCodigoAnio(rs.getString("COD_ANIO"));
		programacion.setCodigoMes(rs.getString("COD_MES"));
		programacion.setNombreMes(rs.getString("MES"));		
		programacion.setNroProgramacion(rs.getString("NRO_PROGRAMACION"));
		programacion.setFechaProgramacion(rs.getString("FECHA_PROGRAMACION"));
		programacion.setNombreProgramacion(rs.getString("NOM_PROGRAMACION"));		
		programacion.setNombreFenomeno(rs.getString("DESC_FENOMENO"));
		programacion.setNroDee(rs.getString("NRO_DEE"));
		programacion.setNombreEstado(rs.getString("NOM_ESTADO"));
		programacion.setNombreRegion(rs.getString("NOM_REGION"));
		programacion.setIdEstado(rs.getInt("IDE_ESTADO"));
		return programacion;
	}

}
