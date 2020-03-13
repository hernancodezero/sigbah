package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.CorreoBean;

/**
 * @className: CorreoProgMapper.java
 * @description: 
 * @date: 19 de jun. de 2018
 * @author: Alan Chumpitaz.
 */
public class CorreoProgMapper implements RowMapper<CorreoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public CorreoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		CorreoBean correo = new CorreoBean();

		correo.setAsunto(rs.getString("ASUNTO"));
		correo.setMensaje(rs.getString("MENSAJE"));
		return correo;
	}

}
