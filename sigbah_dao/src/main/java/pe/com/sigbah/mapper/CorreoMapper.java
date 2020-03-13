package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.CorreoBean;

/**
 * @className: CorreoMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class CorreoMapper implements RowMapper<CorreoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public CorreoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		CorreoBean controlCalidad = new CorreoBean();
		controlCalidad.setIdEstado(rs.getInt("ID_ESTADO"));
		controlCalidad.setNombreEstado(rs.getString("ESTADO"));
		controlCalidad.setModulo(rs.getString("MODULO"));
		controlCalidad.setNombre(rs.getString("NOMBRE"));		
		controlCalidad.setCorreo(rs.getString("CORREO"));
		controlCalidad.setAsunto(rs.getString("ASUNTO"));
		controlCalidad.setMensaje(rs.getString("MENSAJE"));
		return controlCalidad;
	}

}
