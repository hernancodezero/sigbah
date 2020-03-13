package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ConsultaProgramacionBean;
import pe.com.sigbah.common.bean.RequerimientoBean;


/**
 * @className: RequerimientoMapper.java
 * @description: 
 * @date: 19 jul. 2017
 * @author: whr.
 */
public class ConsultaProgramacionMapper implements RowMapper<ConsultaProgramacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ConsultaProgramacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConsultaProgramacionBean consulta = new ConsultaProgramacionBean();
		
		consulta.setIdProgramacion(rs.getInt("IDE_PROGRAMACION"));	
		consulta.setCodigoAnio(rs.getString("COD_ANIO"));
		consulta.setNombreMes(rs.getString("MES"));	
		consulta.setNroDee(rs.getString("NRO_DEE"));
		consulta.setNroProgramacion(rs.getString("NRO_PROGRAMACION"));
		
		consulta.setFechaProgramacion(rs.getString("FECHA_PROGRAMACION"));		
		consulta.setNombreProgramacion(rs.getString("NOM_PROGRAMACION"));
		consulta.setNombreRegion(rs.getString("NOM_REGION"));
		consulta.setTotalAlimento(rs.getDouble("TOTAL_TONELADAS_ALIMENTOS"));
		consulta.setTotalBna(rs.getDouble("TOTAL_TONELADAS_BNA"));
		consulta.setNombreEstado(rs.getString("NOM_ESTADO"));
		
		
       return consulta;
	}

}
