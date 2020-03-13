package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.EstadoCartillaInventarioBean;

/**
 * @className: DetalleEstadoCartillaInventarioMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class DetalleEstadoCartillaInventarioMapper implements RowMapper<EstadoCartillaInventarioBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EstadoCartillaInventarioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		EstadoCartillaInventarioBean estado = new EstadoCartillaInventarioBean();
		estado.setItem(rs.getBigDecimal("ROWNUM"));
		estado.setNroCartilla(rs.getString("NRO_CARTILLA"));
		estado.setIdCartilla(rs.getInt("FK_IDE_CARTILLA"));
		estado.setIdEstado(rs.getInt("IDE_ESTADO"));
		estado.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		estado.setFechaEstado(rs.getString("FECHA_ESTADO"));
		estado.setUsuario(rs.getString("USUARIO"));
		return estado;
	}

}
