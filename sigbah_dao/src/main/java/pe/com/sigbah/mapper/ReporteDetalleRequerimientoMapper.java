package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.EmergenciaBean;


/**
 * @className: EmergenciaMapper.java
 * @description: 
 * @date: 15 jul. 2017
 * @author: whr.
 */
public class ReporteDetalleRequerimientoMapper implements RowMapper<EmergenciaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EmergenciaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmergenciaBean emergencia = new EmergenciaBean();
		
		emergencia.setDesDepartamento(rs.getString("DES_DEPARTAMENTO"));
		emergencia.setDesProvincia(rs.getString("DES_PROVINCIA"));
		emergencia.setDesDistrito(rs.getString("DES_DISTRITO"));
		emergencia.setCodSinpad(rs.getString("COD_SINPAD"));
		emergencia.setPoblacionINEI(rs.getInt("POBLACION_INEI"));
		emergencia.setFamAfectado(rs.getInt("NUM_FAM_AFECTADAS"));
		emergencia.setFamDamnificado(rs.getInt("NUM_FAM_DAMNIFICADAS"));
		emergencia.setTotalFam(rs.getInt("TOTAL_FAMILIAS"));
		emergencia.setPersoAfectado(rs.getInt("NUM_PER_AFECTADAS"));
		emergencia.setPersoDamnificado(rs.getInt("NUM_PER_DAMNIFICADAS"));
		emergencia.setTotalPerso(rs.getInt("TOTAL_PERSONAS"));
		emergencia.setFamAfectadoReal(rs.getInt("NUM_FAM_AFECTADAS_REAL"));
		emergencia.setFamDamnificadoReal(rs.getInt("NUM_FAM_DAMNIFICADAS_REAL"));
		emergencia.setTotalFamReal(rs.getInt("TOTAL_FAMILIAS_REAL"));
		emergencia.setPersoAfectadoReal(rs.getInt("NUM_PER_AFECTADAS_REAL"));
		emergencia.setPersoDamnificadoReal(rs.getInt("NUM_PER_DAMNIFICADAS_REAL"));
		emergencia.setTotalPersoReal(rs.getInt("TOTAL_PERSONAS_REAL"));
		 
       return emergencia;
	}

}
