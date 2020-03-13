package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.RequerimientoBean;


/**
 * @className: RequerimientoMapper.java
 * @description: 
 * @date: 19 jul. 2017
 * @author: whr.
 */
public class RequerimientoMapper implements RowMapper<RequerimientoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RequerimientoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RequerimientoBean requerimiento = new RequerimientoBean();
		
		requerimiento.setCodAnio(rs.getString("COD_ANIO"));
		requerimiento.setCodMes(rs.getString("COD_MES"));	
		requerimiento.setNomMes(rs.getString("NOMBRE_MES"));
		requerimiento.setFkIdeDdi(rs.getInt("FK_IDE_DDI"));
		requerimiento.setNombreDdi(rs.getString("NOM_DDI"));
		requerimiento.setIdRequerimiento(rs.getInt("IDE_REQUERIMIENTO"));
		requerimiento.setFkIdeFenomeno(rs.getInt("FK_IDE_FENOMENO"));
		requerimiento.setDescFenomeno(rs.getString("DESC_FENOMENO"));		
		requerimiento.setCodRequerimiento(rs.getString("COD_REQUERIMIENTO"));
		requerimiento.setNomRequerimiento(rs.getString("NOM_REQUERIMIENTO"));
		requerimiento.setFkIdeRegion(rs.getInt("FK_IDE_REGION"));
		requerimiento.setNomRegion(rs.getString("NOM_REGION"));
		requerimiento.setFechaRequerimiento(rs.getString("FECHA_REQUERIMIENTO"));
		requerimiento.setNumRequerimiento(rs.getString("NUM_REQUERIMIENTO"));
		
		
       return requerimiento;
	}

}
