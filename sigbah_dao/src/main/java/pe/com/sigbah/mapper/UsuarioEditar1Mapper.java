package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.UsuarioBean;

/**
 * @className: UsuarioEditarMapper.java
 * @description: 
 * @date: 21 de ago. de 2017
 * @author: ARCHY.
 */
public class UsuarioEditar1Mapper implements RowMapper<UsuarioBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public UsuarioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		UsuarioBean usuarioBean = new UsuarioBean();
		usuarioBean.setIdUsuario(rs.getInt("ID"));
		usuarioBean.setUsuario(rs.getString("USERNAME"));
		usuarioBean.setNombreUsuario(rs.getString("NOMBRE_USUARIO").trim());
		usuarioBean.setIdDdi(rs.getInt("IDE_DDI"));
//		usuarioBean.setIdAlmacen(rs.getInt("IDE_ALMACEN"));
		usuarioBean.setEmail(rs.getString("EMAIL"));
		usuarioBean.setCargo(rs.getString("CARGO"));
		usuarioBean.setPassword(rs.getString("PASSWORD"));
		usuarioBean.setFlagActivo(rs.getString("flg_activo"));
		return usuarioBean;
		
	}

}
