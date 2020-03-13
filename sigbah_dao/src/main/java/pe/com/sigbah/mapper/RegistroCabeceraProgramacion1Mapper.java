package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProgramacionBean;

/**
 * @className: RegistroCabeceraProgramacionMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class RegistroCabeceraProgramacion1Mapper implements RowMapper<ProgramacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProgramacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProgramacionBean programacion = new ProgramacionBean();
		programacion.setNroProgramacion(rs.getString("NRO_PROGRAMACION"));
		programacion.setFechaProgramacion(rs.getString("FECHA"));
		programacion.setNombreProgramacion(rs.getString("NOMBRE"));
		programacion.setNroDee(rs.getString("NRO_DEE"));
		programacion.setNombreRegion(rs.getString("REGION_DESTINO"));
		programacion.setTipoAtencion(rs.getInt("TIP_ATENCION"));
		programacion.setIdRacion(rs.getInt("IDE_RAC_OPERATIVA"));
		programacion.setCodRequerimiento(rs.getString("COD_REQUERIMIENTO"));
		programacion.setCodRacion(rs.getString("COD_RACION"));
		return programacion;
	}

}
