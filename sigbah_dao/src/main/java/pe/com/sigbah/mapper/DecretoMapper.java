package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DeeBean;


/**
 * @className: racionMapper.java
 * @description: 
 * @date: 19 jul. 2017
 * @author: whr.
 */
public class DecretoMapper implements RowMapper<DeeBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DeeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeeBean dee = new DeeBean();
		
		dee.setIdDee(rs.getInt("IDE_DEE"));
		dee.setCodAnio(rs.getString("ANIO"));
		dee.setNomMes(rs.getString("MES"));
		dee.setFechaIni(rs.getString("FECHA_INICIO"));
		dee.setFechaFin(rs.getString("FECHA_FIN"));
		dee.setNumDias(rs.getString("NRO_DIAS"));
		dee.setNumDee(rs.getString("NRO_DEE"));
		dee.setNomDee(rs.getString("NOMBRE_DEE"));
		dee.setNomEstado(rs.getString("ESTDO"));	
		dee.setNombreArchivo(rs.getString("NOM_ARCHIVO"));	
		dee.setCodigoArchivoAlfresco(rs.getString("COD_ALFRESCO"));	
		
		
        
       return dee;
	}

}
