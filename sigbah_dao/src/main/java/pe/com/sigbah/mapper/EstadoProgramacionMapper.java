package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.EstadoProgramacionBean;

/**
 * @className: EstadoProgramacionMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class EstadoProgramacionMapper implements RowMapper<EstadoProgramacionBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EstadoProgramacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		EstadoProgramacionBean estado = new EstadoProgramacionBean();
		estado.setIdEstado(rs.getInt("FK_IDE_ESTADO_PROGRAMACION"));
		estado.setNombreEstado(rs.getString("NOM_ESTADO_PROGRAMACION"));
		estado.setFechaEstado(rs.getString("FECHA_ESTADO"));
		estado.setObservacion(rs.getString("OBSERVACION"));
		estado.setUsuario(rs.getString("NOMBRE_USUARIO"));
		return estado;
	}

}
