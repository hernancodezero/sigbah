package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.EstadoUsuarioBean;

/**
 * @className: EstadoUsuarioMapper.java
 * @description: 
 * @date: 9 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class EstadoUsuarioMapper implements RowMapper<EstadoUsuarioBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EstadoUsuarioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		EstadoUsuarioBean estado = new EstadoUsuarioBean();
		estado.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		estado.setNombreEstado(rs.getString("NOM_ESTADO"));
		return estado;
	}

}
