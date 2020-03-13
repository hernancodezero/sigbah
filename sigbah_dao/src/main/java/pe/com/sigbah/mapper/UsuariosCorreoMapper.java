package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.UsuarioBean;

/**
 * @className: UsuariosMapper.java
 * @description: 
 * @date: 21 de ago. de 2017
 * @author: ARCHY.
 */
public class UsuariosCorreoMapper implements RowMapper<UsuarioBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public UsuarioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		UsuarioBean usuarioBean = new UsuarioBean();
		usuarioBean.setNombreDdi(rs.getString("DDI"));
		usuarioBean.setIdUsuario(rs.getInt("ID"));
		usuarioBean.setUsuario(rs.getString("USERNAME"));
		usuarioBean.setNombreUsuario(rs.getString("USUARIO"));
		usuarioBean.setIdDdi(rs.getInt("IDE_DDI"));
		usuarioBean.setCodigoDdi(rs.getString("COD_DDI"));
		usuarioBean.setEmail(rs.getString("EMAIL"));
		return usuarioBean;
		
	}

}
