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
public class EmergenciaMapper implements RowMapper<EmergenciaBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EmergenciaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmergenciaBean emergencia = new EmergenciaBean();
		emergencia.setIdEmergencia(rs.getInt("IDE_EMERGENCIA"));
		emergencia.setNombreEmergencia(rs.getString("NOMBRE_EMERGENCIA"));
		emergencia.setIdFenomeno(rs.getInt("IDE_FENOMENO"));
		emergencia.setDescFenomeno(rs.getString("DESC_FENOMENO"));		
		emergencia.setCodAnio(rs.getString("COD_ANIO"));
		emergencia.setNombreMes(rs.getString("NOMBRE_MES"));
		emergencia.setCodMes(rs.getString("COD_MES"));		
		emergencia.setCodDepartamento(rs.getString("COD_DEPARTAMENTO"));
		emergencia.setDesDepartamento(rs.getString("DES_DEPARTAMENTO"));
		emergencia.setCodProvincia(rs.getString("COD_PROVINCIA"));
		emergencia.setDesProvincia(rs.getString("DES_PROVINCIA"));
		emergencia.setCodDistrito(rs.getString("COD_DISTRITO"));
		emergencia.setDesDistrito(rs.getString("DES_DISTRITO"));
		emergencia.setFamAfectado(rs.getInt("FAM_AFECTADO"));
		emergencia.setFamDamnificado(rs.getInt("FAM_DAMNIFICADO"));
		emergencia.setPersoAfectado(rs.getInt("PERSO_AFECTADO"));
		emergencia.setPersoDamnificado(rs.getInt("PERSO_DAMNIFICADO"));
		emergencia.setTotalFam(rs.getInt("TOTAL_FAMILIAS"));
		emergencia.setTotalPerso(rs.getInt("TOTAL_PERSONAS"));
		emergencia.setFecha(rs.getString("FECHA"));	
		
       return emergencia;
	}

}
