package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProyectoManifiestoBean;

/**
 * @className: ManifiestoMapper.java
 * @description: 
 * @date: 22 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ManifiestoMapper implements RowMapper<ProyectoManifiestoBean> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProyectoManifiestoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProyectoManifiestoBean proyecto = new ProyectoManifiestoBean();
		proyecto.setIdProyectoManifiesto(rs.getInt("IDE_PROYECTO_MANIF"));
		proyecto.setNroProyectoManifiesto(rs.getString("NRO_PROY_MANIFIESTO"));
		proyecto.setNroProgramacion(rs.getString("NRO_PROGRAMACION"));
		proyecto.setIdProgramacion(rs.getInt("IDE_PROGRAMACION"));
		return proyecto;
	}

}
