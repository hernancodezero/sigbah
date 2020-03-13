package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.ProgramacionAlmacenBean;

/**
 * @className: ProgramacionAlmacenMapper.java
 * @description: 
 * @date: 21 de jun. de 2017
 * @author: Junior Huaman Flores.
 */
public class ProgramacionAlmacenMapper implements RowMapper<ProgramacionAlmacenBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ProgramacionAlmacenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProgramacionAlmacenBean almacen = new ProgramacionAlmacenBean();
		almacen.setIdProgramacionAlmacen(rs.getInt("IDE_PROG_ALMACEN"));
		almacen.setIdProgramacion(rs.getInt("FK_IDE_PROGRAMACION"));
		almacen.setIdAlmacen(rs.getInt("IDE_ALMACEN"));
		almacen.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		return almacen;
	}

}
