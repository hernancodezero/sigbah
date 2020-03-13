package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesBean;

/**
 * @className: HistorialReporDonanteMapper.java
 * @description: 
 * @date: 06 de set. de 2017
 * @author: ARCHY.
 */
public class HistorialReporDonanteMapper implements RowMapper<DonacionesBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesBean donaciones = new DonacionesBean();
		donaciones.setNombreOrigen(rs.getString("PROCEDENCIA"));	
		donaciones.setNombrePais(rs.getString("NOMBRE_ES"));
		donaciones.setTipoDonante(rs.getString("TIPO_PERSONA"));
		donaciones.setNombreDonante(rs.getString("NOM_DONANTE"));
		donaciones.setRepresentante(rs.getString("REPRESENTANTE"));
		donaciones.setFinalidad(rs.getString("FINALIDAD"));
		return donaciones;
	}

}
