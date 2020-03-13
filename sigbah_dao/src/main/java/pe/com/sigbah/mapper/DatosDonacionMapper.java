package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.DonacionesBean;

/**
 * @className: DatosDonacionMapper.java
 * @description: 
 * @date: 20 de jul. de 2017
 * @author: ARCHY.
 */
public class DatosDonacionMapper implements RowMapper<DonacionesBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DonacionesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DonacionesBean donaciones = new DonacionesBean();
		donaciones.setIdDonacion(rs.getInt("IDE_DONACION"));
		donaciones.setCodigoDonacion(rs.getString("DONACION"));
		donaciones.setTipoDonante(rs.getString("NOMBRE_TIPO_DONACION"));	
		donaciones.setNombreDonante(rs.getString("NOM_DONANTE"));
		donaciones.setRepresentante(rs.getString("REPRESENTANTE"));
		return donaciones;
	}

}
