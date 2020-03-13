package pe.com.sigbah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sigbah.common.bean.AlmacenBean;

/**
 * @className: AlmacenUsuarioMapper.java
 * @description: 
 * @date: 9 de jul. de 2017
 * @author: Junior Huaman Flores.
 */
public class AlmacenUsuarioDdiMapper implements RowMapper<AlmacenBean> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public AlmacenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		AlmacenBean almacen = new AlmacenBean();
		
		almacen.setIdAlmacen(rs.getInt("IDE_ALMACEN"));
		almacen.setIdDdi(rs.getInt("FK_IDE_DDI"));
		almacen.setNombreAlmacen(rs.getString("NOMBRE_ALMACEN"));
		almacen.setExiste(rs.getString("EXISTE"));
		return almacen;
	}

}
