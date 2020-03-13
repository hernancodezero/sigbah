package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.EstadoCartillaInventarioBean;

/**
 * @className: EstadoCartillaInventarioBean.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class EstadoCartillaInventarioMapper implements RowMapper<EstadoCartillaInventarioBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EstadoCartillaInventarioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		EstadoCartillaInventarioBean estado = new EstadoCartillaInventarioBean();
		estado.setIdEstado(rs.getInt("FK_IDE_ESTADO"));
		estado.setNombreEstado(rs.getString("NOM_ESTADO"));
		return estado;
	}

}
