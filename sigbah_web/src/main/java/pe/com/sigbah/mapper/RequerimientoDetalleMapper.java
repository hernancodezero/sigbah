package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.EmergenciaBean;


/**
 * @className: RequerimientoDetalleMapper.java
 * @description: 
 * @date: 19 jul. 2017
 * @author: whr.
 */
public class RequerimientoDetalleMapper implements RowMapper<EmergenciaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EmergenciaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmergenciaBean emergencia = new EmergenciaBean();
		
		emergencia.setFkIdRequerimiento(rs.getInt("IDE_REQUERIMIENTO"));
		emergencia.setFkIdRequerimientoDamni(rs.getInt("IDE_REQ_DAMNIFICADO"));
		emergencia.setCodDepartamento(rs.getString("COD_DEPARTAMENTO"));
		emergencia.setDesDepartamento(rs.getString("DES_DEPARTAMENTO"));
		emergencia.setCodProvincia(rs.getString("COD_PROVINCIA"));
		emergencia.setDesProvincia(rs.getString("DES_PROVINCIA"));
		emergencia.setCodDistrito(rs.getString("COD_DISTRITO"));
		emergencia.setDesDistrito(rs.getString("DES_DISTRITO"));
		emergencia.setIdEmergencia(rs.getInt("COD_SINPAD"));
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
