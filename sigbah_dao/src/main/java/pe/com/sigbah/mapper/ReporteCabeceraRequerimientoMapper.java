package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.RequerimientoBean;


/**
 * @className: EmergenciaMapper.java
 * @description: 
 * @date: 15 jul. 2017
 * @author: whr.
 */
public class ReporteCabeceraRequerimientoMapper implements RowMapper<RequerimientoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RequerimientoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RequerimientoBean emergencia = new RequerimientoBean();
		emergencia.setNombreSistema(rs.getString("Nombre_Sistema"));
		emergencia.setNumRequerimiento(rs.getString("NUM_REQUERIMIENTO"));
		emergencia.setNomRequerimiento(rs.getString("NOM_REQUERIMIENTO"));
		emergencia.setFechaRequerimiento(rs.getString("FECHA_REQUERIMIENTO"));
		emergencia.setDescFenomeno(rs.getString("DESC_FENOMENO"));		
		emergencia.setNomRegion(rs.getString("NOM_REGION"));
		emergencia.setFlgSinpad(rs.getString("FLG_SINPAD"));
		emergencia.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
        		 
       return emergencia;
	}

}
