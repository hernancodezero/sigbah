package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.UsuarioWSBean;

/**
 * @className: UsuarioWSMapper.java
 * @description: 
 * @date: 14 de ago. de 2018
 * @author: Alan Chumpitaz.
 */
public class UsuarioWSMapper implements RowMapper<UsuarioWSBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public UsuarioWSBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		UsuarioWSBean usuarioBean = new UsuarioWSBean();

		usuarioBean.setUsuario(rs.getString("U_SER"));
		usuarioBean.setPassword(rs.getString("P_ASS"));
		usuarioBean.setEmail(rs.getString("E_MAIL"));

		return usuarioBean;
		
	}

}
