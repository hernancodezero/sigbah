package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesBean;

/**
 * @className: HistorialDonacionesMapper.java
 * @description: 
 * @date: 06 de set. de 2017
 * @author: ARCHY.
 */
public class HistorialDonacionesMapper implements RowMapper<DonacionesBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesBean donaciones = new DonacionesBean();
		donaciones.setCodigoAnio(rs.getString("ANIO"));
		donaciones.setNombreDdi(rs.getString("DDI"));
		donaciones.setCodigoDonacion(rs.getString("COD_DONACION"));	
		donaciones.setTipoDonacion(rs.getString("PROCEDENCIA"));
		donaciones.setNombrePais(rs.getString("PAIS"));
		donaciones.setNombreDonante(rs.getString("DONANTE"));
		donaciones.setIdEstado(rs.getInt("fk_ide_estado"));
		donaciones.setNombreEstado(rs.getString("ESTADO"));
		donaciones.setIdDonacion(rs.getInt("IDE_DONACION"));
		return donaciones;
	}

}
