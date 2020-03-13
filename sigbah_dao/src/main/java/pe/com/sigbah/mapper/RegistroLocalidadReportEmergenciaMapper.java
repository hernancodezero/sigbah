package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.LocalidadEmergenciaBean;


/**
 * @className: EmergenciaMapper.java
 * @description: 
 * @date: 15 jul. 2017
 * @author: whr.
 */
public class RegistroLocalidadReportEmergenciaMapper implements RowMapper<LocalidadEmergenciaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public LocalidadEmergenciaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		LocalidadEmergenciaBean emergencia = new LocalidadEmergenciaBean();
	
		emergencia.setDesDepartamento(rs.getString("DES_DEPARTAMENTO"));
		emergencia.setDesProvincia(rs.getString("DES_PROVINCIA"));
		emergencia.setDesDistrito(rs.getString("DES_DISTRITO"));
		emergencia.setDesCentroPoblado(rs.getString("DES_CENTRO_POBLADO"));
		emergencia.setFamAfectado(rs.getInt("FAM_AFECTADO"));
		emergencia.setFamDamnificado(rs.getInt("FAM_DAMNIFICADO"));
		emergencia.setTotalFam(rs.getInt("TOTAL_FAMILIAS"));
		emergencia.setPersoAfectado(rs.getInt("PERSO_AFECTADO"));
		emergencia.setPersoDamnificado(rs.getInt("PERSO_DAMNIFICADO"));
		emergencia.setTotalPerso(rs.getInt("TOTAL_PERSONAS"));
		
       return emergencia;
	}

}
