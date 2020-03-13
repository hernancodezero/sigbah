package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesBean;

/**
 * @className: ControlCalidadMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DonacionesMapper implements RowMapper<DonacionesBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesBean donaciones = new DonacionesBean();
		donaciones.setIdDonacion(rs.getInt("IDE_DONACION"));
		donaciones.setCodigoDonacion(rs.getString("COD_DONACION"));
		donaciones.setCodigoAnio(rs.getString("ANIO"));	
		donaciones.setCodigoDdi(rs.getString("DDI"));
		donaciones.setFechaEmision(rs.getString("FECHA"));
		donaciones.setNombrePais(rs.getString("PAIS"));
		donaciones.setNombreEstado(rs.getString("ESTADO"));
		donaciones.setNombreDonante(rs.getString("DONANTE"));
		donaciones.setCodigoMes(rs.getString("Mes"));
		donaciones.setIdEstado(rs.getInt("fk_ide_estado"));
		return donaciones;
	}

}
