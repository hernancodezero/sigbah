package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProgramacionBean;

/**
 * @className: RegistroProgramacionMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class RegistroProgramacionMapper implements RowMapper<ProgramacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProgramacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProgramacionBean programacion = new ProgramacionBean();
		programacion.setIdProgramacion(rs.getInt("IDE_PROGRAMACION"));
		programacion.setCodigoAnio(rs.getString("COD_ANIO"));
		programacion.setCodigoMes(rs.getString("COD_MES"));
		programacion.setIdRequerimiento(rs.getInt("IDE_REQUERIMIENTO"));
		programacion.setNombreRequerimiento(rs.getString("NOM_REQUERIMIENTO"));
		programacion.setFechaProgramacion(rs.getString("FEC_PROGRAMACION"));
		programacion.setIdDdi(rs.getInt("IDE_DDI"));
		programacion.setIdRegion(rs.getInt("IDE_REGION"));
		programacion.setCodigoDdi(rs.getString("COD_DDI"));		
		programacion.setCodigoProgramacion(rs.getString("COD_PROGRAMACION"));
		programacion.setNroProgramacion(rs.getString("NRO_PROGRAMACION"));
		programacion.setIdRacion(rs.getInt("IDE_RAC_OPERATIVA"));		
		programacion.setNombreRacion(rs.getString("NOMBRE_RACION"));
		programacion.setNombreProgramacion(rs.getString("NOM_PROGRAMACION"));
		programacion.setIdNroDee(rs.getInt("FK_IDE_DEE"));
		programacion.setNombreDeclarion(rs.getString("NOM_DECLARATORIA"));
		programacion.setTipoAtencion(rs.getInt("TIP_ATENCION"));
		programacion.setObservacion(rs.getString("OBSERVACION"));
		programacion.setIdEstado(rs.getInt("IDE_ESTADO"));
		programacion.setNombreEstado(rs.getString("NOM_ESTADO"));
		return programacion;
	}

}
