package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.RequerimientoBean;


/**
 * @className: RequerimientoEditMapper.java
 * @description: 
 * @date: 19 jul. 2017
 * @author: whr.
 */
public class RequerimientoEditMapper implements RowMapper<RequerimientoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RequerimientoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RequerimientoBean requerimiento = new RequerimientoBean();
		requerimiento.setIdRequerimiento(rs.getInt("IDE_REQUERIMIENTO"));	
		requerimiento.setCodAnio(rs.getString("COD_ANIO"));
		requerimiento.setFkIdeDdi(rs.getInt("FK_IDE_DDI"));
		requerimiento.setNombreDdi(rs.getString("NOMBRE_DDI"));
		requerimiento.setNumRequerimiento(rs.getString("NRO_REQ_CONCATENADO"));
		requerimiento.setCodRequerimiento(rs.getString("COD_REQUERIMIENTO"));
		requerimiento.setNomRequerimiento(rs.getString("NOM_REQUERIMIENTO"));
		requerimiento.setFechaRequerimiento(rs.getString("FEC_REQUERIMIENTO"));
		requerimiento.setFkIdeRegion(rs.getInt("FK_IDE_REGION"));
		requerimiento.setFlgSinpad(rs.getString("FLG_SINPAD"));
		requerimiento.setFkIdeFenomeno(rs.getInt("FK_IDE_FENOMENO"));
		requerimiento.setObservacion(rs.getString("OBSERVACION"));	
		requerimiento.setIdEstado(rs.getInt("FK_IDE_ESTADO"));	
		
       return requerimiento;
	}

}
