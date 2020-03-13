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
public class RegistroLocalidadEmergenciaMapper implements RowMapper<LocalidadEmergenciaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public LocalidadEmergenciaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		LocalidadEmergenciaBean emergencia = new LocalidadEmergenciaBean();
		emergencia.setIdEmergencia(rs.getInt("IDE_EMERGENCIA"));
		emergencia.setAnio(rs.getString("ANIO"));
		emergencia.setCodDepartamento(rs.getString("COD_DEPARTAMENTO"));
		emergencia.setDesDepartamento(rs.getString("DES_DEPARTAMENTO"));
		emergencia.setCodProvincia(rs.getString("COD_PROVINCIA"));
		emergencia.setDesProvincia(rs.getString("DES_PROVINCIA"));
		emergencia.setCodDistrito(rs.getString("COD_DISTRITO"));
		emergencia.setDesDistrito(rs.getString("DES_DISTRITO"));
		emergencia.setCodCentroPoblado(rs.getString("COD_CENTRO_POBLA"));
		emergencia.setDesCentroPoblado(rs.getString("DES_CENTRO_POBLA"));
		emergencia.setFamAfectado(rs.getInt("FAM_AFECTADO"));
		emergencia.setFamDamnificado(rs.getInt("FAM_DAMNIFICADO"));
		emergencia.setPersoAfectado(rs.getInt("PERSO_AFECTADO"));
		emergencia.setPersoDamnificado(rs.getInt("PERSO_DAMNIFICADO"));
		
         
       return emergencia;
	}

}
