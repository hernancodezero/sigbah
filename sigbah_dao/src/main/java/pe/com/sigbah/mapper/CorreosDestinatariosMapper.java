package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.CorreoBean;

/**
 * @className: CorreosDestinatariosMapper.java
 * @description: 
 * @date: 27 de set. de 2017
 * @author: ARCHY.
 */
public class CorreosDestinatariosMapper implements RowMapper<CorreoBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public CorreoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		CorreoBean correoBean = new CorreoBean();
		correoBean.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		correoBean.setNombre(rs.getString("NOMBRE_MODULO"));
		correoBean.setIdUsuario(rs.getInt("FK_IDE_USER_DESTINATARIO"));
		correoBean.setNombreEstado(rs.getString("NOM_ESTADO"));		
		correoBean.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
		correoBean.setUsuario(rs.getString("USERNAME"));
		correoBean.setCorreo(rs.getString("EMAIL"));
		correoBean.setCargo(rs.getString("CARGO"));
		correoBean.setNombreDdi(rs.getString("NOM_DDI"));
		correoBean.setNombreFlag(rs.getString("NOMBRE_FLG_ACTIVO"));
		correoBean.setFlag(rs.getString("FLG_ACTIVO"));
		return correoBean;
	}

}
