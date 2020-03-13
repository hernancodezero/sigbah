package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesBean;

/**
 * @className: HistorialReporGeneralMapper.java
 * @description: 
 * @date: 06 de set. de 2017
 * @author: ARCHY.
 */
public class HistorialReporGeneralMapper implements RowMapper<DonacionesBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesBean donaciones = new DonacionesBean();
		donaciones.setCodigoDonacion(rs.getString("COD_DONACION"));	
		donaciones.setCodigoAnio(rs.getString("ANIO"));
		donaciones.setNombreDdi(rs.getString("DDI"));
		donaciones.setFechaEmision(rs.getString("FECHA"));
		donaciones.setNombreEstado(rs.getString("ESTADO"));
		donaciones.setNroDee(rs.getString("NRO_DEE"));
		donaciones.setNombreDeclaratoria(rs.getString("NOM_DECLARATORIA"));
		donaciones.setTipoDonacion(rs.getString("TIPO_DONACION"));
		return donaciones;
	}

}
